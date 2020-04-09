FROM openjdk:8
ADD target/web-services.jar web-services.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","web-services.jar"]