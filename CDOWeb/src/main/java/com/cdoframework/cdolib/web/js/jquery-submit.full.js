jQuery.extend({
	   asyncCfmSubmit:function(_message,_url,_paramJSON,_callback){
	 		 $.submitCfmData(_message,true,_url,_paramJSON,_callback);
	 	},	
	 	
	   syncCfmSubmit:function(_message,_url,_paramJSON,_callback){
		   	 $.submitCfmData(_message,false,_url,_paramJSON,_callback);
		 },
	
	   submitCfmData:function(_message,_bAsync,_url,_paramJSON,_callback){
		   		  //将html 转义符转化为正常符
		   		  $.decodeParam(_paramJSON);
				  bootbox.confirm(_message, function(result){
				  if(result){                   
				    		$.ajax({
				    			type : "post",
				    			url : _url,
				    			async:_bAsync,
				    			cache : false,
				    			data : _paramJSON,
				    			success : function(data) {
				    				return _callback(data);
				    			},
				    			error : function() {				
				    				bootbox.alert('ajax通讯异常,保存失败!');
				    			}
				    		});	
				    	}//result
				    })//confirm
		}, 
		
	 	asyncSubmit:function(_url,_paramJSON,_callback){
	 		  $.submitData(true,_url,_paramJSON,_callback);
	 	},	
	 	
	    syncSubmit:function(_url,_paramJSON,_callback){
		   	 $.submitData(false,_url,_paramJSON,_callback);
		 },
	
		submitData:function(_bAsync,_url,_paramJSON,_callback){	
	   		  //将html 转义符转化为正常符
	   		$.decodeParam(_paramJSON);
			$.ajax({
    			type : "post",
    			url : _url,
    			async:_bAsync,
    			cache : false,
    			data : _paramJSON,
    			success : function(data) {
    				 _callback(data);
    			},
    			error : function() {				
    				bootbox.alert('ajax通讯异常,保存失败!');
    			}
    		});	
		},
		asynSubmitData:function(_url,_paramJSON,_callback){	
	   		  //将html 转义符转化为正常符
	   		$.decodeParam(_paramJSON);
			$.ajax({
  			type : "post",
  			url : _url,
  			async:true,
  			cache : false,
  			data : _paramJSON,
  			success : function(data) {
  				 _callback(data);
  			}
		  });	
		},		
		decodeParam:function(_paramJSON){
			for(var key in _paramJSON){
				var oldVal=_paramJSON[key];
				var objType=$.type(oldVal);
				if(objType =="string"){
					_paramJSON[key]=$.htmlDecode(oldVal);
				}else if(objType=="object"){
					$.decodeParam(oldVal);
				}else if(objType=="array"){
					$.decodeArray(oldVal);
				}
			}
		},
		decodeArray:function(_array){
			for(var i=0;i<_array.length;i++){
				var oldVal=_array[i];
				var objType=$.type(oldVal);
				if(objType =="string"){
					_array[i]=$.htmlDecode(oldVal);
				}else if(objType=="object"){
					$.decodeParam(oldVal);
				}else if(objType=="array"){
					$.decodeArray(oldVal);
				}
			}
		},
		htmlEncode:function(str){
			var s = "";
			if(str.length == 0) return "";
			s = str.replace(/&/g,"&amp;");
			s = s.replace(/</g,"&lt;");
			s = s.replace(/>/g,"&gt;");
			s = s.replace(/ /g,"&nbsp;");
			s = s.replace(/\'/g,"&#39;");
			s = s.replace(/\"/g,"&quot;");
			s = s.replace(/\r/g,"");
			s = s.replace(/\\/g,"&#092;");
			s = s.replace(/\//g,"&#47;");		
			return s;
		},
		htmlDecode:function(str){
			var s = "";
			if(str.length == 0) return "";
			s = str.replace(/&amp;/g,"&");
			s = s.replace(/&lt;/g,"<");
			s = s.replace(/&gt;/g,">");
			s = s.replace(/&nbsp;/g," ");
			s = s.replace(/&#39;/g,"\'");
			s = s.replace(/&quot;/g,"\"");
			s = s.replace(/&#092;/g,"\\");
			s = s.replace(/&#47;/g,"/");
			return s;
		}
  });   