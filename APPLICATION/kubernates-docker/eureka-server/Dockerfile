FROM openjdk:8
MAINTAINER adarshkumarsingh83@gmail.com
ADD target/eureka-server.jar eureka-server.jar
EXPOSE 8761
ENV REGISTRY_HOST=eureka-server
ENTRYPOINT ["java","-jar","eureka-server.jar"]