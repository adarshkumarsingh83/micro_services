# SPRING BOOT CLOUD ARCHITECTURE MICRO SERVICE
---

## EUREKA SERVER 
* discovery server which register the services and other services  
* keep track of the services status and help for inter service communication
* maven dependency 
```
        <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
		</dependency>
```
* configuration
* @EnableEurekaServer in main class
* application.yml
````
spring:
  application:
    name: spring-cloud-eureka-server
server:
  port: 8761
eureka:
  client:
    registerWithEureka: false
    fetchRegistry: false
````


---

## API GATEWAY
* intercept the request and forward to the business service
* pull the server info for the business services and based on url forward to the service
* maven dependency 
````
        <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-gateway</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
````
* configuration 
* application.yml
````
server:
  port: 8080
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka
spring:
  application:
    name: gateway-service
  cloud:
    gateway:
      routes:
        - id: service-one
          uri: lb://SPRING-CLOUD-CLIENT-SERVICE
          predicates:
            - Path=/api/wish/**
        - id: service-two
          uri: lb://SPRING-CLOUD-CLIENT-SERVICE
          predicates:
            - Path=/api/wish/**
````
* configuration via class 
```
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("service-one",r -> r.path("/api/wish/**")
                        .uri("lb://SPRING-CLOUD-CLIENT-SERVICE"))
                .route("service-two",r -> r.path("/api/wish/**")
                        .uri("lb://SPRING-CLOUD-CLIENT-SERVICE"))
                .build();
    }
}
```
----

## CONFIG SERVER 
* keep all the configuration in git and based on request pass to the services 
* maven dependency
````
	  <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-server</artifactId>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
````
* configuration 
* @EnableConfigServer on main class 
* application.yml
````
server:
  port: 8888
spring:
  application:
    name: spring-cloud-config-server
  security:
    user:
      name: rootname
      password: rootpwd
  cloud:
    config:
      server:
        git:
          clone-on-start: true
          uri: https://github.com/adarshkumarsingh83/config-repo/
eureka:
  client:
    registerWithEureka: true
    fetchRegistry: true
    serviceUrl:
      defaultZone: ${EUREKA_URI:http://localhost:8761/eureka}
  instance:
    instance-id: ${spring.cloud.client.hostname}:${spring.application.name}:${spring.application.instance_id:${random.value}}
    preferIpAddress: true
````
* create a git repo with name 'config-repo'
*  create a file with 'service-name'-'profile'.yml
*  create a file with 'service-name'-'profile'.properties
*  provide the key and value in the config file 
* example https://github.com/adarshkumarsingh83/config-repo/blob/main/spring-cloud-client-service-default.properties

----

## BUSINESS SERVICE 
* actual rest services which has business login
* maven dependency 
````
        for rest endpoint 
        <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		
		for eureka client 
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
		
		for config client 
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>
````
* configuration
* application.yml
````
spring:
  application:
    name: spring-cloud-client-service
  profiles:
    active:
      - default
  config:
    import: optional:configserver:http://rootname:rootpwd@localhost:8888
server:
  port: 0
  servlet:
    context-path: /api
eureka:
  client:
    registerWithEureka: true
    fetchRegistry: true
    serviceUrl:
      defaultZone: ${EUREKA_URI:http://localhost:8761/eureka}
  instance:
    instance-id: ${spring.cloud.client.hostname}:${spring.application.name}:${spring.application.instance_id:${random.value}}
    preferIpAddress: true
````
* expose business endpoint 
* inject the configuration using @Value 

* for config server 
````
spring:
  profiles:
    active:
      - default
  config:
    import: optional:configserver:http://rootname:rootpwd@localhost:8888
````
* for eureka server 
````
eureka:
  client:
    registerWithEureka: true
    fetchRegistry: true
    serviceUrl:
      defaultZone: ${EUREKA_URI:http://localhost:8761/eureka}
  instance:
    instance-id: ${spring.cloud.client.hostname}:${spring.application.name}:${spring.application.instance_id:${random.value}}
    preferIpAddress: true
````