document.write("<div id='divMultiChoiceTree' style='position: absolute; z-index: 999999; background-color:#FFFFFF ;overflow:auto;width: 200; height: 200; display: none'>");
document.write("<table border=\"1\" cellspacing=\"0\">");
document.write("<tr>");
document.write("<td>");

document.write("<iframe name='iframeMultiChoiceTree' scrolling='yes' frameborder='0' width='100%' height='260'></iframe>");

document.write("</td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td>");
document.write("<table id=\"tableMultiChoiceTree\" width=\"100%\">");
document.write("<tr>");
document.write("<td id=\"tdClear\" align=\"center\"><input id=\"inputClear\" type=\"button\" value=\"清除\" onclick=\"varMultiChoiceTree.onClear()\"/></td>");
document.write("<td id=\"tdOK\" align=\"center\"><input id=\"inputOK\" type=\"button\" value=\"确定\" onclick=\"varMultiChoiceTree.onOK()\"/></td>");
document.write("<td id=\"tdClose\" align=\"center\"><input id=\"inputClose\" type=\"button\" value=\"关闭\" onclick=\"varMultiChoiceTree.onClose()\"/></td>");
document.write("</tr>");
document.write("</table>");
document.write("</td>");
document.write("</tr>");
document.write("</table>");
document.write("</div>");

var varMultiChoiceTree=null;
var strIconPath="Icon/";

