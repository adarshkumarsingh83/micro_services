FROM openjdk:8
MAINTAINER adarshkumarsingh83@gmail.com
ADD target/address-service.jar address-service.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","address-service.jar"]