FROM openjdk:8
MAINTAINER adarshkumarsingh83@gmail.com
ADD target/springboot-api-gateway.jar springboot-api-gateway.jar
EXPOSE 8080
ENV REGISTRY_HOST=springboot-api-gateway
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","springboot-api-gateway.jar"]