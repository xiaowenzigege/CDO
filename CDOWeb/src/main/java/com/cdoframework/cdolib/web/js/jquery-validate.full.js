jQuery.extend({
	 		regex:function (value,params) {
	 			 var exp = new RegExp(params);
	 			 return exp.test(value);
            },
            validateForm:function(_formId,_jsonRule){
            	var inputElements=$("#"+_formId).find("input,textarea");
            	var errorMsg="";            	
            	var index=1;   
            	var checkJSON={};
            	var rule=_jsonRule["rules"];
            	var message={};
            	if(_jsonRule.hasOwnProperty("messages")){
            		message=_jsonRule["messages"]; 
            	}
            	inputElements.each(function(){ 
            			//遍历form里元素对象
            			var placeholder=$.trim($(this).attr("placeholder"));            			
            			if(placeholder!=""){
            				placeholder=",必须"+placeholder;
            			}
            			//获取元素id,name的属性值
            		    var name=$.trim($(this).attr("id"));
            		    if(name==""){
            		    	name=$.trim($(this).attr("name"));
            		    }  
            		    //若验证规则里未定义该元素,则表示不需要检查该元素的value值
            		    if(!rule.hasOwnProperty(name)){
            		    	return true;
            		    }
            		    //获取元素的value值
            		    var value=$.trim($(this).val());                		    
            		    //验证元素规则json
            		    var elementRule=rule[name];
            		    //验证元素不符合规则时 提示信息
            		    var elementMsg={};
            		    if(message.hasOwnProperty(name)){
            		    	elementMsg=message[name];
            		    }            		    
            		    //规则可定义,当满足忽略条件时,则不检查此项元素值,默认 ：需要检查
            		    var ignore=false;
            		    if(elementRule.hasOwnProperty("ignore")){
            		    	var ignoreJson=elementRule["ignore"];
            		    	var ignore=true;//设置忽略
            		    	//所有条件满足时,才忽略此项检查
            		    	for(var item in ignoreJson){             		    		
            		    		var condition=ignoreJson[item]+"";              		    	    	
            		    	    if($("#"+item).val()!=condition){
            		    	    	ignore=false;//当有一项不满条件时
            		    	    	break;
            		    	    }
            		    	}
                    		if(ignore){ 
                    		  return true;
                    		}    
            		    }
            		    
            		    var required=false;
            		    if(elementRule.hasOwnProperty("required")){
            		    	required=elementRule["required"];
            		    	//当满足某些条件,去掉必须检查项
                		    if(elementRule.hasOwnProperty("ignoreRequired")){
                		    	var ignoreJson=elementRule["ignoreRequired"];
                		    	var ignoreRequired=true;//设置忽略必须
                		    	//所有条件满足时,才忽略此项检查
                		    	for(var item in ignoreJson){             		    		
                		    		var condition=ignoreJson[item]+"";              		    	    	
                		    	    if($("#"+item).val()!=condition){
                		    	    	ignoreRequired=false;//当有一项不满条件时
                		    	    	break;
                		    	    }
                		    	}
                        		if(ignoreRequired){ 
                        			required=false;
                        		}    
                		    }
            		     }   
            		    
            		    //设置不满足正则表达式的   默认 提示信息
            		     var regexMsg="";
	       		    	 if(elementRule.hasOwnProperty("label")){
	       		    		regexMsg=elementRule["label"]+"不符合规则";
	    		    	 }
	       		    	 //如果message定义了此元素的提示信息,则使用message里的提示信息
        		    	 if(elementMsg.hasOwnProperty("regex")){            		    		 
        		    		 regexMsg=elementMsg["regex"];
        		    	 }
        		    	 
        		    	//1.必选项
            		    if(required){            		    	
            		    	 var type =$(this).attr("type");
            		    	//设置不满足条件时的   默认 提示信息
            		    	 var requireMsg="";
            		    	 if(elementRule.hasOwnProperty("label")){
            		    		 requireMsg=elementRule["label"]+"不能为空";
            		    	 }
            		    	 //如果message定义了此元素的提示信息,则使用message里的提示信息
            		    	 if(elementMsg.hasOwnProperty("required")){            		    		 
            		    		 requireMsg=elementMsg["required"];
            		    	 }
            		    	 //检查checkbox
            		    	 if(type=="checkbox"){            		    		
            		    	    var chkName=$(this).attr("name");
            		    	    //如果已经检查过了,本次终止检查
             		     	    if(checkJSON.hasOwnProperty(chkName)){
            		     		   return true;
            		     	    }
             		     	    checkJSON[chkName]=false;
            		     	    $('input[name="'+chkName+'"]').each(function() {
            	    	    		 if ($(this).is(":checked")){	    			 
            	    	    			 checkJSON[chkName]=true;
            	    	    		 }	
            		     	     }); 
            		     	   if(!checkJSON[chkName]){
            		     		   //提示
               		    		  errorMsg=errorMsg+"<li style='list-style-type:none'>"+index+"."+requireMsg+"</li>";
            		    		  index++;            		     		   
            		     	   }
            		    	   return true;
            		    	 }            		    	
            		    	//text,textArea
            		    	if(value==""){            		    				
            		    		errorMsg=errorMsg+"<li style='list-style-type:none'>"+index+"."+requireMsg+"</li>";
            		    		index++;
            		    	}else{
            		    		//没有正则表达式验证
            		    		if(!elementRule.hasOwnProperty("regex")){
            		    			return true;
            		    		}
            		    		//正则表达式验证
            		    		if(!$.regex(value,elementRule["regex"])){
            		    			errorMsg=errorMsg+"<li style='list-style-type:none'>"+index+"."+regexMsg+placeholder+"</li>";
            		    			index++;
            		    		}
            		    	}
            		    //2.可选项	
            		    }else{
            		    	if(value==""){  
            		    		return true;
            		    	}
        		    		//没有正则表达式验证
        		    		if(!elementRule.hasOwnProperty("regex")){
        		    			return true;
        		    		}     
        		    		//正则表达式验证
            		    	if(!$.regex(value,elementRule["regex"])){
        		    			errorMsg=errorMsg+"<li style='list-style-type:none'>"+index+"."+regexMsg+placeholder+"</li>";;
        		    			index++;
        		    		}
            		   }
            		});
            	if(errorMsg!=""){
            		errorMsg="<ul style='margin：0px;padding：0px;'>"+errorMsg+"</u>"
            	}
            	return errorMsg;
            }
      });    