# EsparkEurekaServerApplication

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
			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
		</dependency>
    </dependencies>
```

* yml configuration 
  * server.port: It is specified on which port the application will serve. The default port number for Eureka Server is 8761.
  * eureka.client.registerWithEureka: We set this property to false so that it does not register itself in the list. Because; Here it acts as a server, not a client.
  * eureka.client.fetchRegistry: This value is set to false because it will not retrieve the registered microservices list from anywhere. It will create and maintain this list itself.**
``` 
spring:
  application:
    name: espark-eureka-server

eureka:
  client:
    registerWithEureka: false
    fetchRegistry: false

server:
  port: 8761
```

* annotation for configuration 
````
@EnableEurekaServer
@SpringBootApplication
public class EsparkEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsparkEurekaServerApplication.class, args);
	}

}
````

* To Build 
  * mvn clean package 

* To run 
  * mvn spring-boot:run 

* url 
  * http://localhost:8761/eureka/


* [reference](https://docs.spring.io/spring-cloud-netflix/reference/spring-cloud-netflix.html)
