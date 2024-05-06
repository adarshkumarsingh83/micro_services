# EsparkApiAggregatorApplication

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
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
    </dependencies>
```

* yml configuration
    * eureka.client.serviceUrl.defaultZone: This information must be given to which Eureka Server address the application will connect to.
    * server.port: It is specified on which port the application will serve. The default port number for Eureka Server is 8761.
    * eureka.client.registerWithEureka: We set this property to false so that it does not register itself in the list. Because; Here it acts as a server, not a client.
    * eureka.client.fetchRegistry: This value is set to false because it will not retrieve the registered microservices list from anywhere. It will create and maintain this list itself.**

``` 


spring:
  application:
    name: espark-api-aggregator

eureka:
  client:
    fetchRegistry: true
    registerWithEureka: true
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/

server:
  servlet:
    context-path: /api
  port: 8090

```


* To Build
    * mvn clean package

* To run
    * mvn spring-boot:run

* api url
    * http://localhost:8090/api/wish
