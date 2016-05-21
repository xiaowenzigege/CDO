function HttpClient(strServletURL)
{
	this.strServletURL	=strServletURL;
	
	this.SERVICENAME_KEY = "strServiceName";
	this.TRANSNAME_KEY   = "strTransName";
	
	//getURL
	this.getServletURL	=function()
	{
		return this.strServletURL;
	}

	var getCDONode=function(httpRequest)
	{
		var domXML=httpRequest.responseXML;
		return domXML.documentElement;
	}

	var thisPointer=this;

	var componentToRequest=function(component,nameRegex,cdoRequest)
	{
		//验证组件名称是否合法
		if(nameRegex.test(component.name)==false)
		{//不合法
			throw "Invalid component's name";
			return -1;
		}

		//组件名称合法
		var strDataType=component.cdodatatype;
		if(typeof(strDataType)=="undefined")
		{//组件没有定义jcpdatatype，丢弃
			return -1;
		}

		//检验输入值
		var strDataRegex=component.cdodataregex;
		if(typeof(strDataRegex)!="undefined")
		{//需要校验
			if(strDataRegex.test(component.value)==false)
			{//不合法
				if(onVerifyFailed!=null)
				{
					onVerifyFailed(component);
					return -1;
				}
			}
		}
		
		//定义了datatype
		switch(strDataType.toLowerCase())
		{
			case "byte":
				cdoRequest.setByteValue(component.name,component.value);
				break;
			case "short":
				cdoRequest.setShortValue(component.name,component.value);
				break;
			case "integer":
				cdoRequest.setIntegerValue(component.name,component.value);
				break;
			case "long":
				cdoRequest.setLongValue(component.name,component.value);
				break;
			case "float":
				cdoRequest.setFloatValue(component.name,component.value);
				break;
			case "double":
				cdoRequest.setDoubleValue(component.name,component.value);
				break;
			case "string":
				cdoRequest.setStringValue(component.name,component.value);
				break;
			case "date":
				cdoRequest.setDateValue(component.name,component.value);
				break;
			case "time":
				cdoRequest.setTimeValue(component.name,component.value);
				break;
			case "datetime":
				cdoRequest.setDateTimeValue(component.name,component.value);
				break;
			default:
				break;
		}
		
		return 0;
	}
	
	//将Form的输入转换成Request
	var formToRequest=function(form,cdoRequest)
	{
		var components = form.elements;

		var nameRegex = /([a-z]|[A-Z])([a-z]|[A-Z]|[0-9])*/;
		for(var i=0;i<components.length;i=i+1)
		{
			var strTagName=components[i].tagName.toLowerCase();
			if(strTagName=="input")
			{
				switch(components[i].type)
				{
					case "checkbox":
					case "radio":
						if(components[i].checked==true)
						{
							if(componentToRequest(components[i],nameRegex,cdoRequest)==-1)
							{
								return -1;
							}
						}
						break;
					case "hidden":
					case "password":
					case "text":
						if(componentToRequest(components[i],nameRegex,cdoRequest)==-1)
						{
							return -1;
						}
						break;
					case "file":
						break;
				}
				continue;
			}
			else if(strTagName=="button" || strTagName=="select" || strTagName=="textarea")
			{
				if(componentToRequest(components[i],nameRegex,cdoRequest)==-1)
				{
					return -1;
				}
			}
		}
		return 0;
	}

	//执行Form提交的事务请求
	this.submit = function(form,strTransName,bAMode)
	{
		// bAMode: true  //采用Ajax异步调用
		// bAMode: false //采用Ajax同步调用
		if(bAMode==null)
		{
			bAMode=false;
		}

		//创建Request
		var cdoRequest=new CDO();
		if(formToRequest(form,cdoRequest)!=0)
		{
			return;
		}

		//创建并初始化HttpClient
		var httpRequest=null;
		if(window.XMLHttpRequest)
		{ 
		    httpRequest = new XMLHttpRequest();
		}
		else if(window.ActiveXObject)
		{
		    httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
		}

		var cdoResponse=null;
		var onHttpStateChanged=function()
		{
			if(httpRequest.readyState!=4)
			{
				return;
			}
			if(httpRequest.status==200)
			{
				var node	=getCDONode(httpRequest);
				cdoResponse=new CDO();
				cdoResponse.fromXML(node);
				if(thisPointer.onRequestOK != null)
				{
					thisPointer.onRequestOK(strTransName,cdoResponse.getCDOValue("cdoResponse"),cdoResponse.getCDOValue("cdoReturn"));
				}
			}
			else
			{
				if(thisPointer.onRequestFailed != null)
				{
					thisPointer.onRequestFailed(strTransName,httpRequest.status,httpRequest.statusText);
				}
				else
				{
					//默认的处理请求失败的方法
					var strHTML = "<DIV style=\"font-size:20pt;background-color:slategray;\">HTTP Status ";
					strHTML += httpRequest.status;
					strHTML += " - ";
					strHTML += httpRequest.statusText;
					strHTML += "</DIV>";
					document.body.innerHTML = strHTML;
				}
			}
			httpRequest=null;
		}

		if(getBrowserType()=="Firefox")
		{
			httpRequest.onreadystatechange = onHttpStateChanged();
		}
		else
		{
			httpRequest.onreadystatechange = onHttpStateChanged;
		}
		
		//发送HTTP请求
		var strParameter="strTransName="+strTransName;
		try
		{
			httpRequest.open('POST',encodeURI(this.strServletURL+"?"+strParameter),bAMode);
			httpRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			httpRequest.send("$$CDORequest$$="+encodeURIComponent(cdoRequest.toXML()));
			//alert(cdoRequest.toXML());
			if(getBrowserType()=="Firefox")
			{
				httpRequest.onreadystatechange = onHttpStateChanged();
			}
			else
			{
				httpRequest.onreadystatechange = onHttpStateChanged;
			}
		}
		catch(e)
		{
			return null;
		}
	}

	//加载CDO XML文件,返回CDO对象
	this.loadXML=function(strURL)
	{
		var httpRequest=null;
		if(window.XMLHttpRequest)
		{ 
		    httpRequest	=new XMLHttpRequest();
		}
		else if(window.ActiveXObject)
		{
		    httpRequest	=new ActiveXObject("Microsoft.XMLHTTP");
		}

		//发送HTTP请求
		try
		{
			httpRequest.open('GET',strURL,false);
			httpRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			httpRequest.send(null);
		}
		catch(e)
		{
			return null;
		}

		var cdoResponse=null;

		if(httpRequest.status==200)
		{
			var node	=getCDONode(httpRequest);
			cdoResponse=new CDO();
			cdoResponse.fromXML(node);
		}

		return cdoResponse;
	}


	//执行事务
	this.handleTrans=function(strTransName,cdoRequest,bAMode,reqCache)
	{    
		// bAMode: true  //采用Ajax异步调用
		// bAMode: false //采用Ajax同步调用
		if(bAMode==null)
		{
			bAMode=false;
		}

		var httpRequest=null;
		if(window.XMLHttpRequest)
		{ 
		    httpRequest	=new XMLHttpRequest();
		}
		else if(window.ActiveXObject)
		{
		    httpRequest	=new ActiveXObject("Microsoft.XMLHTTP");
		}

		var cdoResponse=null;
		var onHttpStateChanged=function()
		{
			if(httpRequest.readyState!=4)
			{
				return;
			}
			if(httpRequest.status==200)
			{
				var node	=getCDONode(httpRequest);
				cdoResponse=new CDO();
				cdoResponse.fromXML(node);
				if(thisPointer.onRequestOK != null)
				{
					thisPointer.onRequestOK(strTransName,cdoResponse.getCDOValue("cdoResponse"),cdoResponse.getCDOValue("cdoReturn"));
				}
			}
			else
			{
				if(thisPointer.onRequestFailed != null)
				{
					thisPointer.onRequestFailed(strTransName,httpRequest.status,httpRequest.statusText);
				}
				else
				{
					//默认的处理请求失败的方法
					var strHTML = "<DIV style=\"font-size:20pt;background-color:slategray;\">HTTP Status ";
					strHTML += httpRequest.status;
					strHTML += " - ";
					strHTML += httpRequest.statusText;
					strHTML += "</DIV>";
					document.body.innerHTML = strHTML;
				}
			}
			httpRequest=null;
		}
		if(getBrowserType()=="Firefox")
		{
			httpRequest.onreadystatechange = onHttpStateChanged();
		}
		else
		{
			httpRequest.onreadystatechange = onHttpStateChanged;
		}

		//发送HTTP请求
		var strParameter="strTransName="+strTransName;
		if(reqCache){
			strParameter+="&reqCache="+reqCache;
		}
		try
		{
			var strRequest = cdoRequest.toXML();
			var str_URL = encodeURI(this.strServletURL+"?"+strParameter) + "&$$CDORequest$$=" + encodeURIComponent(strRequest);
			//CDORequest生成的URL的串的长度,如果大于1KB,则走POST,否则走GET(URL最大长度为2048字符(2KB))
			if(str_URL.getLength() > 1024)
			{
				httpRequest.open('POST',encodeURI(this.strServletURL+"?"+strParameter),bAMode);
				httpRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
				httpRequest.send("$$CDORequest$$="+encodeURIComponent(strRequest));
			}
			else
			{
				httpRequest.open('GET',str_URL,bAMode);
				httpRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
				httpRequest.send(null);
			}
			
			if(getBrowserType()=="Firefox")
			{
				if(bAMode)
				{
					httpRequest.onreadystatechange = function()
					{
						onHttpStateChanged();
					}
				}
				else
				{
					httpRequest.onreadystatechange = onHttpStateChanged();
				}
			}
			else
			{
				httpRequest.onreadystatechange = onHttpStateChanged;
			}
		}
		catch(e)
		{
			return null;
		}

	}

	//定义事件
	this.onRequestFailed=null;	//function(strTransName,nStatus,strStatusText)
	this.onRequestOK=null;		//function(strTransName,cdoResponse,cdoReturn)
	this.onVerifyFailed=null;
}

