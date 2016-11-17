#!/bin/sh
PATH="$PATH:$JAVA_HOME/bin"
export PATH
SIGN_HOME=$(cd "$(dirname "$0")"; pwd)
export SIGN_HOME
CLASSPATH=.
for k in $SIGN_HOME/lib/*.jar
do
 CLASSPATH=$CLASSPATH:$k
done
export CLASSPATH
echo $CLASSPATH
java  -Xms256m -Xmx512m -classpath "$CLASSPATH" com.liantong.epgtools.AppBusiness $SIGN_HOME &
