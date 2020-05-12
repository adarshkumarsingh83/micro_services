FROM openjdk:8
MAINTAINER adarshkumarsingh83@gmail.com
ADD target/springboot-cluster-client.jar springboot-cluster-client.jar
EXPOSE 8080-8090
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","springboot-cluster-client.jar"]