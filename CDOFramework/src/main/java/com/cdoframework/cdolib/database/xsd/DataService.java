/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.database.xsd;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.database.INoSQLDataEngine;
import com.cdoframework.cdolib.database.TransDefine;
import com.cdoframework.cdolib.servicebus.IServicePlugin;
import com.cdoframework.cdolib.servicebus.Service;
/**
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class DataService implements java.io.Serializable {

    /**
     * Field dataGroupId.
     */
    private java.lang.String dataGroupId = "";

    /**
     * Field noSQLDBId.
     */
    private java.lang.String noSQLDBId;

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field dataServiceChoice.
     */
    private com.cdoframework.cdolib.database.xsd.DataServiceChoice dataServiceChoice;

    public DataService() {
        super();
        setDataGroupId("");
    }

    /**
     * Returns the value of field 'choiceValue'. The field
     * 'choiceValue' has the following description: Internal choice
     * value storage
     * 
     * @return the value of field 'ChoiceValue'.
     */
    public java.lang.Object getChoiceValue() {
        return this._choiceValue;
    }

    /**
     * Returns the value of field 'dataGroupId'.
     * 
     * @return the value of field 'DataGroupId'.
     */
    public java.lang.String getDataGroupId() {
        return this.dataGroupId;
    }

    /**
     * Returns the value of field 'dataServiceChoice'.
     * 
     * @return the value of field 'DataServiceChoice'.
     */
    public com.cdoframework.cdolib.database.xsd.DataServiceChoice getDataServiceChoice() {
        return this.dataServiceChoice;
    }

    /**
     * Returns the value of field 'noSQLDBId'.
     * 
     * @return the value of field 'NoSQLDBId'.
     */
    public java.lang.String getNoSQLDBId() {
        return this.noSQLDBId;
    }

    /**
     * Method isValid.
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid() {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(final java.io.Writer out) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(final org.xml.sax.ContentHandler handler) throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, handler);
    }

    /**
     * Sets the value of field 'dataGroupId'.
     * 
     * @param dataGroupId the value of field 'dataGroupId'.
     */
    public void setDataGroupId(final java.lang.String dataGroupId) {
        this.dataGroupId = dataGroupId;
    }

    /**
     * Sets the value of field 'dataServiceChoice'.
     * 
     * @param dataServiceChoice the value of field
     * 'dataServiceChoice'.
     */
    public void setDataServiceChoice(final com.cdoframework.cdolib.database.xsd.DataServiceChoice dataServiceChoice) {
        this.dataServiceChoice = dataServiceChoice;
        this._choiceValue = dataServiceChoice;
    }

    /**
     * Sets the value of field 'noSQLDBId'.
     * 
     * @param noSQLDBId the value of field 'noSQLDBId'.
     */
    public void setNoSQLDBId(final java.lang.String noSQLDBId) {
        this.noSQLDBId = noSQLDBId;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * com.cdoframework.cdolib.database.xsd.DataService
     */
    public static com.cdoframework.cdolib.database.xsd.DataService unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.database.xsd.DataService) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.database.xsd.DataService.class, reader);
    }

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate() throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }

    //------------------------------manual code----------------------
    
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
  		defaultBigTableArray=hmBigTableGroupConfig.get("_defaultBigTableArray");		
//  		BigTableGroup[] bigTableGroups = this.getBigTableGroup();  	
//  		BigTableGroup bg=null;
//  		for(int i=0;i<bigTableGroups.length;i++)
//  		{	
//  			bg= bigTableGroups[i];	
//  			if(hmBigTableGroupConfig.containsKey(bg.getId()))
//  				this.hmBigTableGroup.put(bg.getId(),hmBigTableGroupConfig.get(bg.getId()));
//  			else
//  				this.hmBigTableGroup.put(bg.getId(),bg.getBigTable());				
//
//  			if(i==0)
//  			{//设置默认 bigTable默认数组
//  				if(hmBigTableGroupConfig.containsKey("_defaultBigTableArray"))
//  					defaultBigTableArray=hmBigTableGroupConfig.get("_defaultBigTableArray");
//  				else
//  					defaultBigTableArray = bg.getBigTable();
//  			}
//  		}		
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
//  			int nTransCount = this.getDataServiceChoiceCount();
//  			if(nTransCount==0)
//  			{
//  				return com.cdoframework.cdolib.base.Return.OK;
//  			}
//  			DataServiceChoice[] dataServiceChoices = this.getDataServiceChoice();
  			DataServiceChoice[] dataServiceChoices = {this.getDataServiceChoice()};
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
//  			NoSQLTransTypeChoice noSQLTransTypeChoices=noSqlTrans.getNoSQLTransTypeChoice()[0];
  			NoSQLTransTypeChoice noSQLTransTypeChoices=noSqlTrans.getNoSQLTransTypeChoice();
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


  	private com.cdoframework.cdolib.base.Return initNoSQLDataEngine(IServicePlugin servicePlugin,INoSQLDataEngine nosqlDataEngine,com.cdoframework.cdolib.database.xsd.CollectionNameType collectionType)
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
