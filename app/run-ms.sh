#!/bin/bash
echo "Execution of app into folder : $APP_ROOT"
echo "Environment : $APP_ENV"
cd $APP_ROOT
#cd $APP_ROOT/code
#echo "Maven build ..."
#mvn -U -up  -Dskip.integration.tests=true -Dskip.unit.tests=true install
#cp target/track-your-tasks-0.0.1-SNAPSHOT-spring-boot.jar $APP_ROOT/track-your-tasks.jar
chmod 777 $APP_ROOT/track-your-tasks.jar
echo "Starting Microservices ..."
java -cp $APP_ROOT -server -jar $APP_ROOT/track-your-tasks.jar