/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.database.dataservice;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.database.INoSQLDataEngine;
import com.cdoframework.cdolib.database.TransDefine;
import com.cdoframework.cdolib.servicebus.IServicePlugin;
import com.cdoframework.cdolib.servicebus.Service;

/**
 * Class DataService.
 * 
 * @version $Revision$ $Date$
 */
public class DataService implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _dataGroupId
     */
    private java.lang.String _dataGroupId = "";

    /**
     * Field _noSQLDBId
     */
    private java.lang.String _noSQLDBId;

    /**
     * Field _bigTableGroupList
     */
    private java.util.ArrayList _bigTableGroupList;

    /**
     * Field _dataServiceChoiceList
     */
    private java.util.ArrayList _dataServiceChoiceList;


      //----------------/
     //- Constructors -/
    //----------------/

    public DataService() 
     {
        super();
        setDataGroupId("");
        _bigTableGroupList = new ArrayList();
        _dataServiceChoiceList = new ArrayList();
    } //-- com.cdoframework.cdolib.database.dataservice.DataService()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addBigTableGroup
     * 
     * 
     * 
     * @param vBigTableGroup
     */
    public void addBigTableGroup(com.cdoframework.cdolib.database.dataservice.BigTableGroup vBigTableGroup)
        throws java.lang.IndexOutOfBoundsException
    {
        _bigTableGroupList.add(vBigTableGroup);
    } //-- void addBigTableGroup(com.cdoframework.cdolib.database.dataservice.BigTableGroup) 

    /**
     * Method addBigTableGroup
     * 
     * 
     * 
     * @param index
     * @param vBigTableGroup
     */
    public void addBigTableGroup(int index, com.cdoframework.cdolib.database.dataservice.BigTableGroup vBigTableGroup)
        throws java.lang.IndexOutOfBoundsException
    {
        _bigTableGroupList.add(index, vBigTableGroup);
    } //-- void addBigTableGroup(int, com.cdoframework.cdolib.database.dataservice.BigTableGroup) 

    /**
     * Method addDataServiceChoice
     * 
     * 
     * 
     * @param vDataServiceChoice
     */
    public void addDataServiceChoice(com.cdoframework.cdolib.database.dataservice.DataServiceChoice vDataServiceChoice)
        throws java.lang.IndexOutOfBoundsException
    {
        _dataServiceChoiceList.add(vDataServiceChoice);
    } //-- void addDataServiceChoice(com.cdoframework.cdolib.database.dataservice.DataServiceChoice) 

    /**
     * Method addDataServiceChoice
     * 
     * 
     * 
     * @param index
     * @param vDataServiceChoice
     */
    public void addDataServiceChoice(int index, com.cdoframework.cdolib.database.dataservice.DataServiceChoice vDataServiceChoice)
        throws java.lang.IndexOutOfBoundsException
    {
        _dataServiceChoiceList.add(index, vDataServiceChoice);
    } //-- void addDataServiceChoice(int, com.cdoframework.cdolib.database.dataservice.DataServiceChoice) 

    /**
     * Method clearBigTableGroup
     * 
     */
    public void clearBigTableGroup()
    {
        _bigTableGroupList.clear();
    } //-- void clearBigTableGroup() 

    /**
     * Method clearDataServiceChoice
     * 
     */
    public void clearDataServiceChoice()
    {
        _dataServiceChoiceList.clear();
    } //-- void clearDataServiceChoice() 

    /**
     * Method enumerateBigTableGroup
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateBigTableGroup()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_bigTableGroupList.iterator());
    } //-- java.util.Enumeration enumerateBigTableGroup() 

    /**
     * Method enumerateDataServiceChoice
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDataServiceChoice()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_dataServiceChoiceList.iterator());
    } //-- java.util.Enumeration enumerateDataServiceChoice() 

    /**
     * Method getBigTableGroup
     * 
     * 
     * 
     * @param index
     * @return BigTableGroup
     */
    public com.cdoframework.cdolib.database.dataservice.BigTableGroup getBigTableGroup(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _bigTableGroupList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.database.dataservice.BigTableGroup) _bigTableGroupList.get(index);
    } //-- com.cdoframework.cdolib.database.dataservice.BigTableGroup getBigTableGroup(int) 

    /**
     * Method getBigTableGroup
     * 
     * 
     * 
     * @return BigTableGroup
     */
    public com.cdoframework.cdolib.database.dataservice.BigTableGroup[] getBigTableGroup()
    {
        int size = _bigTableGroupList.size();
        com.cdoframework.cdolib.database.dataservice.BigTableGroup[] mArray = new com.cdoframework.cdolib.database.dataservice.BigTableGroup[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.database.dataservice.BigTableGroup) _bigTableGroupList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.database.dataservice.BigTableGroup[] getBigTableGroup() 

    /**
     * Method getBigTableGroupCount
     * 
     * 
     * 
     * @return int
     */
    public int getBigTableGroupCount()
    {
        return _bigTableGroupList.size();
    } //-- int getBigTableGroupCount() 

    /**
     * Returns the value of field 'dataGroupId'.
     * 
     * @return String
     * @return the value of field 'dataGroupId'.
     */
    public java.lang.String getDataGroupId()
    {
        return this._dataGroupId;
    } //-- java.lang.String getDataGroupId() 

    /**
     * Method getDataServiceChoice
     * 
     * 
     * 
     * @param index
     * @return DataServiceChoice
     */
    public com.cdoframework.cdolib.database.dataservice.DataServiceChoice getDataServiceChoice(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _dataServiceChoiceList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.database.dataservice.DataServiceChoice) _dataServiceChoiceList.get(index);
    } //-- com.cdoframework.cdolib.database.dataservice.DataServiceChoice getDataServiceChoice(int) 

    /**
     * Method getDataServiceChoice
     * 
     * 
     * 
     * @return DataServiceChoice
     */
    public com.cdoframework.cdolib.database.dataservice.DataServiceChoice[] getDataServiceChoice()
    {
        int size = _dataServiceChoiceList.size();
        com.cdoframework.cdolib.database.dataservice.DataServiceChoice[] mArray = new com.cdoframework.cdolib.database.dataservice.DataServiceChoice[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.database.dataservice.DataServiceChoice) _dataServiceChoiceList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.database.dataservice.DataServiceChoice[] getDataServiceChoice() 

    /**
     * Method getDataServiceChoiceCount
     * 
     * 
     * 
     * @return int
     */
    public int getDataServiceChoiceCount()
    {
        return _dataServiceChoiceList.size();
    } //-- int getDataServiceChoiceCount() 

    /**
     * Returns the value of field 'noSQLDBId'.
     * 
     * @return String
     * @return the value of field 'noSQLDBId'.
     */
    public java.lang.String getNoSQLDBId()
    {
        return this._noSQLDBId;
    } //-- java.lang.String getNoSQLDBId() 

    /**
     * Method isValid
     * 
     * 
     * 
     * @return boolean
     */
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param out
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param handler
     */
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Method removeBigTableGroup
     * 
     * 
     * 
     * @param vBigTableGroup
     * @return boolean
     */
    public boolean removeBigTableGroup(com.cdoframework.cdolib.database.dataservice.BigTableGroup vBigTableGroup)
    {
        boolean removed = _bigTableGroupList.remove(vBigTableGroup);
        return removed;
    } //-- boolean removeBigTableGroup(com.cdoframework.cdolib.database.dataservice.BigTableGroup) 

    /**
     * Method removeDataServiceChoice
     * 
     * 
     * 
     * @param vDataServiceChoice
     * @return boolean
     */
    public boolean removeDataServiceChoice(com.cdoframework.cdolib.database.dataservice.DataServiceChoice vDataServiceChoice)
    {
        boolean removed = _dataServiceChoiceList.remove(vDataServiceChoice);
        return removed;
    } //-- boolean removeDataServiceChoice(com.cdoframework.cdolib.database.dataservice.DataServiceChoice) 

    /**
     * Method setBigTableGroup
     * 
     * 
     * 
     * @param index
     * @param vBigTableGroup
     */
    public void setBigTableGroup(int index, com.cdoframework.cdolib.database.dataservice.BigTableGroup vBigTableGroup)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _bigTableGroupList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _bigTableGroupList.set(index, vBigTableGroup);
    } //-- void setBigTableGroup(int, com.cdoframework.cdolib.database.dataservice.BigTableGroup) 

    /**
     * Method setBigTableGroup
     * 
     * 
     * 
     * @param bigTableGroupArray
     */
    public void setBigTableGroup(com.cdoframework.cdolib.database.dataservice.BigTableGroup[] bigTableGroupArray)
    {
        //-- copy array
        _bigTableGroupList.clear();
        for (int i = 0; i < bigTableGroupArray.length; i++) {
            _bigTableGroupList.add(bigTableGroupArray[i]);
        }
    } //-- void setBigTableGroup(com.cdoframework.cdolib.database.dataservice.BigTableGroup) 

    /**
     * Sets the value of field 'dataGroupId'.
     * 
     * @param dataGroupId the value of field 'dataGroupId'.
     */
    public void setDataGroupId(java.lang.String dataGroupId)
    {
        this._dataGroupId = dataGroupId;
    } //-- void setDataGroupId(java.lang.String) 

    /**
     * Method setDataServiceChoice
     * 
     * 
     * 
     * @param index
     * @param vDataServiceChoice
     */
    public void setDataServiceChoice(int index, com.cdoframework.cdolib.database.dataservice.DataServiceChoice vDataServiceChoice)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _dataServiceChoiceList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _dataServiceChoiceList.set(index, vDataServiceChoice);
    } //-- void setDataServiceChoice(int, com.cdoframework.cdolib.database.dataservice.DataServiceChoice) 

    /**
     * Method setDataServiceChoice
     * 
     * 
     * 
     * @param dataServiceChoiceArray
     */
    public void setDataServiceChoice(com.cdoframework.cdolib.database.dataservice.DataServiceChoice[] dataServiceChoiceArray)
    {
        //-- copy array
        _dataServiceChoiceList.clear();
        for (int i = 0; i < dataServiceChoiceArray.length; i++) {
            _dataServiceChoiceList.add(dataServiceChoiceArray[i]);
        }
    } //-- void setDataServiceChoice(com.cdoframework.cdolib.database.dataservice.DataServiceChoice) 

    /**
     * Sets the value of field 'noSQLDBId'.
     * 
     * @param noSQLDBId the value of field 'noSQLDBId'.
     */
    public void setNoSQLDBId(java.lang.String noSQLDBId)
    {
        this._noSQLDBId = noSQLDBId;
    } //-- void setNoSQLDBId(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return DataService
     */
    public static com.cdoframework.cdolib.database.dataservice.DataService unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.DataService) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.DataService.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.DataService unmarshal(java.io.Reader) 

    /**
     * Method validate
     * 
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	private static Logger logger = Logger.getLogger(DataService.class);
	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	private Map<String,TransDefine> hmTransDefine = new HashMap<String,TransDefine>(30);
	private Map<String,BigTable[]> hmBigTableGroup = new HashMap<String,BigTable[]>(2);
	private boolean hasBigTable;
	private BigTable[] defaultBigTableArray;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------
	private String strServiceName;
	private INoSQLDataEngine nosqlDataEngine;
	private IServicePlugin servicePlugin;

	
	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//私有方法 所有仅在本类或派生类中使用的函数在此定义为private方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	public BigTable[] getBigTableArray(String strId)
	{
		if(strId!=null)
		{
			return hmBigTableGroup.get(strId);
		}
		return defaultBigTableArray;
	}
	public boolean hasBigTable()
	{
		return hasBigTable;
	}
	public static DataService fromXML(String strXML) throws Exception
	{
		StringReader reader=null;
		try
		{
			reader=new StringReader(strXML);
			DataService dataService=(DataService)DataService.unmarshal(reader);			
			return dataService;
		}
		finally
		{
			if(reader!=null)
			{
				try
				{
					reader.close();
				}
				catch(Exception e)
				{
					logger.error("fromXML:"+e.getMessage(),e);
				}
			}
		}
	}
	
	public static DataService loadDataServiceFromFile(String strSQLTransFile) throws Exception
	{
		String strSQLTransXML=Utility.readTextFile(strSQLTransFile);
		if(strSQLTransXML==null)
		{
			return null;
		}

		return fromXML(strSQLTransXML);
	}

	public static DataService loadDataServiceFromFile(String strSQLTransFile,String strCoding) throws Exception
	{
		String strSQLTransXML=Utility.readTextFile(strSQLTransFile,strCoding);
		if(strSQLTransXML==null)
		{
			return null;
		}

		return fromXML(strSQLTransXML);
	}

	public static DataService loadDataServiceResource(String strSQLTransResource,String strCoding) throws Exception
	{
		String strSQLTransXML=Utility.readTextResource(strSQLTransResource,strCoding);
		if(strSQLTransXML==null)
		{
			return null;
		}

		return fromXML(strSQLTransXML);
	}

	/**
	 * 
	 * 解析本dataService.xml中的bigtable
	 * 如果全局有bigtable，则用全局的bigTable
	       如果全局没有bigTable,则用本配置文件的bigTable
	 */	
	private void initBigTableConfig(HashMap<String,BigTable[]> hmBigTableGroupConfig)
	{
		BigTableGroup[] bigTableGroups = this.getBigTableGroup();
		BigTableGroup bg=null;
		for(int i=0;i<bigTableGroups.length;i++)
		{	
			bg= bigTableGroups[i];	
			if(hmBigTableGroupConfig.containsKey(bg.getId()))
				this.hmBigTableGroup.put(bg.getId(),hmBigTableGroupConfig.get(bg.getId()));
			else
				this.hmBigTableGroup.put(bg.getId(),bg.getBigTable());				

			if(i==0)
			{//设置默认 bigTable默认数组
				if(hmBigTableGroupConfig.containsKey("_defaultBigTableArray"))
					defaultBigTableArray=hmBigTableGroupConfig.get("_defaultBigTableArray");
				else
					defaultBigTableArray = bg.getBigTable();
			}
		}		
	}
	/**
	 * 
	 * @param strServiceName
	 * @param service
	 * @param servicePlugin
	 * @param serviceBus
	 * @param hmBigTableGroupConfig  在servicebus中的initBigTableConfig 方法中初始化
	 * @return
	 */
    public com.cdoframework.cdolib.base.Return init(String strServiceName,Service service,com.cdoframework.cdolib.servicebus.ServicePlugin servicePlugin,com.cdoframework.cdolib.servicebus.ServiceBus serviceBus
    		,HashMap<String,BigTable[]> hmBigTableGroupConfig)
	{
    	this.servicePlugin = servicePlugin;
    	this.strServiceName = strServiceName;
		try
		{
			this.hmBigTableGroup.putAll(hmBigTableGroupConfig);	
			//设置默认 bigTable默认数组
			if(hmBigTableGroupConfig.containsKey("_defaultBigTableArray"))
				defaultBigTableArray=hmBigTableGroupConfig.get("_defaultBigTableArray");
		
			String strDataGroupId = this.getDataGroupId();
			if(strDataGroupId==null)
			{
				strDataGroupId= serviceBus.getDefaultDataGroupId();
				this.setDataGroupId(strDataGroupId);
			}
			
			//default nosqlDataEngine					
			nosqlDataEngine = servicePlugin.getNoSQLDataEngine(this.getNoSQLDBId());
			if(this.getNoSQLDBId()!=null && nosqlDataEngine==null)
			{
				return com.cdoframework.cdolib.base.Return.valueOf(-1,"There is no defination of NoSQLDB");
			}

			//解析trans
			int nTransCount = this.getDataServiceChoiceCount();
			if(nTransCount==0)
			{
				return com.cdoframework.cdolib.base.Return.OK;
			}
			DataServiceChoice[] dataServiceChoices = this.getDataServiceChoice();
			//初始设置 dataservice.xml中没有 bigtable
			//如果 存在bigTable 则sqlTrans至少存在一个bigTableGroupId 
			hasBigTable=false;
			for(DataServiceChoice dataServiceChoice:dataServiceChoices)
			{
				DataServiceChoiceItem[] dataServiceChoiceItems = dataServiceChoice.getDataServiceChoiceItem();
				if(dataServiceChoiceItems==null)
				{
					continue;
				}
				for(DataServiceChoiceItem dataServiceChoiceItem:dataServiceChoiceItems)
				{
					if(dataServiceChoiceItem==null)
					{
						continue;
					}
					//nosqlTrans
					NoSQLTrans noSQLTransTemp = dataServiceChoiceItem.getNoSQLTrans();
					if(noSQLTransTemp!=null)
					{
						com.cdoframework.cdolib.base.Return ret = parseNoSqlTrans(noSQLTransTemp,strDataGroupId,service);
						if(ret.getCode()!=0)
						{
							return ret;
						}
					}
					
					//sqlTrans
					SQLTrans sqlTransTemp=dataServiceChoiceItem.getSQLTrans();
					if(sqlTransTemp!=null)
					{
						if(!hasBigTable)
						{
							String strBigTable=sqlTransTemp.getBigTableGroupId();
							if(strBigTable!=null && !strBigTable.equals(""))
								hasBigTable=true;
						}
						
						com.cdoframework.cdolib.base.Return ret = parseSqlTrans(sqlTransTemp,strDataGroupId,service);
						if(ret.getCode()!=0)
						{
							return ret;
						}
					}					
				}				
			}
		}
		catch(Exception e)
		{
			logger.error("init:"+e.getMessage(),e);
			return com.cdoframework.cdolib.base.Return.valueOf(-1,e.getLocalizedMessage());
		}
		return com.cdoframework.cdolib.base.Return.OK;
	}

    private com.cdoframework.cdolib.base.Return parseSqlTrans(SQLTrans sqlTransTemp,String strDataGroupId,Service service)
    {
		String strTransName = sqlTransTemp.getTransName();
		sqlTransTemp.setDataService(this);
		
		if(sqlTransTemp.getDataGroupId()==null)
		{
			sqlTransTemp.setDataGroupId(strDataGroupId);
		}
		
		TransDefine transDefine = new TransDefine();
		transDefine.setSqlTrans(sqlTransTemp);
		this.hmTransDefine.put(strTransName,transDefine);
		TransDefine oldValue = service.putTransDefine(strTransName,transDefine);
		if(oldValue!=null)
		{
			//TODO output log
			return com.cdoframework.cdolib.base.Return.valueOf(-1,"duplicated transName :"+strTransName);
		}
		return com.cdoframework.cdolib.base.Return.OK;
    }
    private com.cdoframework.cdolib.base.Return parseNoSqlTrans(NoSQLTrans noSqlTransTemp,String strDataGroupId,Service service)
    {
    	noSqlTransTemp.setDataService(this);
		
		//如果配置了NoSQLTrans,系统总线上就应该有NoSqlDataEngine,如果没有就应该报错
		com.cdoframework.cdolib.base.Return ret = initNoSQLTransDataEngine(noSqlTransTemp,nosqlDataEngine,servicePlugin);
		if(ret.getCode() !=0)
		{
			return ret;
		}
		String strTransName = noSqlTransTemp.getTransName();			
		TransDefine transDefine = this.hmTransDefine.get(strTransName);
		if(transDefine==null)
		{
			transDefine = new TransDefine();
			transDefine.setNoSqlTrans(noSqlTransTemp);
			hmTransDefine.put(strTransName,transDefine);
			TransDefine oldValue = service.putTransDefine(strTransName,transDefine);
			if(oldValue!=null)
			{
				//TODO output log
				return com.cdoframework.cdolib.base.Return.valueOf(-1,"duplicated transName :"+strTransName);
			}
		}
		else
		{
			//TODO output log
			return com.cdoframework.cdolib.base.Return.valueOf(-1,"duplicated transName :"+strTransName);
		}	
		return com.cdoframework.cdolib.base.Return.OK;
    }
    
    
	private com.cdoframework.cdolib.base.Return  initNoSQLTransDataEngine(NoSQLTrans noSqlTrans,INoSQLDataEngine nosqlDataEngine,IServicePlugin servicePlugin)
	{
		com.cdoframework.cdolib.base.Return ret = null;
		try{
			NoSQLTransTypeChoice noSQLTransTypeChoices=noSqlTrans.getNoSQLTransTypeChoice()[0];
			NoSQLTransTypeChoiceItem noSqlTransTypeChoiceItem=null;
			for(int i=0;i<noSQLTransTypeChoices.getNoSQLTransTypeChoiceItemCount();i++)
			{
				noSqlTransTypeChoiceItem=noSQLTransTypeChoices.getNoSQLTransTypeChoiceItem(i);
				ret = initNoSQLDataEngine(servicePlugin,nosqlDataEngine,noSqlTransTypeChoiceItem.getQueryField());
				if(ret!=null && ret.getCode()!=0)
				{
					logger.error(ret.getText());
					break;
				}
				ret = initNoSQLDataEngine(servicePlugin,nosqlDataEngine,noSqlTransTypeChoiceItem.getQueryRecord());
				if(ret!=null && ret.getCode()!=0)
				{
					logger.error(ret.getText());
					break;
				}
				ret = initNoSQLDataEngine(servicePlugin,nosqlDataEngine,noSqlTransTypeChoiceItem.getQueryRecordSet());
				if(ret!=null && ret.getCode()!=0)
				{
					logger.error(ret.getText());
					break;
				}
				ret = initNoSQLDataEngine(servicePlugin,nosqlDataEngine,noSqlTransTypeChoiceItem.getAdd());
				if(ret!=null && ret.getCode()!=0)
				{
					logger.error(ret.getText());
					break;
				}
				ret = initNoSQLDataEngine(servicePlugin,nosqlDataEngine,noSqlTransTypeChoiceItem.getModify());
				if(ret!=null && ret.getCode()!=0)
				{
					logger.error(ret.getText());
					break;
				}
				ret = initNoSQLDataEngine(servicePlugin,nosqlDataEngine,noSqlTransTypeChoiceItem.getReplace());
				if(ret!=null && ret.getCode()!=0)
				{
					logger.error(ret.getText());
					break;
				}
				ret = initNoSQLDataEngine(servicePlugin,nosqlDataEngine,noSqlTransTypeChoiceItem.getRemove());
				if(ret!=null && ret.getCode()!=0)
				{
					logger.error(ret.getText());
					break;
				}	
				ret = initNoSQLDataEngine(servicePlugin,nosqlDataEngine,noSqlTransTypeChoiceItem.getQueryCount());
			}
			if(ret!=null && ret.getCode()!=0)
			{
				logger.error(ret.getText());
				return ret;
			}
			return com.cdoframework.cdolib.base.Return.OK;
		}
		catch(Exception ex)
		{
			logger.error("caught exception when handle strTransName "+noSqlTrans.getTransName(),ex);
			return com.cdoframework.cdolib.base.Return.valueOf(-1,"caught exception when parse strTransName "+noSqlTrans.getTransName()+ex.getLocalizedMessage());			
		}
		
	}


	private com.cdoframework.cdolib.base.Return initNoSQLDataEngine(IServicePlugin servicePlugin,INoSQLDataEngine nosqlDataEngine,com.cdoframework.cdolib.database.dataservice.CollectionNameType collectionType)
	{//TODO 未处理dbname
		if(collectionType==null)
		{
			return null;
		}
		String strDBServerId = collectionType.getDBServerId();
		if(strDBServerId==null || strDBServerId.trim().length()==0 || strDBServerId.equalsIgnoreCase(nosqlDataEngine.getId()))
		{
			collectionType.setNoSQLDataEngine(nosqlDataEngine);
			collectionType.setDBServerId(nosqlDataEngine.getId());
		}
		else
		{
			nosqlDataEngine = servicePlugin.getNoSQLDataEngine(strDBServerId);
			if(nosqlDataEngine==null)
			{
				return com.cdoframework.cdolib.base.Return.valueOf(-1,"There is no defination of NoSQLDB: "+strDBServerId);
			}
			collectionType.setNoSQLDataEngine(nosqlDataEngine);
		}
		

		return com.cdoframework.cdolib.base.Return.OK;
		
	}
	public Map<String,TransDefine> getTransMap()
	{
		return hmTransDefine;
	}
}
