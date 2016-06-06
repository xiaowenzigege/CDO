//--------原生数据使用nameValuePair形式数据
{
  "namespace" : "com.cdo.avro.schema",
  "type" : "record",
  "name" : "NameValuePair",
  "fields" : [  
   {"name":"field","type":"string"},
   {"name":"value","type":"bytes"}
]
}
//---------------封装成List<NameValuePair> 数据-----------------
{
  "namespace" : "com.cdo.avro.schema",
  "type" : "record",
  "name" : "CDOData",
  "fields" : [  
		{"name": "fields", "type": {"type": "array", "items":com.cdo.avro.schema.NameValuePair}}
]
}

//自定义rpc  使用 List<NameValuePair>数据传输数据,不如直接使用 rpc Map<key,Bytes>
{"namespace": "com.cdo.avro.protocol",
 "protocol": "CDOProtocol",
 "doc":"avro cdo rpc ",
  
"types": [
	{"name" : "CDOData","type" : "record",
  	"fields" : [{
    	"name" : "fields",
    	"type" : {"type" : "array","items" : {"type" : "record","name" : "NameValuePair","fields" : [ 
    															{"name" : "field","type" : "string"}, 
    															{"name" : "value","type" : "bytes"} ]
      }
    }
  } ]
}
],
 "messages": {
     "send": {
         "request": [{"name": "CDORequest", "type": "CDOData"}],
         "response":"CDOData"
     }
 }
}

//混合数据类型,测试效果不如统一 使用buffer 封装字节处理快
{
  "namespace" : "com.cdo.avro.schema",
  "type" : "record",
  "name" : "AvroCDOMixed",
  "fields" : [  
   {"name":"key","type":["null",{"type":"map", "values": ["boolean","int","long","float","double","bytes","string",{"type":"array","items":"string"}]}]}
]
}
