FROM openjdk:8
MAINTAINER adarshkumarsingh83@gmail.com
ADD target/management-aggregator-1.0-SNAPSHOT.jar management-aggregator-server-docker.jar
EXPOSE 8600
ENV REGISTRY_HOST=eureka-server
ENTRYPOINT ["java","-jar","management-aggregator-server-docker.jar"]