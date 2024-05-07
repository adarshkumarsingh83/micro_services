# espark-api-gateway-declarative


* maven dependency
```  
   <properties>
		<java.version>21</java.version>
		<spring-cloud.version>2023.0.1</spring-cloud.version>
	</properties>
	
    <dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
	
    <dependencies>
       <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-gateway</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>

    </dependencies>
```

* yml configuration
    * server.port: It is specified on which port the application will serve. The default port number for Eureka Server is 8761.
    * eureka.client.serviceUrl.defaultZone We set this property to eureka register server
```
spring:
  application:
    name: espark-api-gateway-declarative
  cloud:
    gateway:
      actuator:
        verbose:
          enabled: true
      discovery:
        locator:
          enabled: true
          lowerCaseServiceId: true
      routes:
        - id: first
          uri: lb://ESPARK-API-AGGREGATOR
          predicates:
            - Path=/api/wish/**
          filters:
            - AddRequestHeader=wish-request, wish-request-header
            - AddResponseHeader=wish-response, wish-response-header
        - id: second
          uri: lb://ESPARK-API-AGGREGATOR
          predicates:
            - Path=/api/greet/**
          filters:
            - AddRequestHeader=greet-request, greet-request-header
            - AddResponseHeader=greet-response, greet-response-header

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/

server:
  port: 9090
``` 

* properties configuration
```
management.endpoint.gateway.enabled=true
management.endpoints.web.exposure.include=*
```

* To Build
    * mvn clean package

* To run
    * mvn spring-boot:run

* api service url
    * http://localhost:8080/api/wish
    * http://localhost:8080/api/greet

* [ref] (https://docs.spring.io/spring-cloud-gateway/reference/index.html)