package com.cdoframework.cdolib.data.cdo;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nanoxml.XMLElement;

import com.cdoframework.cdolib.data.cdo.CDO;

/**
 * xml2CDO 
 * @author KenelLiu
 *
 */
public class ParseXmlCDO {
	
	
	protected static void xml2CDO(CDO cdo,XMLElement nodeCDO,boolean isRootNode)
	{
		Iterator enumNodes=nodeCDO.enumerateChildren();

		while(enumNodes.hasNext())
		{
			XMLElement node=(XMLElement)enumNodes.next();

			String strTag=node.getName();
			if(strTag.equals("BF"))//BooleanField
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				boolean bValue=false;
				if(strValue.equalsIgnoreCase("true"))
				{
					bValue=true;
				}
				cdo.putItem(strName,new BooleanField(strName,bValue));
			}
			else if(strTag.equals("BYF"))//ByteField
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");				
				cdo.putItem(strName,new ByteField(strName,Byte.parseByte(strValue)));
			}
			else if(strTag.equals("SF"))//ShortField
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				cdo.putItem(strName,new ShortField(strName, Short.parseShort(strValue)));
			}
			else if(strTag.equals("NF"))//IntegerField
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				cdo.putItem(strName,new IntegerField(strName, Integer.parseInt(strValue)));
			}
			else if(strTag.equals("LF"))//LongField
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				cdo.putItem(strName,new LongField(strName, Long.parseLong(strValue)));
			}
			else if(strTag.equals("FF"))//FloatField
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				cdo.putItem(strName,new FloatField(strName, Float.parseFloat(strValue)));
			}
			else if(strTag.equals("DBLF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				cdo.putItem(strName,new DoubleField(strName, Double.parseDouble(strValue)));
			}
			else if(strTag.equals("STRF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				cdo.putItem(strName,new StringField(strName, strValue));
			}
			else if(strTag.equals("DF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				cdo.putItem(strName,new DateField(strName, strValue));
			}
			else if(strTag.equals("TF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				cdo.putItem(strName,new TimeField(strName, strValue));
			}
			else if(strTag.equals("DTF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				cdo.putItem(strName,new DateTimeField(strName, strValue));
			}
			else if(strTag.equals("CDOF"))
			{
				String strName	=node.getStringAttribute("N");
				CDO cdoValue	=new CDO();
				xml2CDO(cdoValue,(XMLElement)node.getChildren().get(0),false);
				cdo.putItem(strName,new CDOField(strName, cdoValue));
			}else if(strTag.equals("FILE"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				cdo.putItem(strName,new FileField(strName, new File(strValue)));
			   //仅对最顶级CDO里的file 做字节流传输,嵌套里的文件在网络传输中不处理  忽略掉
				if(isRootNode)
					cdo.fileCount++;
			}
			else if(strTag.equals("BAF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				String[] strValueArr=null;
				if(strValue.length()==0)
				{
					strValueArr=new String[0];
				}
				else
				{
					strValueArr=strValue.split(",");
				}
				boolean[] bsValue=new boolean[strValueArr.length];
				for(int i=0;i<strValueArr.length;i++)
				{
					if(strValueArr[i].equalsIgnoreCase("true"))
					{
						bsValue[i]=true;	
					}
				}
				cdo.putItem(strName,new BooleanArrayField(strName, bsValue));
			}
			else if(strTag.equals("BYAF"))
			{//				
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				String[] strValueArr=null;
				if(strValue.length()==0)
				{
					strValueArr=new String[0];
				}
				else
				{
					strValueArr=strValue.split(",");
				}
				//bytes数据比较多，采用分成四等份，循环1/4 即可完成原来遍历所有数据处理。
				byte[] bysValue=new byte[strValueArr.length];
				int length=bysValue.length;				
				int mid=length/2;
				int quarter=mid/2;
				int j=quarter+1;
				int m=mid+1;
				int quarter3=mid+quarter;
				int n=mid+quarter+1;
							
				for(int i=0;i<=quarter ;i++){
					try{
						bysValue[i]=Byte.parseByte(strValueArr[i]);				
						if(j<=mid){
							bysValue[j]=Byte.parseByte(strValueArr[j]);
							j++;
						}
						if(m<=quarter3){
							bysValue[m]=Byte.parseByte(strValueArr[m]);
							m++;
						}
						if(n<length){
							bysValue[n]=Byte.parseByte(strValueArr[n]);
							n++;
						}				
					}catch(Exception ex)
					{
						throw new RuntimeException("Parse xml error: unexpected short value "+strValueArr[i]+" under "+strTag);
					}					
				}			
				cdo.putItem(strName,new ByteArrayField(strName, bysValue));
			}
			else if(strTag.equals("SAF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				String[] strValueArr=null;
				if(strValue.length()==0)
				{
					strValueArr=new String[0];
				}
				else
				{
					strValueArr=strValue.split(",");
				}
				short[] shsValue=new short[strValueArr.length];
				for(int i=0;i<strValueArr.length;i++)
				{
					try{
						shsValue[i]=Short.parseShort(strValueArr[i]);
					}catch(Exception ex)
					{
						throw new RuntimeException("Parse xml error: unexpected short value "+strValueArr[i]+" under "+strTag);
					}
				}			
				cdo.putItem(strName,new ShortArrayField(strName,shsValue));
			}
			else if(strTag.equals("NAF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				String[] strValueArr=null;
				if(strValue.length()==0)
				{
					strValueArr=new String[0];
				}
				else
				{
					strValueArr=strValue.split(",");
				}
				int[] nsValue=new int[strValueArr.length];
				for(int i=0;i<strValueArr.length;i++)
				{
					try{
						nsValue[i]=Integer.parseInt(strValueArr[i]);
					}catch(Exception ex)
					{
						throw new RuntimeException("Parse xml error: unexpected int value "+strValueArr[i]+" under "+strTag);
					}
				}			
				cdo.putItem(strName,new IntegerArrayField(strName, nsValue));
			}
			else if(strTag.equals("LAF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				String[] strValueArr=null;
				if(strValue.length()==0)
				{
					strValueArr=new String[0];
				}
				else
				{
					strValueArr=strValue.split(",");
				}
				long[] lsValue=new long[strValueArr.length];
				for(int i=0;i<strValueArr.length;i++)
				{
					try{
						lsValue[i]=Long.parseLong(strValueArr[i]);
					}catch(Exception ex)
					{
						throw new RuntimeException("Parse xml error: unexpected long value "+strValueArr[i]+" under "+strTag);
					}
				}			
				cdo.putItem(strName,new LongArrayField(strName, lsValue));
			}
			else if(strTag.equals("FAF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				String[] strValueArr=null;
				if(strValue.length()==0)
				{
					strValueArr=new String[0];
				}
				else
				{
					strValueArr=strValue.split(",");
				}
				float[] fsValue=new float[strValueArr.length];
				for(int i=0;i<strValueArr.length;i++)
				{
					try{
						fsValue[i]=Float.parseFloat(strValueArr[i]);
					}catch(Exception ex)
					{
						throw new RuntimeException("Parse xml error: unexpected float value "+strValueArr[i]+" under "+strTag);
					}
				}			
				cdo.putItem(strName,new FloatArrayField(strName,fsValue));
				
			}
			else if(strTag.equals("DBLAF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				String[] strValueArr=null;
				if(strValue.length()==0)
				{
					strValueArr=new String[0];
				}
				else
				{
					strValueArr=strValue.split(",");
				}
				double[] dblsValue=new double[strValueArr.length];
				for(int i=0;i<strValueArr.length;i++)
				{
					try{
						dblsValue[i]=Double.parseDouble(strValueArr[i]);
					}catch(Exception ex)
					{
						throw new RuntimeException("Parse xml error: unexpected float value "+strValueArr[i]+" under "+strTag);
					}
				}			
				cdo.putItem(strName,new DoubleArrayField(strName, dblsValue));
			}
			else if(strTag.equals("STRAF"))
			{
				String strName	=node.getStringAttribute("N");
				Iterator enumItems	=node.enumerateChildren();

				String[] strsValue=new String[node.countChildren()];
				int nIndex=0;
				while(enumItems.hasNext())
				{
					XMLElement subNode=(XMLElement)enumItems.next();
					String strSubNodeTag=subNode.getName();
					if(strSubNodeTag.equals("STR")==false)
					{
						throw new RuntimeException("Parse xml error: unexpected Tag name "+strSubNodeTag+" under "+strTag);
					}
					strsValue[nIndex]=subNode.getContent();
					nIndex++;
				}
				cdo.putItem(strName,new StringArrayField(strName, strsValue));
			}
			else if(strTag.equals("DAF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				String[] strValueArr=null;
				if(strValue.length()==0)
				{
					strValueArr=new String[0];
				}
				else
				{
					strValueArr=strValue.split(",");
				}
				cdo.putItem(strName,new DateArrayField(strName, strValueArr));
			}
			else if(strTag.equals("TAF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				String[] strsValue=null;
				if(strValue.length()==0)
				{
					strsValue=new String[0];
				}
				else
				{
					strsValue=strValue.split(",");
				}
				cdo.putItem(strName,new TimeArrayField(strName, strsValue));
			}
			else if(strTag.equals("DTAF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				String[] strsValue=null;
				if(strValue.length()==0)
				{
					strsValue=new String[0];
				}
				else
				{
					strsValue=strValue.split(",");
				}
				cdo.putItem(strName,new DateTimeArrayField(strName, strsValue) );
			}
			else if(strTag.equals("CDOAF"))
			{
				String strName	=node.getStringAttribute("N");
				Iterator enumItems	=node.enumerateChildren();

				List<CDO> cdosValue=new ArrayList<CDO>(node.countChildren());
				int nIndex=0;
				while(enumItems.hasNext())
				{
					XMLElement subNode=(XMLElement)enumItems.next();
					String strSubNodeTag=subNode.getName();
					if(strSubNodeTag.equals("CDO")==false)
					{
						throw new RuntimeException("Parse xml error: unexpected Tag name "+strSubNodeTag+" under "+strTag);
					}
					CDO tmpCDO=new CDO();
					xml2CDO(tmpCDO,subNode,false);
					cdosValue.add(tmpCDO);
					nIndex++;
				}
				cdo.putItem(strName,new CDOArrayField(strName, cdosValue));
			}else
			{
				throw new RuntimeException("Parse xml error: unexpected Tag name ["+strTag+"]");
			}
		}
	}
}
