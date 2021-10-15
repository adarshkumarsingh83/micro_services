
## TO BUILD THE CODE 
* mvn clean package

## TO EXECUTE CODE 
* mvn spring-boot:run

## To run via jar file
$ java -jar target/espark-config-server.jar

## To Run via Docker
$ docker build -f Dockerfile -t espark-config-server .
$ docker run -e "SPRING_PROFILES_ACTIVE=jdbc" -p 8080:8080 -t espark-config-server

## TO TEST THE CONFIGURATION SERVER DIRECTLY
* http://localhost:8888/espark-api-server/{profile-name}/{label-name}
* http://localhost:8888/espark-api-server/dev/latest
* http://localhost:8888/espark-api-server/prod/latest


## This will allow us to reinitialize this controller to get any new config
* http://localhost:8888/refresh 

# TO VIEW THE H2 DB DATA
### log into the homepage of db
* http://localhost:8888/h2-console

### SQL QUERY FOR DATA
INSERT INTO properties (ID, application,profile,label,key,value) VALUES(3,'espark-api-server','dev','latest','user','somt');