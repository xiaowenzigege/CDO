3.X.X 版本
1. 去掉 BigTableConfig.xsd,若用分库分表,请使用shardJdbc
2  去掉 DataService.xsd 中的MongoDB的写法,MongoDB目前变化太大,已不适用
3  去掉 Framewok.xsd，缓存使用redis,独立出来写，分布式且支持多类型，原缓存依赖memcache 太旧了，且不支持多种类型
4  去掉 TransFilter 定义了 使用前置事件，后置事件，缓存配置依赖memache。

5  ServiceBus.xsd 由 ServiceBusResource.xsd  和 PluginsConfig.xsd 组成，分拆主要是为了部署方便，将不变和经常变化的分开。
   ServiceBusResource.xsd 主要定义了 关系型数据库源
   PluginsConfig.xsd   定义了使用插件的配置
6  使用高版本castor1.4.1及dbcp2.6，仅JDK1.8+ 才能支持



                                                                                        
                                                                                       
              —ServiceBusResource[配置多个数据库源]                                
             |                                            |-------- 多个TransService                           |                     
             |                                            |
ServiceBus---|                  ——多个普通plugin.xml--  |                
             |                 |                          |________ 多个DataService        
              —PluginsConfig—|
                               |                          __多个TransService  
                               |                         |   
                                ——多个普通plugin.xml---|
                                                         |__多个DataService
