FROM openjdk:8
MAINTAINER adarshkumarsingh83@gmail.com
ADD target/springboot-config-server.jar springboot-config-server.jar
EXPOSE 8888
ENV REGISTRY_HOST=springboot-config-server
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","springboot-config-server.jar"]