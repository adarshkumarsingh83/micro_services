FROM openjdk:8
MAINTAINER adarshkumarsingh83@gmail.com
ADD target/api-service.jar api-service.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","api-service.jar"]