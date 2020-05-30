FROM openjdk:8
MAINTAINER adarshkumarsingh83@gmail.com
ADD target/springboot-eureka-server.jar springboot-eureka-server.jar
EXPOSE 8761
ENV REGISTRY_HOST=springboot-eureka-server
ENTRYPOINT ["java","-jar","springboot-eureka-server.jar"]