var HtmlAction={
		encode:function(_str){
			var s = "";
			if(_str==null ||_str==undefined || _str.length == 0) return s;
			s = _str.replace(/&/g,"&amp;");
			s = s.replace(/</g,"&lt;");
			s = s.replace(/>/g,"&gt;");
			s = s.replace(/ /g,"&nbsp;");
			s = s.replace(/\'/g,"&#039;"); 
			s = s.replace(/\"/g,"&quot;");		
			s = s.replace(/\\/g,"&#092;");
			s = s.replace(/\//g,"&#47;");
			s = s.replace(/\r/g,"");
			return s;			
		},
		decode:function(_str){
			var s = "";
			if(_str==null ||_str==undefined || _str.length == 0) return s;
			s = _str.replace(/&amp;/g,"&");
			s = s.replace(/&lt;/g,"<");
			s = s.replace(/&gt;/g,">");
			s = s.replace(/&nbsp;/g," ");
			s = s.replace(/&#039;/g,"\'");
			s = s.replace(/&quot;/g,"\"");
			s = s.replace(/&#092;/g,"\\");
			s = s.replace(/&#47;/g,"/");
			return s;			
		},
		getFileType:function(_filePath){			
			var filePath=$.trim(_filePath);
			var index =filePath.lastIndexOf(".");
		    if(index != -1)
		        return filePath.substring(startIndex+1, filePath.length).toLowerCase();
		    else 
		       return "";			
		},
		getAllCheckbox:function(_name){
			var arr=new Array();
			$('input[name="'+_name+'"]').each(function() {	
				arr.push($(this).val());
			});	
			return arr;
		},
		getCheckedbox:function(_name){
			var arr=new Array();
			$('input[name="'+_name+'"]').each(function() {					
				if($(this).is(":checked")){	    			  
					arr.push($(this).val());
				}    		 
			});				
			return arr;
		},
		arr2str:function(_arr,_split){
			var values="";
			if(_split==null || _split==undefined)
				_split=",";
			for(var i=0;i<_arr.length;i++){
				if(i>0){values+=_split}
				values+=_arr[i];
			}
			return values			
		},
		callbackMessage:function(data,_callback,sucessMsg,errorMsg,_errorCallback){
			if(sucessMsg==null || sucessMsg==undefined)
				sucessMsg="保存成功!";
			if(errorMsg==null || errorMsg==undefined)
				errorMsg="保存失败!";
			if (data.states == 2000) {
				bootbox.alert({
	                buttons:{ok: {label: '确定'}},
	                message:sucessMsg,
	                callback: _callback
	            });
			} else {	
				if(_errorCallback!=null && _errorCallback!=undefined){
					bootbox.alert({
		                buttons:{ok: {label: '确定'}},
		                message:errorMsg+data.message,
		                callback: _errorCallback
		            });
				}else{
					bootbox.alert(errorMsg+data.message);	
				}
				
			}
		}
		
}