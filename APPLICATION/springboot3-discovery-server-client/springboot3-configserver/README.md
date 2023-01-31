# SPRINGBOOT CONFIG SERVER 
---

## To build
* mvn clean package

## to run
* mvn spring-boot:run 

## To access the props 
* curl http://rootname:rootpwd@localhost:8888/config-client/default/main
* http://localhost:8888/spring-cloud-client-service-default.properties
  * rootname/rootpwd
* curl http://rootname:rootpwd@localhost:8888/spring-cloud-client-service-default.properties

## To test 
```
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
```
