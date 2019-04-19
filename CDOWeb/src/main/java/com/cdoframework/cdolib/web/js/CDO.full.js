//required jquery.js 1.12+ ,json2.js//
function CDO() {
	this.hmItem ={};	
	this.exists=function(strKey) {
		if(this.hmItem.hasOwnProperty(strKey)){
			return true;
		}
		return false;
	};	
	this.get=function (strKey) {
		if(this.exists(strKey)){
			return this.hmItem[strKey];			
		}
		return null;
	};	
	this.put=function(strKey, objValue) {
		this.hmItem[strKey]=objValue;
	};		
	this.getFieldValue = function (strFieldId) {
		return this.get(strFieldId);	
	};

	this.getByteValue = function (strFieldId) {
		var value= this.getFieldValue(strFieldId);
		if(value==null)return null;
		try{
			var arr=new Array();
			arr.push(value);
			return this.regByteVal(strFieldId,arr)[0];
		}catch(err){
			console.warn("getByteValue occur error,"+err); 
			return null;
		}		
	};
	this.getShortValue = function (strFieldId) {
		var value= this.getFieldValue(strFieldId);
		if(value==null)return null;
		try{
			var arr=new Array();
			arr.push(value);
			return this.regShortVal(strFieldId,arr)[0];
		}catch(err){
			console.warn("getShortValue occur error,"+err);
			return null;
		}			
	};
	this.getIntegerValue = function (strFieldId) {
		var value= this.getFieldValue(strFieldId);
		if(value==null)return null;
		try{
			var arr=new Array();
			arr.push(value);
			return this.regIntegerVal(strFieldId,arr)[0];
		}catch(err){
			console.warn("getIntegerValue occur error,"+err);
			return null;
		}			
	};
	this.getLongValue = function (strFieldId) {
		var value= this.getFieldValue(strFieldId);
		if(value==null)return null;
		try{
			var arr=new Array();
			arr.push(value);
			return this.regLongVal(strFieldId,arr)[0];
		}catch(err){
			console.warn("getLongValue occur error,"+err);
			return null;
		}			
	};
	this.getBooleanValue = function (strFieldId) {		
		var value=this.getFieldValue(strFieldId);
		if(value==null) return null;
		try{
			var arr=new Array();
			arr.push(value);
			return this.regBooleanVal(strFieldId,arr)[0];
		}catch(err){
			console.warn("getBooleanValue occur error,"+err);
			return null;
		}			
	};	
	this.getFloatValue = function (strFieldId) {
		var value= this.getFieldValue(strFieldId);
		if(value==null)return null;
		try{
			var arr=new Array();
			arr.push(value);
			return this.regFloatVal(strFieldId,arr)[0];
		}catch(err){
			console.warn("getFloatValue occur error,"+err);
			return null;
		}			
	};	
	this.getDoubleValue = function (strFieldId) {
		var value=this.getFieldValue(strFieldId);
		if(value==null)return null;
		try{
			var arr=new Array();
			arr.push(value);
			return this.regFloatVal(strFieldId,arr)[0];
		}catch(err){
			console.warn("getDoubleValue occur error,"+err);
			return null;
		}
	};	
	this.getStringValue = function (strFieldId) {		 
		 var value=this.getFieldValue(strFieldId);
		 if(value==null) return null;
		 var objType=$.type(value);
		 switch(objType){
				case "boolean":
				case "number":
			    case "string":
					value=this.encodeHtml(value+"");
				break;
				default:
				  console.warn("getStringValue occur error,Invalid FieldId "+strFieldId+",value can't be cast to String"); 
			}	
		 return value;
	};

	this.getDateValue = function (strFieldId) {		
		var value=this.getFieldValue(strFieldId);
		if(value==null)return null;
		try{
			var arr=new Array();
			arr.push(value);
			return this.regDateVal(strFieldId,arr)[0];
		}catch(err){
			console.warn("getDateValue occur error,"+err);
			return null;
		}
	};
	this.getTimeValue = function (strFieldId) {		
		var value=this.getFieldValue(strFieldId);
		if(value==null)return null;
		try{
			var arr=new Array();
			arr.push(value);
			return this.regTimeVal(strFieldId,arr)[0];
		}catch(err){
			console.warn("getTimeValue occur error,"+err);
			return null;
		}
	};
	this.getDateTimeValue = function (strFieldId) {
		var value=this.getFieldValue(strFieldId);
		if(value==null)return null;
		try{
			var arr=new Array();
			arr.push(value);
			return this.regDateTimeVal(strFieldId,arr)[0];
		}catch(err){
			console.warn("getDateTimeValue occur error,"+err);
			return null;
		}	
	};
	
	this.getCDOValue = function (strFieldId) {
		var cdoValue=this.getFieldValue(strFieldId);
		if(value==null) return null;
		if(cdoValue instanceof CDO){
			return cdoValue;
		}else{
			 console.warn("getCDOValue occur error,Invalid FieldId "+strFieldId+",value is not CDO"); 
		}
	};
	this.getByteArrayValue = function (strFieldId) {
		try{
			var value=this.getFieldValue(strFieldId);
			if(value==null) return null;
			return this.regByteVal(strFieldId,value);
		}catch(err){
			console.warn("getByteArrayValue occur error,"+err);
			return null;
		}		
	};
	this.getShortArrayValue = function (strFieldId) {
		try{
			var value=this.getFieldValue(strFieldId);
			if(value==null) return null;
			return this.regShortVal(strFieldId,value);
		}catch(err){
			console.warn("getShortArrayValue occur error,"+err);
			return null;
		}	
	};
	this.getIntegerArrayValue = function (strFieldId) {
		try{
			var value=this.getFieldValue(strFieldId);
			if(value==null) return null;
			return this.regIntegerVal(strFieldId,value);
		}catch(err){
			console.warn("getIntegerArrayValue occur error,"+err);
			return null;
		}	
	};
	this.getLongArrayValue = function (strFieldId) {
		try{
			var value=this.getFieldValue(strFieldId);
			if(value==null) return null;
			return this.regLongVal(strFieldId,value);
		}catch(err){
			console.warn("getLongArrayValue occur error,"+err);
			return null;
		}	
	};
	this.getBooleanArrayValue = function (strFieldId) {		
		try{
			var value=this.getFieldValue(strFieldId);
			if(value==null) return null;
			return this.regBooleanVal(strFieldId,value);
		}catch(err){
			console.warn("getFloatArrayValue occur error,"+err);
			return null;
		}	
	};	
	this.getFloatArrayValue = function (strFieldId) {
		try{
			var value=this.getFieldValue(strFieldId);
			if(value==null) return null;
			return this.regFloatVal(strFieldId,value);
		}catch(err){
			console.warn("getFloatArrayValue occur error,"+err);
			return null;
		}	
	};
	this.getDoubleArrayValue = function (strFieldId) {
		try{
			var value=this.getFieldValue(strFieldId);
			if(value==null) return null;
			return this.regFloatVal(strFieldId,value);
		}catch(err){
			console.warn("getDoubleArrayValue occur error,"+err);
			return null;
		}	
	};
	this.getStringArrayValue = function (strFieldId) {
		try{
			var strsValue=this.getFieldValue(strFieldId);
			if(strsValue==null) return null;
			this.checkArrVal(strFieldId,strsValue);
			for(var i=0;i<strsValue.length;i++){
				var objType=$.type(strsValue[i]);
				 switch(objType){
						case "boolean":
						case "number":
						case "string":
							strsValue[i]=this.encodeHtml(strsValue[i]+"");		
							break;
						default:
							console.warn("getStringArrayValue occur error,Invalid FieldId "+strFieldId+",value can't be cast to StringArray,value="+JSON.stringify(strsValue)); 
							return null;
					}											
			}
			return strsValue;
		}catch(err){
			console.warn("getStringArrayValue occur error,"+err);
			return null;
		}			
	};
	this.getDateArrayValue = function (strFieldId) {
		try{
			var value=this.getFieldValue(strFieldId);
			if(value==null) return null;
			return this.regDateVal(strFieldId,value);
		}catch(err){
			console.warn("getDateArrayValue occur error,"+err);
			return null;
		}	
	};
	this.getTimeArrayValue = function (strFieldId) {
		try{
			var value=this.getFieldValue(strFieldId);
			if(value==null) return null;
			return this.regTimeVal(strFieldId,value);
		}catch(err){
			console.warn("getTimeArrayValue occur error,"+err);
			return null;
		}	
	};
	this.getDateTimeArrayValue = function (strFieldId) {
		try{
			var value=this.getFieldValue(strFieldId);
			if(value==null) return null;
			return this.regDateTimeVal(strFieldId,value);
		}catch(err){
			console.warn("getDateTimeArrayValue occur error,"+err);
			return null;
		}		
	};	
	this.getCDOArrayValue = function (strFieldId) {
		var arrValues=this.getFieldValue(strFieldId);
		if(arrValues==null)return null;
		var objType=$.type(arrValues);
		if(objType!="array"){
			console.warn("getCDOArrayValue occur error,Invalid FieldId "+strFieldId+",value is not CDOArray");
			return null;			
		}	
		for(var i=0;i<arrValues.length;i++){
			if(arrValues[i] instanceof CDO){
				continue;
			}
			console.warn("getCDOArrayValue occur error,Invalid FieldId "+strFieldId+",value is not CDOArray");
			return null;
		}
		return arrValues;		
	};
	//--------------设置数据---------------//
	this.setByteValue = function (strFieldId, byValue) {
		try{
			var arr=new Array();
			arr.push(byValue);
			var value=this.regByteVal(strFieldId,arr)[0];
			this.put(strFieldId,value);
		}catch(err){
			console.warn("setByteValue occur error,"+err);
		}
	};
	this.setShortValue = function (strFieldId, shValue) {
		try{
			var arr=new Array();
			arr.push(shValue);
			var value=this.regShortVal(strFieldId,arr)[0];
			this.put(strFieldId,value);	
		}catch(err){
			console.warn("setShortValue occur error,"+err);
		}
	};
	this.setIntegerValue = function (strFieldId, nValue) {
		try{
			var arr=new Array();
			arr.push(nValue);
			var value=this.regIntegerVal(strFieldId,arr)[0];
			this.put(strFieldId,value);
		}catch(err){
			console.warn("setIntegerValue occur error,"+err);
		}
	};
	this.setLongValue = function (strFieldId, lValue) {
		try{
			var arr=new Array();
			arr.push(lValue);		
			var value=this.regLongVal(strFieldId,arr)[0];
			this.put(strFieldId,value);	
		}catch(err){
			console.warn("setLongValue occur error,"+err);
		}
	};
	this.setBooleanValue = function (strFieldId, bValue) {
		try{
			var arr=new Array();
			arr.push(bValue);				
			var value=this.regBooleanVal(strFieldId,arr)[0];
			this.put(strFieldId,value);
		}catch(err){
			console.warn("setBooleanValue occur error,"+err);
		}
	};	
	this.setFloatValue = function (strFieldId,fValue) {
		try{
			var arr=new Array();
			arr.push(fValue);			
			var value=this.regFloatVal(strFieldId,arr)[0];
			this.put(strFieldId,value);	
		}catch(err){
			console.warn("setFloatValue occur error,"+err);
		}
	};
	this.setDoubleValue = function (strFieldId, dblValue) {
		try{
			var arr=new Array();
			arr.push(dblValue);			
			var value=this.regFloatVal(strFieldId,arr)[0];
			this.put(strFieldId,value);	
		}catch(err){
			console.warn("setDoubleValue occur error,"+err);
		}
	};
	this.setStringValue = function (strFieldId,strValue) {
		try{
			this.checkField(strFieldId);
			var objType=$.type(strValue);
			switch(objType){
				case "boolean":
				case "number":
				case "string":
				var value=this.decodeHtml(strValue+"");
			    this.put(strFieldId,value)
				break;
			  default:	
				throw "Invalid FieldId " + strFieldId+",value cant't cast to String";				
			}				
		}catch(err){
			console.warn("setStringValue occur error,"+err);
		}
	};	
	this.setDateValue = function (strFieldId,dateValue) {
		try{
			var arr=new Array();
			arr.push(dateValue);				
			var value=this.regDateVal(strFieldId,arr)[0];
			this.put(strFieldId,value);
		}catch(err){
			console.warn("setDateValue occur error,"+err);
		}
	};
	this.setTimeValue = function (strFieldId, timeValue) {
		try{
			var arr=new Array();
			arr.push(timeValue);	
			var value=this.regTimeVal(strFieldId,arr)[0];
			this.put(strFieldId,value);
		}catch(err){
			console.warn("setTimeValue occur error,"+err);
		}
	};
	this.setDateTimeValue = function (strFieldId, dtValue) {
		try{
			var arr=new Array();
			arr.push(dtValue);	
			var value=this.regDateTimeVal(strFieldId,arr)[0];
			this.put(strFieldId,value);
		}catch(err){
			console.warn("setDateTimeValue occur error,"+err);
		}
	};	
	this.setCDOValue = function (strFieldId, cdoValue) {
		if(cdoValue instanceof CDO){
			this.put(strFieldId,cdoValue);	
		}else{
			console.warn("setCDOValue occur error,Invalid FieldId " + strFieldId+",value is not CDO");
		}		
	};
	
	this.regByteVal=function(strFieldId,byValues){
		this.checkField(strFieldId);
		this.checkArrVal(strFieldId,byValues);
		var rules="^-?\\d{1,3}$";
		var exp = new RegExp(rules);
		for(var i=0;i<byValues.length;i++){
			var bFlag=exp.test(byValues[i]);
			if(!bFlag){
				throw "Invalid FieldId " + strFieldId+",value is not Byte/ByteArray,value="+JSON.stringify(byValues);
			}
			var value=parseInt(byValues[i])
			if(value<-128 || value>127){
				throw "Invalid FieldId " + strFieldId+",value is not Byte/ByteArray,value="+JSON.stringify(byValues)+",maxValue=127"+",minValue=-128";
			}
			byValues[i]=value;
		}
		return byValues;
	};
	this.regShortVal=function(strFieldId,shValues){
		this.checkField(strFieldId);
		this.checkArrVal(strFieldId,shValues);
		var rules="^-?\\d{1,5}$";
		var exp = new RegExp(rules);
		for(var i=0;i<shValues.length;i++){
			var bFlag=exp.test(shValues[i]);
			if(!bFlag){
				throw "Invalid FieldId " + strFieldId+",value is not Short/ShortArray,value="+JSON.stringify(shValues);
			}
			var value=parseInt(shValues[i])
			if(value<-32768 || value>32767){
				throw "Invalid FieldId " + strFieldId+",value is not Short/ShortArray,value="+JSON.stringify(shValues)+",maxValue=32767"+",minValue=-32768";
			}
			shValues[i]=value;
		}
		return shValues;
	};	
	this.regIntegerVal=function(strFieldId,nValues){
		this.checkField(strFieldId);
		this.checkArrVal(strFieldId,nValues);
		var rules="^-?\\d{1,10}$";		
		var exp = new RegExp(rules);
		for(var i=0;i<nValues.length;i++){
			var bFlag=exp.test(nValues[i]);
			if(!bFlag){
				throw "Invalid Integer FieldId " + strFieldId+",value is not Integer/IntegerArray,value="+JSON.stringify(nValues);
			}		
			var value=parseInt(nValues[i])
			if(value<-2147483648 || value>2147483647){
				throw "Invalid Integer FieldId " + strFieldId+",value is not Integer/IntegerArray,value="+JSON.stringify(nValues)+",maxValue=2147483647"+",minValue=-2147483648";
			}
			nValues[i]=value;
		}
		return nValues;
	};	
	
	this.regLongVal=function(strFieldId,lValues){
		this.checkField(strFieldId);
		this.checkArrVal(strFieldId,lValues);		
		var rules="^-?\\d{1,19}$";
		var exp =new RegExp(rules);
		for(var i=0;i<lValues.length;i++){
			var bFlag=exp.test(lValues[i]);
			if(!bFlag){
				throw "Invalid Long FieldId " + strFieldId+",value is not Long/LongArray,value="+JSON.stringify(lValues);
			}		
			var value=parseInt(lValues[i]);
			if(value<-9223372036854775808 || value>9223372036854775807){
				throw "Invalid Long FieldId " + strFieldId+",value is not Long/LongArray,value="+JSON.stringify(lValues)+",maxValue=9223372036854775807"+",minValue=-9223372036854775808";
			}
			lValues[i]=value;
		}
		return lValues;
	};	
	
	this.regBooleanVal=function(strFieldId,bValues){
		this.checkField(strFieldId);
		this.checkArrVal(strFieldId,bValues);	
		
		var rules="^-?\\d{1,19}$";
		var exp =new RegExp(rules);
		for(var i=0;i<bValues.length;i++){
			var objType=$.type(bValues[i]);
			var value;
			switch(objType){
				case "string":
					value=$.trim(bValues[i]).toLowerCase();
					var bFlag=exp.test(value);
					if(bFlag){
						if(value=="0"){
							value=false;
						}else{
							value=true;
						}	
					}else{
						if(value=="false" || value=='false'){
							value=false;
						}else if(value="true" || value=='true'){
							value=true;
						}else{
							throw "Invalid FieldId " + strFieldId+",value is not Boolean/BooleanArray,value="+JSON.stringify(bValues);
						}
					}					
					break;
				case "number":
					value=parseInt(bValues[i]);
					if(value==0){
						value=false;
					}else{
						value=true;
					}
					break;
				case "boolean":
					value=bValues[i];
					break;
				default:
					 throw "Invalid FieldId " + strFieldId+",value is not Boolean/BooleanArray,value="+JSON.stringify(bValues);
					break;
			}
			bValues[i]=value;
		}
		return bValues;
	};		
	this.regFloatVal=function(strFieldId,fValues){
		this.checkField(strFieldId);
		this.checkArrVal(strFieldId,fValues);	
		var rules="^-?\\d*\\.\\d+$";
		var exp = new RegExp(rules);
		for(var i=0;i<fValues.length;i++){
			var bFlag=exp.test(fValues[i]);
			if(!bFlag){
				throw "Invalid FieldId " + strFieldId+",value is not Float/FloatArray, value="+JSON.stringify(fValues);
			}	
			fValues[i]=parseFloat(fValues[i])
		}
		return fValues;
	};
	
	this.decodeHtml=function(strValue){
		var s="";
		if(strValue!=null && strValue.length>0){
			s= strValue.replace(/&amp;/g,"&");
			s = s.replace(/&lt;/g,"<");
			s = s.replace(/&gt;/g,">");
			s = s.replace(/&nbsp;/g," ");
			s = s.replace(/&#39;/g,"\'");
			s = s.replace(/&quot;/g,"\"");
			s = s.replace(/&#092;/g,"\\");
			s = s.replace(/&#47;/g,"/");
		}
		return s;		
	};	
	this.encodeHtml=function(str){
		var s = "";
		if(str!=null && str.length >0){
			s = str.replace(/&/g,"&amp;");
			s = s.replace(/</g,"&lt;");
			s = s.replace(/>/g,"&gt;");
			s = s.replace(/ /g,"&nbsp;");
			s = s.replace(/\'/g,"&#39;");
			s = s.replace(/\"/g,"&quot;");
			s = s.replace(/\r/g,"");
			s = s.replace(/\\/g,"&#092;");
			s = s.replace(/\//g,"&#47;");
		}
		return s;
	};	
	this.regDateVal=function(strFieldId,dateValues){
		this.checkField(strFieldId);
		this.checkArrVal(strFieldId,dateValues);	
		var rules="^\\d{4}-(1[0-2]|0[1-9])-([12][0-9]|3[01]|0[1-9])$";
		var dateTimeRule="^\\d{4}-(1[0-2]|0[1-9])-([12][0-9]|3[01]|0[1-9]) ([01][0-9]|[2][0-3]):[0-5][0-9]:[0-5][0-9]$";
		var exp = new RegExp(rules);
		var dateTimeExp=new RegExp(dateTimeRule);
		var retArr=new Array();
		for(var i=0;i<dateValues.length;i++){
			var bFlag=exp.test(dateValues[i]);
			if(!bFlag){
				bFlag=dateTimeExp.test(dateValues[i]);
				if(bFlag){
					retArr.push(dateValues[i].substring(0, 10));
					continue;
				}
				throw "Invalid FieldId "+ strFieldId+",value is not Date/DateArray, value="+JSON.stringify(dateValues);
			}
			retArr.push(dateValues[i]);			
		}
		return retArr;
	};	
	this.regTimeVal=function(strFieldId,timeValues){
		this.checkField(strFieldId);
		this.checkArrVal(strFieldId,timeValues);			
		var rules="^([01][0-9]|[2][0-3]):[0-5][0-9]:[0-5][0-9]$";
		var dateTimeRule="^\\d{4}-(1[0-2]|0[1-9])-([12][0-9]|3[01]|0[1-9]) ([01][0-9]|[2][0-3]):[0-5][0-9]:[0-5][0-9]$";
		var exp = new RegExp(rules);
		var dateTimeExp=new RegExp(dateTimeRule);
		var retArr=new Array();
		for(var i=0;i<timeValues.length;i++){
			var bFlag=exp.test(timeValues[i]);
			if(!bFlag){
				bFlag=dateTimeExp.test(timeValues[i]);
				if(bFlag){											
					retArr.push(timeValues[i].substring(11,19));
					continue;
				}				
				throw "Invalid FieldId " +strFieldId+",value is not Time/TimeArray, value="+JSON.stringify(timeValues);;
			}
			retArr.push(timeValues[i]);	
		}
		return retArr;
	};	
	this.regDateTimeVal=function(strFieldId,dtValues){
		this.checkField(strFieldId);
		this.checkArrVal(strFieldId,dtValues);	
		var rules="^\\d{4}-(1[0-2]|0[1-9])-([12][0-9]|3[01]|0[1-9]) ([01][0-9]|[2][0-3]):[0-5][0-9]:[0-5][0-9]$";
		var exp = new RegExp(rules);
		for(var i=0;i<dtValues.length;i++){
			var bFlag=exp.test(dtValues[i]);
			if(!bFlag){
				throw "Invalid FieldId " + strFieldId+",value is not DateTime/DateTimeArray, value="+JSON.stringify(dtValues);
			}
		}
		return dtValues;
	};
	this.checkArrVal=function(strFieldId,arrValues){
		var objType=$.type(arrValues);
		if(objType!="array"){
			throw "Invalid FieldId " + strFieldId+",value is not array";
		}	
	};
	this.checkField=function(strFieldId){	
		//var rules="^((?!\\.).)*$";
		var rules="^[a-zA-Z][0-9A-Za-z\\-_]{0,15}$"
		var exp = new RegExp(rules);
		var bFlag=exp.test(strFieldId);
		if(!bFlag){
				throw "Invalid FieldId " + strFieldId+",strFieldId rule is "+rules;
		}
	};		
	this.setByteArrayValue = function (strFieldId, bysValue) {
		try{		
			var value=this.regByteVal(strFieldId,bysValue);
			this.put(strFieldId,value);
		 }catch(err){
			console.warn("setByteArrayValue occur error,"+err);
		 }
	};
	this.setShortArrayValue = function (strFieldId, shsValue) {
		try{
			var value=this.regShortVal(strFieldId,shsValue);
			this.put(strFieldId,value);
		  }catch(err){
			console.warn("setShortArrayValue occur error,"+err);
		  }
	};
	this.setIntegerArrayValue = function (strFieldId, nsValue) {
		try{
			var value=this.regIntegerVal(strFieldId,nsValue);
			this.put(strFieldId,value);
		}catch(err){
			console.warn("setIntegerArrayValue occur error,"+err);
		}
	};
	this.setLongArrayValue = function (strFieldId, lsValue) {
		try{
			var value=this.regLongVal(strFieldId,lsValue);
			this.put(strFieldId,value);
		}catch(err){
			console.warn("setLongArrayValue occur error,"+err);
		}
	};
	this.setBooleanArrayValue=function(strFieldId,bsValue){
		try{
			var value=this.regBooleanVal(strFieldId,bsValue);
			this.put(strFieldId,value);
		}catch(err){
			console.warn("setLongArrayValue occur error,"+err);
		}	
		
	};
	this.setFloatArrayValue = function (strFieldId, fsValue) {
		try{
			var value=this.regFloatVal(strFieldId,fsValue);
			this.put(strFieldId,value);
		  }catch(err){
			console.warn("setFloatArrayValue occur error,"+err);
		 }
	};
	this.setDoubleArrayValue = function (strFieldId, dblsValue) {
		try{
			var value=this.regFloatVal(strFieldId,dblsValue);
			this.put(strFieldId,value);
		}catch(err){
			console.warn("setDoubleArrayValue occur error,"+err);
		}
	};
	this.setStringArrayValue = function (strFieldId,strsValue) {
		this.checkField(strFieldId);
		this.checkArrVal(strFieldId,strsValue);
		for(var i=0;i<strsValue.length;i++){
			var objType=$.type(strsValue[i]);
			switch(objType){
				case "boolean":
				case "number":
				case "string":
				strsValue[i]=this.decodeHtml(strsValue[i]+"");					
				break;
			  default:	
				throw "Invalid FieldId " + strFieldId+",value is not String";				
			}						
		}
		this.put(strFieldId,strsValue);
	};
	this.setDateArrayValue = function (strFieldId, strsValue) {
		try{
			var value=this.regDateVal(strFieldId,strsValue);
			this.put(strFieldId,value);
		  }catch(err){
			console.warn("setDateArrayValue occur error,"+err);
		 }
	};
	this.setTimeArrayValue = function (strFieldId, strsValue) {
		try{
			var value=this.regTimeVal(strFieldId,strsValue);
			this.put(strFieldId,value);
		 }catch(err){
			console.warn("setTimeArrayValue occur error,"+err);
		 }
	};
	this.setDateTimeArrayValue = function (strFieldId, strsValue) {
		try{
			var value=this.regDateTimeVal(strFieldId,strsValue);
			this.put(strFieldId,value);
		}catch(err){
			console.warn("setDateTimeArrayValue occur error,"+err);
		}
	};
	this.setCDOArrayValue = function (strFieldId, cdosValue) {
		var objType=$.type(cdosValue);
		if(objType!="array"){
			throw "Invalid FieldId " + strFieldId+",value is not CDOArray,value="+cdosValue;
		}	
		for(var i=0;i<cdosValue.length;i++){
			if(cdosValue[i] instanceof CDO){
				continue;
			}
			throw "Invalid FieldId " + strFieldId+",value is not CDOArray,value="+JSON.stringify(cdosValue);
		}
		this.put(strFieldId,cdosValue);
	};
	
	this.getItem=function(){
		return this.hmItem;
	};
	 
	this.toJSON=function(){
		var JSONObject={};
		this.stringifyJSON(JSONObject,this.getItem());
		return JSONObject;
	} 
	this.toString = function()
	{
		return JSON.stringify(this.toJSON());
	};
	this.stringifyJSON=function(_JSONObject,_hmItem){
		for(var key in _hmItem){
			var value=_hmItem[key];
			var objType=$.type(value);			
			if(objType=="object"){
				if(value instanceof CDO){
					var subJSON={};
					this.stringifyJSON(subJSON,value.getItem());
					
					_JSONObject[key]=subJSON;
				}else{
					_JSONObject[key]=value;
				}
			}else if(objType=="array"){
				this.stringifyJSONArray(_JSONObject,key,value);
			}else{
				_JSONObject[key]=value;
			}			
		}
	};
	this.stringifyJSONArray=function(_JSONObject,_key,_array){
		if(_array.length==0){
			_JSONObject[_key]=_array;		
		}else{
			var value=_array[0];			
			var objType=$.type(value);
			//如果是CDO数组,需要转换处理
			if(value instanceof CDO){
				var subArr=new Array();
				for(var i=0;i<_array.length;i++){
					var subJSON={};					
					this.stringifyJSON(subJSON,value.getItem());
					subArr.push(subJSON);
				}				
				_JSONObject[_key]=subArr;
			}else{
				_JSONObject[_key]=_array;
			}
		}
	};
	
};
