#!/bin/sh

JAR_HOME=/usr/local/park/park
JAR_NAME=$1


case $2 in

start)

	JAR_PID=`ps -ef | grep -v grep | grep $1.jar | awk {'print $2'}`

        for PID in $JAR_PID;
        do

                kill -9 $PID

        done

	rm -f /$JAR_HOME/logs/$JAR_NAME-*

	nohup java -Xms300m -Xmx800m -Xmn512m -XX:SurvivorRatio=6 -jar $JAR_HOME/$JAR_NAME.jar >> /$JAR_HOME/logs/$JAR_NAME-`date +%Y%m%d-%H:%M:%S`-jar.log &
	;;
stop)

	JAR_PID=`ps -ef | grep -v grep | grep $1.jar | awk {'print $2'}`

	for PID in $JAR_PID;
	do

		kill -9 $PID

	done

	if [ $? -eq 0 ];

        	then

                	echo -e "$1 jar stopped \033[31msuccess\033[0m!!!"

               	else

                        echo -e "$1 jar stopped \033[31mfailed\033[0m!!!"

        fi
	
	#kill -9 $JAR_PID
	;;
*|help)
	
	echo " usage: 

		java_jar.sh JAR_NAME option

		option:(start | stop | help)"

esac
