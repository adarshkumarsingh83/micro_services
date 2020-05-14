FROM openjdk:8
MAINTAINER adarshkumarsingh83@gmail.com
ADD target/employee-service.jar employee-service.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","employee-service.jar"]