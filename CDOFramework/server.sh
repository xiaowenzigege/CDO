#!/bin/sh
function run(){
 PATH="$PATH:$JAVA_HOME/bin"
 export PATH
 SERVER_HOME=$(cd "$(dirname "$0")"; pwd)
 export SERVER_HOME
 CLASSPATH=.
 for k in $SERVER_HOME/lib/*.jar
 do
 CLASSPATH=$CLASSPATH:$k
 done
 export CLASSPATH
 echo $CLASSPATH
 java -DCDO_CONFIG_FILE=$SERVER_HOME/config/CDO.conf -Xms256m -Xmx512m -classpath "$CLASSPATH" com.cdo.business.rpc.server.Bootstrap $SERVER_HOME $param &
}

function help(){
        echo ===============================================================================    
        echo useage
        echo    server.sh start
        echo    server.sh stop
        echo ===============================================================================  
}

param=$1
if [[ $param = "start" ]] ; then
 run
elif [[ $param = "stop" ]] ; then
 run
else
 help
fi