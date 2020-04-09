FROM openjdk:8
MAINTAINER adarshkumarsingh83@gmail.com
ADD target/boot-admin-server-1.0-SNAPSHOT.jar boot-admin-docker.jar
EXPOSE 1111
ENV REGISTRY_HOST=eureka-server
ENTRYPOINT ["java","-jar","boot-admin-docker.jar",""]