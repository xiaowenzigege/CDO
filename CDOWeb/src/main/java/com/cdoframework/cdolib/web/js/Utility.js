//随机得到一个0和nMaxNumber之间的整数
function rand(nMaxNumber)
{
	return Math.round(Math.random()*nMaxNumber);
}

//将一个文本串编码成XML文本串
function encodeToXMLText(strText)
{
	var strOutput="";
	var nLength=strText.length;
	for(var i=0;i<nLength;i=i+1)
	{
		var ch=strText.charAt(i);
		switch(ch)
		{
			case '&':
				strOutput+="&amp;";
				break;
			case '/':
				strOutput+="&#47;";
				break;
			case '\'':
				strOutput+="&#039;";
				break;
			case '>':
				strOutput+="&gt;";
				break;
			case '<':
				strOutput+="&lt;";
				break;
			case '\"':
				strOutput+="&quot;";
				break;
			case '\r':
				strOutput+="&#xd;";
				break;
			case '\n':
				strOutput+="&#xa;";
				break;
			default:
				strOutput+=ch;
		}
	}
	return strOutput;
}

//将一个文本串编码成HTML文本串
function encodeToHTMLText(strText)
{
	var strOutput="";
	var nLength=strText.length;
	for(var i=0;i<nLength;i=i+1)
	{
		var ch=strText.charAt(i);
		switch(ch)
		{
			case '&':
				strOutput+="&amp;";
				break;
			case '/':
				strOutput+="&#47;";
				break;
			case ' ':
				strOutput+="&nbsp;";
				break;
			case '\'':
				strOutput+="&#039;";
				break;
			case '>':
				strOutput+="&gt;";
				break;
			case '<':
				strOutput+="&lt;";
				break;
			case '\"':
				strOutput+="&quot;";
				break;
			case '\r':
				strOutput+="&#xd;";
				break;
			case '\n':
				strOutput+="&#xa;";
				break;
			default:
				strOutput+=ch;
		}
	}
	return strOutput;
}

function replaceChar(strText,chFromChar,chToChar)
{
	var strResult="";
	
	for(var i=0;i<strText.length;i=i+1)
	{
		var ch=strText.charAt(i);
		if(ch==chFromChar)
		{
			strResult+=chToChar;
		}
		else
		{
			strResult+=ch;
		}
	}
	
	return strResult;
}

function trimLeft(strText)
{
	var nIndex=0;
	for(var i=0;i<strText.length;i=i+1)
	{
		var ch=strText.charAt(i);
		if(ch==' ' || ch=='\n' || ch=='\t')
		{
			nIndex++;
		}
		else
		{
			break;
		}
	}
	
	return strText.substr(nIndex);
}

function trimRight(strText)
{
	var nIndex=strText.length;
	for(var i=strText.length-1;i>=0;i=i-1)
	{
		var ch=strText.charAt(i);
		if(ch==' ' || ch=='\n' || ch=='\t')
		{
			nIndex--;
		}
		else
		{
			break;
		}
	}
	
	return strText.substr(0,nIndex);
}

function trim(strText)
{
	var strResult=trimLeft(strText);
	strResult=trimRight(strResult);

	return strResult;
}

function getBrowserType() 
{ 
	var OsObject = ""; 

	if(navigator.userAgent.indexOf("MSIE")>=0)
	{ 
		return "MSIE"; //IE浏览器 
	} 
	if(navigator.userAgent.indexOf("Firefox")>=0)
	{ 
		return "Firefox"; //Firefox浏览器 
	} 
	if(navigator.userAgent.indexOf("Safari")>=0)
	{ 
		return "Safari"; //Safan浏览器 
	} 
	if(navigator.userAgent.indexOf("Camino")>=0)
	{ 
		return "Camino"; //Camino浏览器 
	} 
	if(navigator.userAgent.indexOf("Gecko/")>=0)
	{ 
		return "Gecko"; //Gecko浏览器 
	} 
}
//在js中 将文本内容格式化以符合HTML显示要求显示
String.prototype.FormatTextToHTML=function()
{
	return this.replace(/&gt;/g,">" ).replace(/&lt;/g,"<" ).replace(/&amp;/g,"&").replace(/&nbsp;/g," ")
	.replace(/&#47;/g,"/").replace(/&apos;/g,"'").replace(/&quot;/g,"\"").replace(/""/g,"\r");
}

//自定义统计字符数，一个中文算2个字符
String.prototype.getLength = function() {
	var arr = this.match(/[\u00FF-\uFFFF]/gi);
	if(!arr || arr == null)
		return this.length;
	var len = this.length + arr.length;
	return len;
}

//格式化文本内容(去掉换行)
function richTextInputEncoder(source)
{
	var resultStr = source;
	if (source == null)
		return source;
	resultStr = resultStr.replace(/[\r\n\t]/g,"");//去掉回车换行水平制表符
	
	return resultStr;
}
