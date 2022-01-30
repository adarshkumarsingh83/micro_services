# server-application 
---

### To build the code 
* mvn clean package 

### to run the application 
* http://localhost:9090/list 


### To hit api 
* curl -X GET http://localhost:9090/list
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