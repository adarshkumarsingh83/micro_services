# espark-swagger-api-docs

### swagger yaml based web project implemenations 


## TO RUN WITH HTTP SERVER
* $ brew install node

### to install http server
* $ npm install http-server -g

###to run the application
* $ http-server .
* http://localhost:8080/


## TO RUN WITH EXPRESS
* $ npm i
* $ node server.js
* http://localhost:8080/


## TO RUN WITH DOCKER IMAGE

* $ docker build -f Dockerfile -t <docker-image-name> .
*  $ docker run it -d -p <HOST PORT>:<DOCKER PORT> <docker-image-name>

Example
* $ docker build -f Dockerfile -t espark-swagger-api-docs .
* $ docker run -p 8080:8080 espark-swagger-api-docs
* http://localhost:8080/

* $ docker ps -a
* $ docker stop <container id>


### TO LIST DOWN ALL THE IMAGES
* $ docker images

### TO LIST ALL THE PROCESS
* $ docker ps -a

### TO DELETE ALL THE CONTAINERS WITH VOLUMES
* $ docker rm -vf $(docker ps -a -q)

### TO DELETE ALL THE IMAGES
* $ docker rmi -f $(docker images -a -q)

### Enter the container
* $ docker exec -it <container id> /bin/bash
