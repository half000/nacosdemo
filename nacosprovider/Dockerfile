# Docker image for springboot file run
# VERSION 0.0.1
# Author: wangwei
# 基础镜像使用java8
FROM java:8
# 作者
MAINTAINER wangwei <271936145@qq.com>
# VOLUME 指定了临时文件目录为/tmp。
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
#因为 Spring Boot 使用的内嵌 Tomcat 容器默认使用/tmp作为工作目录
VOLUME /tmp
# 将jar包添加到容器中并更名为app.jar
ADD target/nacosprovider-1.1-SNAPSHOT.jar app.jar
# 修改jar的信息
RUN bash -c 'touch /app.jar'
ENV APP_PORT 8080
EXPOSE 8080
USER root
# 运行jar包
ENTRYPOINT ["java","-XX:+PrintGCDetails",  "-Xmx500m", "-Xms500m", "-Xss256k","-Xdebug", "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=7003", \
"-Djava.security.egd=file:/dev/./urandom","-Dserver.port=${APP_PORT}","-jar","/app.jar"]
