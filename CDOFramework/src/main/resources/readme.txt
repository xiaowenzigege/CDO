Business  
	1 启动需要配置vm  
	    -DCDO_CONFIG_FILE=全路径/CDO.conf
	2 在CDO.conf里根据[servicebusResource.xml frameworkResource.xml]文件的实际位置，进行配置
	    servicebusResource.path=D:/tomcat-7.0.32/conf/cdoConfig/servicebusResource.xml 
	    frameworkResource.path=D:/tomcat-7.0.32/conf/cdoConfig/frameworkResource.xml
	    
	3  servicebusResource.xml 与    pluginsConfig.xml 组装成servicebus对象
	
	4  frameworkResource.xml  与  filtersConfig.xml  组装成framework对象
	
	注： 因 servicebusResource 和frameworkResource,CDO.conf由运维人员配置     故剥离出来可进行配置
	    pluginsConfig.xml 和 filtersConfig.xml bigTableConfig.xml 一般都由开发人员配置开发，无需运维人员参与
	故     放在class目录下即可   放在maven项目 在 /src/main/resources
