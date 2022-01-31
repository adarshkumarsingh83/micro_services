# server-application 
---

### To build the code 
* mvn clean package 

### to run the application 
* start the server and client
* http://localhost:9090/list 
```
{
  "msg": "response from remote server",
  "data": [
    "adarsh",
    "radha",
    "amit"
  ]
}
```

### To hit api 
* start the server and client 
* hit the url muilitple times 
* curl -X GET http://localhost:9090/list
```
{
  "msg": "response from local server :=> remote is not responding on time ",
  "data": [
    "sonu",
    "radha",
    "monu"
  ]
} 
```