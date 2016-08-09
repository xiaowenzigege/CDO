/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.cdo.business.client.rpc;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.cdo.google.handle.CDOMessage;
import com.cdo.google.handle.Header;
import com.cdo.google.handle.ProtoProtocol;
import com.cdo.google.protocol.GoogleCDO;
import com.cdo.util.common.UUidGenerator;
import com.cdo.util.constants.Constants;
import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.Field;
import com.cdoframework.cdolib.data.cdo.FileField;
import com.google.protobuf.ByteString;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.DiskAttribute;
import io.netty.handler.codec.http.multipart.DiskFileUpload;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.util.CharsetUtil;

/**
 * Handler that just dumps the contents of the response from the server
 */
public class HttpClientHandler extends SimpleChannelInboundHandler<HttpObject> {

    private boolean readingChunks;
    private volatile Channel channel;
    private String clientId;
    final CallsLinkedHashMap calls = new CallsLinkedHashMap();
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        channel = ctx.channel();
        clientId=UUidGenerator.ClientId.toString(UUidGenerator.ClientId.getClientId());
    }
    
    @Override
    public void channelRead0(ChannelHandlerContext ctx, HttpObject msg) {
        if (msg instanceof HttpResponse) {
            HttpResponse response = (HttpResponse) msg;

           
            System.err.println("STATUS: " + response.status());
            System.err.println("VERSION: " + response.protocolVersion());

            if (!response.headers().isEmpty()) {
                for (CharSequence name : response.headers().names()) {
                    for (CharSequence value : response.headers().getAll(name)) {
                        System.err.println("HEADER: " + name + " = " + value);
                    }
                }
            }
            DefaultFullHttpResponse response1=(DefaultFullHttpResponse)response;
//            response1.content().
//            response.c
            if (response.status().code() == 200 && HttpUtil.isTransferEncodingChunked(response)) {
                readingChunks = true;
                System.err.println("CHUNKED CONTENT {");
            } else {
                System.err.println("CONTENT {");
            }
        }
        if (msg instanceof HttpContent) {
            HttpContent chunk = (HttpContent) msg;
            System.err.println(chunk.content().toString(CharsetUtil.UTF_8));
            
            if (chunk instanceof LastHttpContent) {
                if (readingChunks) {
                    System.err.println("} END OF CHUNKED CONTENT");
                } else {
                    System.err.println("} END OF CONTENT");
                }
                readingChunks = false;
            } else {
                System.err.println(chunk.content().toString(CharsetUtil.UTF_8));
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.channel().close();
    }
    
    public CDO handleTrans(String url,CDO cdoRequest) {
    	
        HttpDataFactory factory = new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE); // Disk if MINSIZE exceed

        DiskFileUpload.deleteOnExitTemporaryFile = true; // should delete file on exit (in normal exit)
        DiskFileUpload.baseDirectory =System.getProperty("java.io.tmpdir"); // system temp directory
        DiskAttribute.deleteOnExitTemporaryFile = true; // should delete file on exit (in normal exit)
        DiskAttribute.baseDirectory = System.getProperty("java.io.tmpdir"); // system temp directory
        
    	//CDO请求数据
    	int callId=RPCClientHandler.callIdCounter.getAndIncrement() & Integer.MAX_VALUE;
        final Call call =new Call(callId);    
        sendmultipart(url, cdoRequest);		            
        calls.put(callId, call);
        boolean interrupted = false;
        synchronized (call) {
          while (!call.done()) {
            try {
              call.wait();                 
            } catch (InterruptedException ie) {
              // save the fact that we were interrupted
              interrupted = true;
            }
          }
          if (interrupted) {
            // set the interrupt flag now that we are done waiting
            Thread.currentThread().interrupt();
          }    
         return new CDO();//call.getRpcResponse();
       } 
        
    }
    
    private  void sendmultipart(String url,CDO cdoRequest){
        // Start the connection attempt.
//        ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port));
        // Wait until the connection attempt succeeds or fails.
//        Channel channel = future.sync().channel();
    	try{
	    	URI uriFile = new URI(url);
	        // Prepare the HTTP request.
	        HttpDataFactory factory = new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE);
	        HttpRequest request = new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, uriFile.toASCIIString());
	        HttpPostRequestEncoder bodyRequestEncoder =new HttpPostRequestEncoder(factory, request, true); // true => multipart
	        
	        HttpHeaders headers = request.headers();
	        headers.set(HttpHeaderNames.HOST, uriFile.getHost());
	        headers.set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
	        headers.set(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.GZIP + "," + HttpHeaderValues.DEFLATE);
	
	        headers.set(HttpHeaderNames.ACCEPT_CHARSET, "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
	        headers.set(HttpHeaderNames.ACCEPT_LANGUAGE, "fr");
	        headers.set(HttpHeaderNames.REFERER, uriFile.toString());
	        headers.set(HttpHeaderNames.USER_AGENT, "Netty Simple File Http Client side");
	        headers.set(HttpHeaderNames.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	        

	
	
	        // add Form attribute
	        bodyRequestEncoder.addBodyAttribute(Constants.CDO.HTTP_CDO_REQUEST, cdoRequest.toXML());
//	        bodyRequestEncoder.addBodyAttribute("strTransName", cdoRequest.getStringValue("strTransName"));
			Iterator<Map.Entry<String,Field>> it=cdoRequest.entrySet().iterator();		    		 
			 while(it.hasNext()){
				 Map.Entry<String,Field> entry=it.next();
				 Field objExt=entry.getValue();
				 if(objExt.getType().getDataType()==DataType.FILE_TYPE){
					 bodyRequestEncoder.addBodyFileUpload(entry.getKey(),((FileField)entry.getValue()).getValue(), "application/x-zip-compressed", false);
				 }
			 }        
	

	        // add Form attribute from previous request in formpost()
	        bodyRequestEncoder.setBodyHttpDatas(bodyRequestEncoder.getBodyListAttributes());
	
	        // finalize request
	        bodyRequestEncoder.finalizeRequest();
	
	        // send request
	        channel.write(request);
	
	        // test if request was chunked and if so, finish the write
	        if (bodyRequestEncoder.isChunked()) {
	            channel.write(bodyRequestEncoder);
	        }
	        channel.flush();
	
	        // Now no more use of file representation (and list of HttpData)
	        bodyRequestEncoder.cleanFiles();
    	}catch(Exception ex){
    	    ex.printStackTrace();	
    	}
    }
}