function MultiChoiceTree(data)
{
	var selectedNodeIds=null;
	this.getSelectedNodeIds=function()
	{
		return selectedNodeIds;
	}
	this.setSelectedNodeIds=function(ids)
	{
		selectedNodeIds=ids;
	}

	var selectedNodeTitles=null;
	this.getSelectedNodeTitles=function()
	{
		return selectedNodeTitles;
	}

	this.data=data;

	var selectedElements=null;
	
	this.show=function(bForLeafNode,nWidth,nHeight)
	{
		var varWidth=200;
		var varHeight=200;
		if(nWidth!=null)
		{
			varWidth=nWidth;
		}
		if(nHeight!=null)
		{
			varHeight=nHeight;
		}
		document.getElementById("divMultiChoiceTree").style.width=varWidth;
		document.getElementById("divMultiChoiceTree").style.height=varHeight;
		document.getElementById("iframeMultiChoiceTree").style.height=varHeight-40;

		varMultiChoiceTree=this;
	
		if(bForLeafNode==null)
		{
			bForLeafNode=false;
		}

		var e = window.event.srcElement;
		selectedElements=null;

		//生成树
		var strHTML="";
		
		strHTML+="<html>";
		strHTML+="<head><meta http-equiv='Content-Type' content='text/html; charset=GBK'></head>";
		strHTML+="<body onkeydown='return false' onselectstart='return false' style='margin: 0px' oncontextmenu='return false'>";
		strHTML+=getTreeHTML(this.data,1,0,selectedNodeIds,bForLeafNode,"");
		strHTML+="</body>";
		strHTML+="</html>";
		iframeMultiChoiceTree.document.writeln(strHTML);
		iframeMultiChoiceTree.document.close();

		selectedElements=new Array();

		if(selectedNodeIds!=null && selectedNodeIds.length>0)
		{//设置选中节点
			with(iframeMultiChoiceTree)
			{
				var tds=document.childNodes(0).childNodes(1).childNodes(0).all;
				for(var i=0;i<tds.length;i++)
				{
					if(tds(i).tagName.toLowerCase()!="td")
					{
						continue;
					}
					if(tds(i).value==null)
					{
						continue;
					}
					if(isIdSelected(tds(i).value,selectedNodeIds)==true)
					{
						selectedElements.push(tds(i).previousSibling.childNodes(0));
					}
				}
				for(var i=0;i<selectedElements.length;i++)
				{//依次打开父节点
					var trNode=selectedElements[i].parentNode.parentNode;
					while(trNode.parentNode.parentNode.parentNode.tagName.toLowerCase()!="body")
					{
						trNode.parentNode.parentNode.parentNode.parentNode.style.childflag=2;
						trNode.parentNode.parentNode.parentNode.parentNode.style.display="block";
						trNode.parentNode.parentNode.parentNode.parentNode.previousSibling.children(0).children(0).src=strIconPath+"OpenCross.png";

						trNode=trNode.parentNode.parentNode.parentNode.parentNode;
					}
				}
			}
		}

		with(iframeMultiChoiceTree)
		{
			for(var i=0;i<selectedElements.length;i++)
			{//依次打开父节点
				var trNode=selectedElements[i].parentNode.parentNode;
				while(trNode.parentNode.parentNode.parentNode.tagName.toLowerCase()!="body")
				{
					trNode.parentNode.parentNode.parentNode.parentNode.style.childflag=2;
					trNode.parentNode.parentNode.parentNode.parentNode.style.display="block";
					trNode.parentNode.parentNode.parentNode.parentNode.previousSibling.children(0).children(0).src=strIconPath+"OpenCross.png";

					trNode=trNode.parentNode.parentNode.parentNode.parentNode;
				}
			}
		}
	
		//显示树
		var nDivX=getAbsLeft(e);
		var nDivY=0;
		var nAbsTop=getAbsTop(e);
		nDivY=nAbsTop+e.offsetHeight;
		if(nAbsTop>=varHeight && nDivY+varHeight>document.body.offsetHeight)
		{
			nDivY=nAbsTop-varHeight;
		}
	
		divMultiChoiceTree.style.posLeft=nDivX;
		divMultiChoiceTree.style.posTop=nDivY;
		divMultiChoiceTree.style.display="block";
		iframeMultiChoiceTree.focus();
	}

	//根据Id查询对应节点的文本
	this.getNodeTitle=function(nodeId,treeNode)
	{
		if(treeNode==null)
		{
			treeNode=this.data;
		}
		
		if(treeNode.constructor!=window.Array)
		{
			if(treeNode.id==nodeId)
			{
				return treeNode.name;
			}
	
			var nLength=0;
			if(treeNode.children!=null)
			{
				nLength=treeNode.children.length;
			}
			if(nLength==0)
			{
				return null;
			}
			for(var i=0;i<nLength;i++)
			{
				var valueTemp=getNodeTitle(nodeId,treeNode.children[i]);
				if(valueTemp!=null)
				{
					return valueTemp;
				}
			}
		}
		else
		{
			var nLength=treeNode.length;
	
			for(var i=0;i<nLength;i++)
			{
				var valueTemp=this.getNodeTitle(nodeId,treeNode[i]);
				if(valueTemp!=null)
				{
					return valueTemp;
				}
			}
		}
	
		return null;
	}

	this.onOpenClose=function()
	{
		var e = iframeMultiChoiceTree.event.srcElement;
		if(e.childflag=="0")
		{//无子结点
		}
		else if(e.childflag=="1")
		{//子结点当前隐藏
			e.parentNode.parentNode.nextSibling.style.display="block";
			if(e.lastchildflag==0)
			{
				e.src=strIconPath+"OpenCross.png";
			}
			else
			{
				e.src=strIconPath+"LOpenCross.png";
			}
			e.childflag="2";
		}
		else
		{//子结点当前显示
			e.parentNode.parentNode.nextSibling.style.display="none";
			if(e.lastchildflag==0)
			{
				e.src=strIconPath+"CloseCross.png";
			}
			else
			{
				e.src=strIconPath+"LCloseCross.png";
			}
			e.childflag="1";
		}
	}
	
	this.onSelect=function()
	{
		var e = iframeMultiChoiceTree.event.srcElement;
		
		if(e.tagName.toLowerCase()=="nobr")
		{
			e=e.parentNode.previousSibling.childNodes(0);
		}
		else if(e.tagName.toLowerCase()=="td")
		{
			e=e.previousSibling.childNodes(0);
		}
		else if(e.tagName.toLowerCase()=="img")
		{//e不用修改
		}
		
		if(e.selectflag=="1")
		{//当前节点未选中，选中它
			e.src=strIconPath+"Selected.gif";
			e.selectflag="2";
			e.parentNode.nextSibling.style.color="#FF0000";

			selectedElements.push(e);
		}
		else
		{//当前已经选中，清除它
			for(var i=0;i<selectedElements.length;i++)
			{
				if(selectedElements[i]==e)
				{
					e.src=strIconPath+"Unselected.gif";
					e.selectflag="1";
					e.parentNode.nextSibling.style.color="#000000";

					selectedElements.splice(i,1);
					break;
				}
			}
		}
	}
	
	this.onClear=function()
	{
		if(selectedElements.length==0)
		{
			return;
		}
		
		for(var i=0;i<selectedElements.length;i++)
		{
			selectedElements[i].src=strIconPath+"Unselected.gif";
			selectedElements[i].selectflag="1";
			selectedElements[i].parentNode.nextSibling.style.color="#000000";
		}
		selectedElements.splice(0,selectedElements.length);
	}
	
	this.onOK=function()
	{
		selectedNodeIds=new Array();
		selectedNodeTitles=new Array();
		selectedNodePaths=new Array();

		for(var i=0;i<selectedElements.length;i++)
		{
			selectedNodeIds[i]=selectedElements[i].parentNode.nextSibling.value;
			selectedNodeTitles[i]=selectedElements[i].parentNode.nextSibling.childNodes(0).innerText;
			selectedNodePaths[i]=selectedElements[i].parentNode.nextSibling.childNodes(0).path;
		}
		
		divMultiChoiceTree.style.display="none";
		
		if(this.onSelected!=null)
		{
			this.onSelected(selectedNodeIds,selectedNodeTitles,selectedNodePaths);
		}
	}
	
	this.onSelected=null;
	
	this.onClose=function()
	{
		divMultiChoiceTree.style.display="none";
	}

	function getAbsLeft(obj)
	{
		if(obj.offsetParent==null)
		{
			return obj.offsetLeft;
		}
		else
		{
			var leftParent=getAbsLeft(obj.offsetParent);
			return obj.offsetLeft+leftParent;
		}
	}
	
	function getAbsTop(obj)
	{
		if(obj.offsetParent==null)
		{
			return obj.offsetTop;
		}
		else
		{
			var topParent=getAbsTop(obj.offsetParent);
			return obj.offsetTop+topParent;
		}
	}
	
	//检查Id是否选中
	function isIdSelected(strId,selectedNodeIds)
	{
		if(selectedNodeIds==null)
		{
			return false;
		}
		for(var i=0;i<selectedNodeIds.length;i++)
		{
			if(selectedNodeIds[i]==strId)
			{
				return true;
			}
		}
		
		return false;
	}

	function getTreeHTML(treeNode,nSiblingCount,nIndex,selectedNodeIds,bForLeafNode,strPath)
	{
		if(strPath==null || strPath=="undefined" || strPath=="")
		{
			strPath="";
		}
		else
		{
			strPath+=">";
		}

		var strHTML="";
		strHTML+="<table style=\"font-size:9pt;padding:0;\" cellpadding=\"0\" cellspacing=\"0\">";
	
		strHTML+=getTreeNodeHTML(treeNode,nSiblingCount,nIndex,selectedNodeIds,bForLeafNode,strPath);
	
		strHTML+="</table>";

		return strHTML;
	}

	function getTreeNodeHTML(treeNode,nSiblingCount,nIndex,selectedNodeIds,bForLeafNode,strPath)
	{
		var strHTML="";

		if(treeNode.constructor!=window.Array)
		{//非数组
			if(treeNode.children==null || treeNode.children.length==0)
			{//叶子节点
				strHTML+="<tr>";

				if(nIndex+1<nSiblingCount)
				{
					strHTML+="<td><img src=\""+strIconPath+"TConnector.png\" onclick=\"parent.varMultiChoiceTree.onOpenClose()\" childflag=\"0\"></td>";
				}
				else
				{
					strHTML+="<td><img src=\""+strIconPath+"LConnector.png\" onclick=\"parent.varMultiChoiceTree.onOpenClose()\" childflag=\"0\"></td>";
				}
				
				if(isIdSelected(treeNode.id,selectedNodeIds)==false)
				{//未选中
					strHTML+="<td style=\"cursor:hand\"><img src=\""+strIconPath+"Unselected.gif\" onclick=\"parent.varMultiChoiceTree.onSelect()\" selectflag=\"1\"></td>";
					strHTML+="<td style=\"cursor:hand\" value=\""+treeNode.id+"\" onclick=\"parent.varMultiChoiceTree.onSelect()\"><nobr path=\""+strPath+treeNode.name+"\">"+treeNode.name+"</nobr></td>";
				}
				else
				{//选中
					strHTML+="<td style=\"cursor:hand\"><img src=\""+strIconPath+"Selected.gif\" onclick=\"parent.varMultiChoiceTree.onSelect()\" selectflag=\"2\"></td>";
					strHTML+="<td style=\"cursor:hand;color:#FF0000\" value=\""+treeNode.id+"\" onclick=\"parent.varMultiChoiceTree.onSelect()\"><nobr path=\""+strPath+treeNode.name+"\">"+treeNode.name+"</nobr></td>";
				}
				
				strHTML+="</tr>";

				return strHTML;
			}
			else
			{//非叶子节点
				strHTML+="<tr>";
		
				if(nIndex+1<nSiblingCount)
				{
					strHTML+="<td><img src=\""+strIconPath+"CloseCross.png\" onclick=\"parent.varMultiChoiceTree.onOpenClose()\" childflag=\"1\" lastchildflag=\"0\"></td>";
				}
				else
				{
					strHTML+="<td><img src=\""+strIconPath+"CloseCross.png\" onclick=\"parent.varMultiChoiceTree.onOpenClose()\" childflag=\"1\" lastchildflag=\"1\"></td>";
				}
	
				if(bForLeafNode==false)
				{//所有节点都能选
					if(isIdSelected(treeNode.id,selectedNodeIds)==false)
					{//未选中
						strHTML+="<td style=\"cursor:hand\"><img src=\""+strIconPath+"Unselected.gif\" onclick=\"parent.varMultiChoiceTree.onSelect()\" selectflag=\"1\"></td>";
						strHTML+="<td style=\"cursor:hand\" value=\""+treeNode.id+"\" onclick=\"parent.varMultiChoiceTree.onSelect()\"><nobr path=\""+strPath+treeNode.name+"\">"+treeNode.name+"</nobr></td>";
					}
					else
					{//选中
						strHTML+="<td style=\"cursor:hand\"><img src=\""+strIconPath+"Selected.gif\" onclick=\"parent.varMultiChoiceTree.onSelect()\" selectflag=\"2\"></td>";
						strHTML+="<td style=\"cursor:hand;color:#FF0000\" value=\""+treeNode.id+"\" onclick=\"parent.varMultiChoiceTree.onSelect()\"><nobr path=\""+strPath+treeNode.name+"\">"+treeNode.name+"</nobr></td>";
					}
				}
				else
				{//只能选择叶子节点
					strHTML+="<td><img src=\""+strIconPath+"Connector.png\"></td>";
					strHTML+="<td><nobr path=\""+strPath+treeNode.name+"\">"+treeNode.name+"</nobr></td>";
				}
				strHTML+="</tr>";

				strHTML+="<tr style='display:none'>";
				if(nIndex+1<nSiblingCount)
				{
					strHTML+="<td style=\"background-image:url('"+strIconPath+"IConnector.png')\"></td>";
				}
				else
				{
					strHTML+="<td></td>";
				}
	
				strHTML+="<td></td>";
				strHTML+="<td>";

				for(var i=0;i<treeNode.children.length;i++)
				{
					var node=treeNode.children[i];

					strHTML+=getTreeHTML(treeNode.children[i],treeNode.children.length,i,selectedNodeIds,bForLeafNode,strPath+treeNode.name);
				}
				strHTML+="</td>";
				strHTML+="</tr>";
			}
		}
		else
		{//数组
			for(var i=0;i<treeNode.length;i++)
			{
				strHTML+=getTreeNodeHTML(treeNode[i],treeNode.length,i,selectedNodeIds,bForLeafNode,strPath);
			}
		}
		
		
		return strHTML;
	}
}
