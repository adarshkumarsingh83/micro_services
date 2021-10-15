# Code Building
### To build the code 
* mvn clean package

### PROFILE ARGUMENT IN IDE AS PROGRAM ARGUS IN INTELLJ /ECLIPSE
* --spring.profiles.active=dev

# Code Execution 
### To run via cmd line
* $ mvn spring-boot:run -Dspring-boot.run.profiles=dev
* $ mvn spring-boot:run -Dspring-boot.run.profiles=prod

### To run via jar file
* $ java -jar -Dspring.profiles.active=dev target/espark-api-server.jar
* $ java -jar -Dspring.profiles.active=prod target/espark-api-server.jar

### To Build & Run via Docker
* $ docker build -f Dockerfile -t espark-api-server .
* $ docker run -e "SPRING_PROFILES_ACTIVE=dev" -p 8080:8080 -t espark-api-server

### To access swagger url
* http://localhost:8080/swagger-ui.html



# TEST SAMPLE SERVICES

### TO TEST LOGIN SERVICE
* $ curl -X POST http://localhost:8080/api/v1/auth/login -d '{"userName":"adarsh","userPwd":"adarsh"}' -H "Content-Type: application/json"

### TO FETCH THE LOG ENABLED SERVICE
* $ curl -X GET http://localhost:8080/api/v1/log/beans

### TO TEST CHANGE LOG LEVEL TO LOG ENABLED SERVICE
* $ curl -X PUT http://localhost:8080/api/v1/log/DEBUG  -d '["ValidationProcessor","AuthenticationServiceImpl"]' -H "Content-Type: application/json"

### TO TEST CHANGE LOG LEVEL TO DEBUG FOR ALL SERVICES 
* $ curl -X PUT http://localhost:8080/api/v1/log/all/DEBUG

### TO TEST THE PROPS FROM CONFIG SERVER
* $ curl -X GET http://localhost:8080/api/config/{prop-name}

### Validation Annotations
* @NotNull – validates that the annotated property value is not null
* @AssertTrue – validates that the annotated property value is true
* @Size – validates that the annotated property value has a size between the attributes min and max; can be applied to String, Collection, Map, and array properties
* @Min – vValidates that the annotated property has a value no smaller than the value attribute
* @Max – validates that the annotated property has a value no larger than the value attribute
* @Email – validates that the annotated property is a valid email address
* @NotEmpty – validates that the property is not null or empty; can be applied to String, Collection, Map or Array values
* @NotBlank – can be applied only to text values and validated that the property is not null or whitespace
* @Positive and @PositiveOrZero – apply to numeric values and validate that they are strictly positive, or positive including 0
* @Negative and @NegativeOrZero – apply to numeric values and validate that they are strictly negative, or negative including 0
* @Past and @PastOrPresent – validate that a date value is in the past or the past including the present; can be applied to date types including those added in Java 8
* @Future and @FutureOrPresent – validates that a date value is in the future, or in the future including the present


---

### KEY POINT FOR THE DEVELOPMENT


* update the swagger based on the end points in
``` 
   /espark-api-server/src/main/resources/static/swagger-apis/apiV1/api.yaml
```

* for adding resources url in
  * /espark-api-server/src/main/resources/application.yaml
```   
  <domain-type>
  host: http://xxxx
  context: /xxxx
  urls:
  key1: /xxx/xxxx
  key2: /xxx/xxxx
```

    * create a config file in
```    
.../config/serviceurl/ implement the ServiceUrlConfiguration interface
and provide the <domain-type> and other details
```

    * create a request bean implementing the RequestBean interface and define the type of the bean based on requirement
       same bean type will further used for defining validator for bean validation.