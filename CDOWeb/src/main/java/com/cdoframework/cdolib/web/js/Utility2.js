//整数数组输出到字符串
function numberArrayToText(arrNumber,strSeperator)
{
	var strNumber="";
	for(var i=0;i<arrNumber.length;i=i+1)
	{
		if(i>0)
		{
			strNumber+=strSeperator;
		}
		strNumber+=arrNumber[i];
	}
	
	return strNumber;
}

//对一个数组中的数字进行排序
function sortNumber(arrNumber,sortMode)
{
	if(sortMode==null)
	{
		sortMode=0;
	}
	for(var i=0;i<arrNumber.length-1;i=i+1)
	{
		for(var j=i+1;j<arrNumber.length;j=j+1)
		{
			if(sortMode==0)
			{
				if(arrNumber[i]>arrNumber[j])
				{
					var nTemp=arrNumber[i];
					arrNumber[i]=arrNumber[j];
					arrNumber[j]=nTemp;
				}
			}
			else
			{
				if(arrNumber[i]<arrNumber[j])
				{
					var nTemp=arrNumber[i];
					arrNumber[i]=arrNumber[j];
					arrNumber[j]=nTemp;
				}
			}
		}
	}
}


//随机选择若干个号码
function randSelectNumber(nMinNumber,nMaxNumber,nOutputCount)
{
	//生成号码数组
	var arrNumber=new Array(nMaxNumber-nMinNumber+1);
	for(var i=nMinNumber;i<=nMaxNumber;i=i+1)
	{
		arrNumber[i-nMinNumber]=i;
	}

	var nLeftCount=nMaxNumber-nMinNumber+1;
	var arrOutput=new Array(nOutputCount);
	for(i=0;i<nOutputCount;i=i+1)
	{
		var nRandNumber=rand(nLeftCount-1);
		arrOutput[i]=arrNumber[nRandNumber];
		
		for(var j=nRandNumber+1;j<arrNumber.length;j=j+1)
		{
			arrNumber[j-1]=arrNumber[j];
		}
		nLeftCount=nLeftCount-1;
	}

	sortNumber(arrOutput);
	return arrOutput;
}


//将0102030405转换成01 02 03 04 05
function stakeToText(strStake,nNumberLength,chSeperator)
{
	var strText="";
	var nNumberCount=strStake.length/nNumberLength;
	var nIndex=0;
	for(var i=0;i<nNumberCount;i=i+1)
	{
		if(i>0)
		{
			strText+=chSeperator;
		}
		strText+=strStake.substr(nIndex,nNumberLength);
		nIndex+=nNumberLength;
	}
	
	return strText;
}

function Cab(nA,nB)
{
	var nResult=1;
	for(var i=0;i<nB;i+=1)
	{
		nResult*=(nA-i);
		nResult/=(i+1);
	}
	
	return nResult;
}

function checkEmail(strEmail)
{
	var regex=/^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;
	if(regex.test(strEmail)==true)
	{
		return true;
	}
	else
	{
		return false;
	}
}


function checkMobilePhone(strPhone)
{
	var regex=/(13|15)\d{9}/;
	if(regex.test(strPhone)==true)
	{
		return true;
	}
	else
	{
		return false;
	}
}

function integerToMoney(nMoney)
{
	var strYuan	=nMoney/100;
	var nCent	=nMoney%100;
	
	if(nCent<10)
	{
		return ""+strYuan+".0"+nCent
	}
	else
	{
		return ""+strYuan+"."+nCent
	}
}
