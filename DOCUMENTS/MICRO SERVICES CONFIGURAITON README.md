----

#  1.Eureka Server 

----
* a.pom.xml file 

````	
<properties>
	<spring-cloud.version>Hoxton.SR3</spring-cloud.version>
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

<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>

````
* b.ApplicationMain.java

````
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer	
````

* c. application.yml
```` 
spring:
  application:
    name: discovery-service
eureka:
  client:
    eureka-server-connect-timeout-seconds: 5
    enabled: true
    fetch-registry: false
    register-with-eureka: false
server:
  port: 8761
```` 

* d. application.properties

````
logging.level.com.netflix.eureka=OFF
logging.level.com.netflix.discovery=OFF
logging.level.org.springframework=INFO
logging.level.org.springframework.cloud=DEBUG

````
----

#  2.For Eureka client 

----
* a. pom.xml 
````
<properties>
	<spring-cloud.version>Hoxton.SR3</spring-cloud.version>
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

 <dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
 </dependency>
 <dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
		
````
* b.ApplicationMain.java
````
  import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
  @EnableEurekaClient		
````
  
* c. application.yml 
````
 eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
      
````
* d. application.properties 
````
 ## Configuring /info endpoint details 
info.app.name=SPRING NAME
info.app.description=spring boot cloud gateway
info.app.version=1.0.0      
````
           
----

#  3.Zuul Server
 
----
* a.pom.xml
````
<properties>
	<spring-cloud.version>Hoxton.SR3</spring-cloud.version>
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
	
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>		
````

* b. Application.java 
````
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
@EnableZuulProxy
````

