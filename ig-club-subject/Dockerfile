#FROM openjdk:8
#ADD target/ig-club-starter-1.0-SNAPSHOT.jar app.jar
#EXPOSE 6388
#ENTRYPOINT java -jar /app.jar

FROM adoptopenjdk/openjdk8
#切换到镜像中的指定路径，设置工作目录
WORKDIR app
#会将宿主机的target/*.jar文件复制到 镜像的工作目录 /app/ 下
COPY target/*.jar app.jar
#执行java -jar命令
CMD ["java", "-jar", "app.jar"]

