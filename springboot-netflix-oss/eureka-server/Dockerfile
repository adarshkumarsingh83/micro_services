FROM openjdk:8
MAINTAINER adarshkumarsingh83@gmail.com
ADD target/eureka-server-1.0-SNAPSHOT.jar eureka-server-docker.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","eureka-server-docker.jar"]