


document.write("<div id='divSingleChoiceTree' style='position: absolute; z-index: 999999; background-color:#FFFFFF ;overflow:auto;width: 300; height: 300; display: none'>");
document.write("<table border=\"1\" cellspacing=\"0\">");
document.write("<tr>");
document.write("<td>");

document.write("<iframe name='iframeSingleChoiceTree' scrolling='yes' frameborder='0' width='100%' height='260'></iframe>");

document.write("</td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td>");
document.write("<table id=\"tableSingleChoiceTree\" width=\"100%\">");
document.write("<tr>");
document.write("<td id=\"tdClear\" align=\"center\"><input id=\"inputClear\" type=\"button\" value=\"清除\" onclick=\"varSingleChoiceTree.onClear()\"/></td>");
document.write("<td id=\"tdOK\" align=\"center\"><input id=\"inputOK\" type=\"button\" value=\"确定\" onclick=\"varSingleChoiceTree.onOK()\"/></td>");
document.write("<td id=\"tdClose\" align=\"center\"><input id=\"inputClose\" type=\"button\" value=\"关闭\" onclick=\"varSingleChoiceTree.onClose()\"/></td>");
document.write("</tr>");
document.write("</table>");
document.write("</td>");
document.write("</tr>");
document.write("</table>");
document.write("</div>");


var varSingleChoiceTree=null;
var strIconPath="Icon/";

