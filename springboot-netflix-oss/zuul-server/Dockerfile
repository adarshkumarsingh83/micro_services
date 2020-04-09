FROM openjdk:8
MAINTAINER adarshkumarsingh83@gmail.com
ADD target/zuul-server-1.0-SNAPSHOT.jar zuul-server-docker.jar
EXPOSE 8100
ENV REGISTRY_HOST=eureka-server
ENTRYPOINT ["java","-jar","zuul-server-docker.jar"]