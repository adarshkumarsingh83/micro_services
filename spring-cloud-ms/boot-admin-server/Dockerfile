FROM openjdk:8-jdk-alpine
MAINTAINER adarshkumarsingh83@gmail.com
ADD target/boot-admin-server.jar boot-admin-server-docker.jar
EXPOSE 8181
ENTRYPOINT ["java","-jar","boot-admin-server-docker.jar"]
