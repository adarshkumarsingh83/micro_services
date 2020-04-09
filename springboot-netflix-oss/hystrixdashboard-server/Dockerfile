FROM openjdk:8
MAINTAINER adarshkumarsingh83@gmail.com
ADD target/hystrixdashboard-server-1.0-SNAPSHOT.jar hystrix-server-docker.jar
EXPOSE 8700
ENV REGISTRY_HOST=eureka-server
ENTRYPOINT ["java","-jar","hystrix-server-docker.jar"]