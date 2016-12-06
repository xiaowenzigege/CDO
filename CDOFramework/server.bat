@echo off 
set param=%1%
if "%param%"=="start" goto run
if "%param%"=="stop" goto run
:help
echo ===============================================================================    
echo useage
echo    server.bat start
echo    server.bat stop
echo ===============================================================================  
echo. 
goto exit

:run
set DIRNAME=.\
if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
if "%OS%" == "Windows_NT" set PROGNAME=%~nx0%
set "JAVA_HOME=D:\Java\jdk1.6.0_30"
set JAVAC_JAR=%JAVA_HOME%\lib\tools.jar
setlocal enabledelayedexpansion
set "JAVA=%JAVA_HOME%\bin\java"
set OPTS=-DCDO_CONFIG_FILE=%DIRNAME%/config/CDO.conf -Xms256M -Xmx512M  -XX:+AggressiveOpts -XX:+UseParallelGC -XX:NewSize=64M
set LIBPATH=.\lib
set CP=%DIRNAME%;
set MAIN=com.cdo.business.rpc.server.Bootstrap 


for /f %%i in ('dir/b %LIBPATH%\*.jar^|sort') do (set CP=!CP!%LIBPATH%\%%i;)

echo ===============================================================================  
echo.  
echo   Engine Startup Environment  
echo.  
echo   JAVA_HOME: %JAVA%  
echo.  
echo   JAVA_OPTS: %OPTS%  
echo.  
echo   CLASSPATH: %CP%  
echo.  
echo ===============================================================================  
echo.  

java %OPTS% -classpath %CP% %MAIN% %DIRNAME% %param%

:exit