* c. application.yml 
```` 
 zuul:
  #Service will be mapped under the /api URI
  #prefix: /api
  #  Uncomment to disable auto-registering all services read from Eureka
  ignoredServices: '*'
  routes:
    test:
      path: /redirect/**
      url: http://google.com
    auth-service:
      path: /auth/**
      serviceId: auth-service
      # In case of auth, we need to pass the "/auth/" in the path to auth service.
      # So, set strip-prefix to false
      strip-prefix: false
      # Exclude authorization from sensitive headers
      sensitive-headers: Cookie,Set-Cookie
    service-id-name:
      path: /service-url/**
      serviceId: actual-service-id
    static:
      path: /static/**

```` 

----

# 4.Boot Admin Server

----
* a. pom.xml
````
<properties>
	<spring-boot-admin.version>2.2.1</spring-boot-admin.version>
</properties>

<dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>de.codecentric</groupId>
			<artifactId>spring-boot-admin-dependencies</artifactId>
			<version>${spring-boot-admin.version}</version>
			<type>pom</type>
			<scope>import</scope>
		</dependency>
	</dependencies>
</dependencyManagement>

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
 </dependency>
 <dependency>
	<groupId>de.codecentric</groupId>
	<artifactId>spring-boot-admin-starter-server</artifactId>
</dependency>

````
* b. ApplicationMain.java 
````
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
	@EnableAdminServer
	@EnableAutoConfiguration
````

* c. application.yml 
````
spring:
  application:
    name: spring-boot-admin
server:
  port: 8181 

````

----

#  5.Boot Admin Client
 
----

* a. pom.xml 

````
<properties>
	<spring-boot-admin.version>2.2.1</spring-boot-admin.version>
</properties>

<dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>de.codecentric</groupId>
			<artifactId>spring-boot-admin-dependencies</artifactId>
			<version>${spring-boot-admin.version}</version>
			<type>pom</type>
			<scope>import</scope>
		</dependency>
	</dependencies>
</dependencyManagement>

<dependency>
	<groupId>de.codecentric</groupId>
	<artifactId>spring-boot-admin-starter-client</artifactId>
</dependency>
````

*  b. ApplicationMain.java
````
import de.codecentric.boot.admin.config.EnableAdminServer;
@EnableAdminServer
````

* c. application.properties 

````
# SPRING BOOT ADMIN
spring.boot.admin.client.url=http://localhost:8181
management.endpoints.web.exposure.include=*

````

----

#  6.Configuration Server
 
----
* a. pom.xml 
````
<properties>
	<spring-cloud.version>Hoxton.SR3</spring-cloud.version>
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

<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-config-server</artifactId>
</dependency>

````
* b. ApplicationMain.java 
````
import org.springframework.cloud.config.server.EnableConfigServer;
	@EnableConfigServer

````
* c. application.yml 
````
spring:
  application:
    name: configuration-server
  cloud:
    config:
      server:
        git:
          uri: https://github.com/adarshkumarsingh83/configuration.git
          searchPaths: espark-configurations
          
server:
  port: 8888       
  
or for local configuration not the git then use below one 
````
* d. application.properties
````
server.port=8888 
spring.profiles.active=native
#common-config is the location inside the resource dir of the configuration server 
spring.cloud.config.server.native.search-locations=classpath:/common-config     
````

          		
----


#   7.Configuration Server Client
 
----
* a. pom.xml 
````
<properties>
	<spring-cloud.version>Hoxton.SR3</spring-cloud.version>
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


<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-config</artifactId>
</dependency>

````
* b.application.properties 
````
#SPRING CONFIGURATION SERVER URL
spring.cloud.config.uri=http://localhost:8888
		
````
----


#  8. Sluth Configuration
 

----
* a. pom.xml
````
<properties>
	<spring-cloud.version>Hoxton.SR3</spring-cloud.version>
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


<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>
		
````
* b. application.properties 
````
#SLUTH SAMPLER CONFIGURATION FOR LOG TRACING
spring.sleuth.sampler.probability=1		

# ENABLE LOGS 
logging.level.org.springframework.cloud=DEBUG
logging.level.org.springframework.web=INFO
logging.level.org.springframework=INFO

````
----


#  9.Zipkin Server

 
----

* a. pom.xml 
````
<parent>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>2.1.0.RELEASE</version>
	<relativePath/> 
</parent>

<dependency>
	<groupId>io.zipkin.java</groupId>
	<artifactId>zipkin-server</artifactId>
	<version>2.11.7</version>
</dependency>
<dependency>
	<groupId>io.zipkin.java</groupId>
	<artifactId>zipkin-autoconfigure-ui</artifactId>
	<version>2.11.7</version>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-devtools</artifactId>
	<scope>runtime</scope>
</dependency>
	
````
* b. application.properties 
````
spring.application.name=zipkin-server
server.port=9411
spring.main.allow-bean-definition-overriding=true
#to Suppress exception related to the promothus
management.metrics.web.server.auto-time-requests=false
	
````
----

#   10.Zipkin Server client

 
----
* a. pom.xml 
````
<properties>
	<spring-cloud.version>Hoxton.SR3</spring-cloud.version>
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

<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-devtools</artifactId>
	<scope>runtime</scope>
</dependency>
````

* b. application.properties 
````
#ZIPKIN SERVER URL
spring.zipkin.base-url=http://localhost:9411/

# ENABLE LOGS 
logging.level.org.springframework.cloud=DEBUG
logging.level.org.springframework.web=INFO
logging.level.org.springframework=INFO
````
----


#  11.Hystrix

 
----
* a. pom.xml
````
<properties>
	<spring-cloud.version>Hoxton.SR3</spring-cloud.version>
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

<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>
		
````
* b. Servvice class on the method wich is call some other external service 
````
  import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

	@HystrixCommand(fallbackMethod = "fallbackMethod")
	public returnType methodName(Type args...){
	    returnType returnValue = restTemplate.getForObject("url", TypeExpected.class);
	   return returnValue;
	}
	
	public returnType fallbackMethod(){
	
	  return valueSameASConfiguredMethod;
	}
	
````

----	


#  12.Hystix Dashboard Server


----	
* a. pom.xml 
````
<properties>
	<spring-cloud.version>Hoxton.SR3</spring-cloud.version>
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

 <dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
		
````

* b. ApplicationMain.java
````
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
	@EnableHystrixDashboard
````

* c. dashboard Url
````
http://localhost:port/hystrix	
````

----	


#  13. Hystrix Dash Board client

 
----	

* a. pom.xml

````		
<properties>
    <spring-cloud.version>Hoxton.M3</spring-cloud.version>
</properties>

<repositories>
    <repository>
         <id>spring-milestones</id>
         <name>Spring Milestones</name>
         <url>https://repo.spring.io/milestone</url>
    </repository>
</repositories>		
		
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

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
````
* b. ApplicationMain.java 
````
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
  @EnableCircuitBreaker
````
  
* c. Service.java
```` 
    Note: always call the method from different class not from same class other wise 
    hystix circuit will not work 

    @HystrixCommand(fallbackMethod = "fallBackMethod")
    public Type methodName(Type var...) {
    
    } 
    
    public Type fallBackMethod(Type var...) {
    
    }  

```` 
* d. application.properties
  ```` 
   management.endpoints.web.exposure.include=* 
  ```` 

* e. open the hystrix circuit in flow many times then collect the stream 
  of hystrix by calling the caller application then pass this url to the 
  hystrix dashboard for more details view 

*  http://localhost:8080/actuator/hystrix.stream


----


#  14.Spring Cloud Api Gateway

 
----
* a. pom.xml 

````	
<properties>
	<spring-cloud.version>Hoxton.SR3</spring-cloud.version>
</properties>

<dependency>
	<groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
	
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

````

* b. ApiGatewayConfig.java
````
ublic class ApiGatewayConfig {


    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()

                .route(r -> r.path("/api/**")
                        //Pre and Post Filters provided by Spring Cloud Gateway
                        .filters(f -> f.addRequestHeader("request-header", "request-header-value")
                                .addResponseHeader("response-header", "response-header-value"))
                        .uri("http://host:port/")
                        .id("service-id"))
                  ...............
                  ...............
                  ...............

                .build();
    }
}

````
* or 

* c. application.yml 
````
spring:
  cloud:
    gateway:
      routes:
      - id: service-id
        uri: http://hots:port/
        predicates:
        - Path=/service-url-patter/**
        ...............
        ...............
        ...............


````

	
