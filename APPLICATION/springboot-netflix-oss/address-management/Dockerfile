FROM openjdk:8
MAINTAINER adarshkumarsingh83@gmail.com
ADD target/address-management-1.0-SNAPSHOT.jar address-management-docker.jar
EXPOSE 8500
ENV REGISTRY_HOST=eureka-server
ENTRYPOINT ["java","-jar","address-management-docker.jar"]