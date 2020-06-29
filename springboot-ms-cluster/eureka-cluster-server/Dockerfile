FROM openjdk:8
MAINTAINER adarshkumarsingh83@gmail.com
ADD target/eureka-cluster-server.jar eureka-cluster-server.jar
EXPOSE 8760-8770
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","eureka-cluster-server.jar"]