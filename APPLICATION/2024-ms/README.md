# espark-2024-microservice-sample

---


* maven configuraiton 
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
	
```

---

# for eureka server 
* @EnableEurekaServer on main class 
```
	<dependencies>
        <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
		</dependency>
    </dependencies>
```
* configuration 
```
eureka:
  client:
    registerWithEureka: false
    fetchRegistry: false

server:
  port: 8761
```
---

# for eureka client 
```
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
```
* configuration 
```
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```

---

# for api gateway 
```
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-gateway</artifactId>
		</dependency>
```

* configuration 
```
spring:
  cloud:
    gateway:
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
```

---

# configuraiton server 
* @EnableConfigServer on main class 
```
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-server</artifactId>
		</dependency>
```
* configuration 
```
spring:
  cloud:
    config:
      server:
        git:
          #uri: file://${user.home}/config-repo
          uri: https://github.com/adarshkumarsingh83/config-repo
          default-label: main
          skipSslValidation: true
          timeout: 4
```

# configuraiton server client 
```
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>
```

* configuration 
```
spring:
  config:
    import: configserver:http://localhost:8090
```

* code 
```
    @Value("${prop-prefix.prop.name}")
    String propName;
```
---

# profile configurations 
* in cmd 
  * java -jar -Dspring.profiles.active=production demo-0.0.1-SNAPSHOT.jar
* in applicattion.propeties 

```
spring.profiles.active=dev

```

* in application.yml file 
```
spring:
  config:
    activate:
      on-profile: default

---

spring:
  config:
    activate:
      on-profile: dev

---
spring:
  config:
    activate:
      on-profile: prod
```

---


