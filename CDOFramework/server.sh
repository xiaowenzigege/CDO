#!/bin/sh
function initEnv(){
 PATH="$PATH:$JAVA_HOME/bin"
 export PATH
 SERVER_HOME=$(cd "$(dirname "$0")"; pwd)
 export SERVER_HOME 
 echo "SERVER_HOME="$SERVER_HOME 
 #CLASSPATH=.
 #for k in $SERVER_HOME/lib/*.jar
 #do
 #CLASSPATH=$CLASSPATH:$k
 #done
 #export CLASSPATH
 #echo $CLASSPATH
}
# 启动服务,内存设置参数值可以大点
function start(){
 	java -Djava.ext.dirs=$SERVER_HOME/lib -DCDO_CONFIG_FILE=$SERVER_HOME/config/CDO.conf -Xms256m -Xmx512m  com.cdo.business.rpc.server.Bootstrap $SERVER_HOME $param &
}
# 关闭服务,参数值无需设置
function stop(){
 	java -Djava.ext.dirs=$SERVER_HOME/lib -DCDO_CONFIG_FILE=$SERVER_HOME/config/CDO.conf -Xms64m -Xmx128m  com.cdo.business.rpc.server.Bootstrap $SERVER_HOME $param
}

function help(){
        echo ===============================================================================    
        echo useage
        echo    server.sh start
        echo    server.sh stop
        echo ===============================================================================  
}

param=$1
initEnv
if [[ $param = "start" ]] ; then
 start
elif [[ $param = "stop" ]] ; then
 stop
else
 help
fi