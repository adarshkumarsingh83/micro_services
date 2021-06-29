FROM openjdk:8
MAINTAINER adarshkumarsingh83@gmail.com
ADD target/configuration-server-1.0-SNAPSHOT.jar configuration-server-docker.jar
EXPOSE 8200
ENV REGISTRY_HOST=eureka-server
ENTRYPOINT ["java","-jar","configuration-server-docker.jar"]