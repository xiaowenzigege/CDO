//----------------------------------------------------------------------
//JavaScript消息服务对象
//所有页面定义一个消息客户端对象，可以向该对象发布消息，订阅消息，消息服务器对象将消息发送给订阅了该消息的对象
function MessageServer()
{
	var arrMessage	=new Array();		//存储消息
	var arrTopic	=new Array();		//存储消息标题
	var hmSubscribe	=new HashMap();		//存储订阅关系

	//订阅消息
	this.subscribe	=function(strTopic,msgClient)
	{
		var arrClient=hmSubscribe.get(strTopic);
		if(arrClient===null)
		{
			arrClient=new Array();
			hmSubscribe.put(strTopic,arrClient);
		}
		arrClient[arrClient.length]=msgClient;
	}

	//退订消息
	this.unsubscribe=function(strTopic,msgClient)
	{
		var arrClient=hmSubscribe.get(strTopic);
		if(arrClient==null)
		{
			return;
		}

		for(var i=0;i<arrClient.length;i=i+1)
		{
			if(arrClient[i]!=msgClient)
			{
				continue;
			}
			delete arrClient[i];
			arrClient.splice(i,1);

			break;
		}
	}

	//关闭客户端
	this.closeClient=function(msgClient)
	{
		var arrTopics=hmSubscribe.getKeys();
		for(var i=0;i<arrTopics.length;i+=1)
		{
			var strTopic=arrTopics[i];
			this.unsubscribe(strTopic,msgClient);
		}
	}

	//发送消息
	this.sendMessage=function(strTopic,cvMessage)
	{
		var nLength=arrTopic.length;
		arrTopic[nLength]=strTopic;
		arrMessage[nLength]=cvMessage;
	}
	var refer = this;
	
	var dtLastOnTimerTime=new Date();
	//处理和分发消息
	this.handleMessage=function()
	{
		var arrClient=hmSubscribe.get("onTimer");
		if(arrClient!=null)
		{
			var dtNow=new Date();
			if(dtNow.getTime()-dtLastOnTimerTime.getTime()>499)
			{
				refer.sendMessage("onTimer","");
				dtLastOnTimerTime=dtNow;
			}
		}

		for(var i=0;i<arrTopic.length;i=i+1)
		{
			//获取一个消息
			var strTopic=arrTopic[i];
			var cvMessage=arrMessage[i];

			//获取订阅了这个消息的消息客户端
			var arrClient=hmSubscribe.get(strTopic);
			if(arrClient==null)
			{
				delete arrTopic[i];
				arrTopic.splice(i,1);
				delete arrMessage[i];
				arrMessage.splice(i,1);
				i=i-1;

				continue;
			}
			for(var j=0;j<arrClient.length;j=j+1)
			{
				try
				{
					arrClient[j].onMessageReceived(strTopic,cvMessage);
				}
				catch(e)
				{//消息执行出错，取消该订阅
					refer.unsubscribe(strTopic,arrClient[j]);
					arrClient.splice(j,1);
					j=j-1;
				}
			}
			
			//删除消息
			delete arrTopic[i];
			arrTopic.splice(i,1);
			delete arrMessage[i];
			arrMessage.splice(i,1);
			i=i-1;
		}
	}
	
	this.start = function()
	{
		window.setInterval(this.handleMessage,100);
	}
}

//----------------------------------------------------------------------
//JavaScript消息客户端对象
function MessageClient(msgServer)
{
	var m_msgServer	=msgServer;

	//订阅消息
	this.subscribe	=function(strTopic)
	{
		m_msgServer.subscribe(strTopic,this);
	}

	//退订消息
	this.unsubscribe	=function(strTopic)
	{
		m_msgServer.unsubscribe(strTopic,this);
	}

	//发送消息
	this.sendMessage	=function(strTopic,cvMessage)
	{
		m_msgServer.sendMessage(strTopic,cvMessage);
	}

	//消息事件	
	this.onMessageReceived=function(strTopic,cvMessage)
	{
		alert(strTopic);
	}
	
	var refer=this;
	
	window.onunload=function()
	{
		m_msgServer.closeClient(refer);
	}
}
