FROM openjdk:8
MAINTAINER adarshkumarsingh83@gmail.com
ADD target/user-management-1.0-SNAPSHOT.jar user-management-server-docker.jar
EXPOSE 8400
ENV REGISTRY_HOST=eureka-server
ENTRYPOINT ["java","-jar","user-management-server-docker.jar"]