function SingleChoiceTree(data)
{
	var selectedNodeId="";
	this.getSelectedNodeId=function()
	{
		return selectedNodeId;
	}

	var selectedNodeTitle="";
	this.getSelectedNodeTitle=function()
	{
		return selectedNodeTitle;
	}

	this.data=data;

	var selectedElement=null;
	
	this.show=function(bForLeafNode)
	{
		varSingleChoiceTree=this;
	
		if(bForLeafNode==null)
		{
			bForLeafNode=false;
		}
	
		var e = window.event.srcElement;
	
		selectedElement=null;
	
		//生成树
		var strHTML="";
		
		strHTML+="<html>";
		strHTML+="<head><meta http-equiv='Content-Type' content='text/html; charset=GBK'></head>";
		strHTML+="<body onkeydown='return false' onselectstart='return false' style='margin: 0px' oncontextmenu='return false'>";
		strHTML+=getTreeHTML(this.data,1,0,selectedNodeId,bForLeafNode);
		strHTML+="</body>";
		strHTML+="</html>";
		iframeSingleChoiceTree.document.writeln(strHTML);
		iframeSingleChoiceTree.document.close();

		if(selectedNodeId!="")
		{//设置选中节点
			with(iframeSingleChoiceTree)
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
					if(tds(i).value==selectedNodeId)
					{
						selectedElement=tds(i).previousSibling.childNodes(0);
						break;
					}
				}
				if(selectedElement!=null)
				{//依次打开父节点
					var trNode=selectedElement.parentNode.parentNode;
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
	
		//显示树
	
		var nDivX=getAbsLeft(e);
		var nDivY=0;
		var nAbsTop=getAbsTop(e);
		nDivY=nAbsTop+e.offsetHeight;
		if(nAbsTop>=300 && nDivY+300>document.body.offsetHeight)
		{
			nDivY=nAbsTop-300;
		}
	
		divSingleChoiceTree.style.posLeft=nDivX;
		divSingleChoiceTree.style.posTop=nDivY;
		divSingleChoiceTree.style.display="block";
		iframeSingleChoiceTree.focus();
	}

	//根据Id查询对应节点的文本

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
		var e = iframeSingleChoiceTree.event.srcElement;
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
		var e = iframeSingleChoiceTree.event.srcElement;
		
		if(e.tagName.toLowerCase()=="nobr")
		{
			e=e.parentNode.previousSibling.childNodes(0);
		}
		else if(e.tagName.toLowerCase()=="td")
		{
			e=e.previousSibling.childNodes(0);
		}
		else if(e.tagName.toLowerCase()=="img")
		{
	//		e=e.parentNode.previousSibling.childNodes(0);
		}
		
		if(e.selectflag=="0")
		{
		}
		if(e.selectflag=="1")
		{
			if(selectedElement!=null)
			{
				selectedElement.src=strIconPath+"Unselected.gif";
				selectedElement.selectflag="1";
				selectedElement.parentNode.nextSibling.style.color="#000000";
			}
			e.src=strIconPath+"Selected.gif";
			e.selectflag="2";
			e.parentNode.nextSibling.style.color="#FF0000";
			selectedElement=e;
		}
		else
		{
		}
	}
	
	this.onClear=function()
	{
		if(selectedElement==null)
		{
			return;
		}
		
		selectedElement.src=strIconPath+"Unselected.gif";
		selectedElement.selectflag="1";
		selectedElement.parentNode.nextSibling.style.color="#000000";
		
		selectedElement=null;
	}
	
	this.onOK=function()
	{
		if(selectedElement==null)
		{
			selectedNodeId="";
			selectedNodeTitle="";
			selectedNodePath="";
		}
		else
		{
			selectedNodeTitle=selectedElement.parentNode.nextSibling.childNodes(0).innerText;
			selectedNodeId=selectedElement.parentNode.nextSibling.value;
			selectedNodePath=selectedElement.parentNode.nextSibling.childNodes(0).path;
	
			selectedElement.src=strIconPath+"Unselected.gif";
			selectedElement.selectflag="1";
			selectedElement.parentNode.nextSibling.style.color="#000000";
		}

		divSingleChoiceTree.style.display="none";
		
		if(this.onSelected!=null)
		{
			this.onSelected(selectedNodeId,selectedNodeTitle,selectedNodePath);
		}
	}
	
	this.onSelected=null;
	
	this.onClose=function()
	{
		divSingleChoiceTree.style.display="none";
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
	
	function getTreeHTML(treeNode,nSiblingCount,nIndex,strInputValue,bForLeafNode,strPath)
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
	
		strHTML+=getTreeNodeHTML(treeNode,nSiblingCount,nIndex,strInputValue,bForLeafNode,strPath);
	
		strHTML+="</table>";
		
		return strHTML;
	}
	
	function getTreeNodeHTML(treeNode,nSiblingCount,nIndex,strInputValue,bForLeafNode,strPath)
	{
		var strHTML="";

		if(treeNode.constructor!=window.Array)
		{//非数组
			if(treeNode.children==null || treeNode.children.length==0)
			{//叶子节点
				strHTML+="<tr>";
				
				if(nIndex+1<nSiblingCount)
				{
					strHTML+="<td><img src=\""+strIconPath+"TConnector.png\" onclick=\"parent.varSingleChoiceTree.onOpenClose()\" childflag=\"0\"></td>";
				}
				else
				{
					strHTML+="<td><img src=\""+strIconPath+"LConnector.png\" onclick=\"parent.varSingleChoiceTree.onOpenClose()\" childflag=\"0\"></td>";
				}
				
				if(strInputValue!=treeNode.id)
				{//未选中
					strHTML+="<td style=\"cursor:hand\"><img src=\""+strIconPath+"Unselected.gif\" onclick=\"parent.varSingleChoiceTree.onSelect()\" selectflag=\"1\"></td>";
					strHTML+="<td style=\"cursor:hand\" value=\""+treeNode.id+"\" onclick=\"parent.varSingleChoiceTree.onSelect()\"><nobr path=\""+strPath+treeNode.name+"\">"+treeNode.name+"</nobr></td>";
				}
				else
				{//选中
					strHTML+="<td style=\"cursor:hand\"><img src=\""+strIconPath+"Selected.gif\" onclick=\"parent.varSingleChoiceTree.onSelect()\" selectflag=\"2\"></td>";
					strHTML+="<td style=\"cursor:hand;color:#FF0000\" value=\""+treeNode.id+"\" onclick=\"parent.varSingleChoiceTree.onSelect()\"><nobr path=\""+strPath+treeNode.name+"\">"+treeNode.name+"</nobr></td>";
				}
				
				strHTML+="</tr>";

				return strHTML;
			}
			else
			{//非叶子节点
				strHTML+="<tr>";
		
				if(nIndex+1<nSiblingCount)
				{
					strHTML+="<td><img src=\""+strIconPath+"CloseCross.png\" onclick=\"parent.varSingleChoiceTree.onOpenClose()\" childflag=\"1\" lastchildflag=\"0\"></td>";
				}
				else
				{
					strHTML+="<td><img src=\""+strIconPath+"CloseCross.png\" onclick=\"parent.varSingleChoiceTree.onOpenClose()\" childflag=\"1\" lastchildflag=\"1\"></td>";
				}
	
				if(bForLeafNode==false)
				{//所有节点都能选
					if(strInputValue!=treeNode.id)
					{//未选中
						strHTML+="<td style=\"cursor:hand\"><img src=\""+strIconPath+"Unselected.gif\" onclick=\"parent.varSingleChoiceTree.onSelect()\" selectflag=\"1\"></td>";
						strHTML+="<td style=\"cursor:hand\" value=\""+treeNode.id+"\" onclick=\"parent.varSingleChoiceTree.onSelect()\"><nobr path=\""+strPath+treeNode.name+"\">"+treeNode.name+"</nobr></td>";
					}
					else
					{//选中
						strHTML+="<td style=\"cursor:hand\"><img src=\""+strIconPath+"Selected.gif\" onclick=\"parent.varSingleChoiceTree.onSelect()\" selectflag=\"2\"></td>";
						strHTML+="<td style=\"cursor:hand;color:#FF0000\" value=\""+treeNode.id+"\" onclick=\"parent.varSingleChoiceTree.onSelect()\"><nobr path=\""+strPath+treeNode.name+"\">"+treeNode.name+"</nobr></td>";
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
					
					strHTML+=getTreeHTML(treeNode.children[i],treeNode.children.length,i,strInputValue,bForLeafNode,strPath+treeNode.name);
				}
				strHTML+="</td>";
				strHTML+="</tr>";
			}
		}
		else
		{//数组
			for(var i=0;i<treeNode.length;i++)
			{
				strHTML+=getTreeNodeHTML(treeNode[i],treeNode.length,i,strInputValue,bForLeafNode,strPath);
			}
		}
		
		
		return strHTML;
	}
}
