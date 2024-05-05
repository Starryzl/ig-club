#!/bin/bash
source /etc/profile
#查看之前运行的进程id
pid=`ps -ef | grep ig-club-starter.jar | grep -v grep | awk '{print $2}'`
echo "部署前的pid进程 :$pid"
# 关闭已经启动的jar进程
if [ -n "$pid" ]
  then
    kill -9 $pid
else
    echo "进程没有启动"
fi
sleep 5s
#copy jar 到启动目录
\cp -rf /home/jenkins/workspace/ig-club-subject/ig-club-subject/ig-club-starter/target/ig-club-starter.jar /home/jenkins/workspace/ig-club-subject/ig-club-subject/ig-club-starter/target/ig-club-starter.jar

cd /home/workplace/maven-demo/restartdir
nohup /usr/java/jdk1.8.0_371/bin/java -jar /home/workplace/maven-demo/restartdir/ymxSeller-0.0.1-SNAPSHOT.jar  > /home/workplace/maven-demo/restartdir/springboot.log  2>&1 &
echo "脚本执行完毕"
sleep 5s
pid=`ps -ef|grep ymxSeller-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{print $2}'`
# 检验进程是否启动
if [ -n "$pid" ]
  then
        echo "部署后的pid进程 :$pid"
                echo "启动成功"
else
    echo "进程没有启动"
fi
~
