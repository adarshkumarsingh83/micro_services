# client-application 

---

### To Build the code 
* mvn clean package 

### To Run the code 
* mvn spring-boot:run 

### to access the health endpoint 
* http://localhost:8080/actuator/health
```  
 {
  "status": "UP",
  "components": {
    "circuitBreakers": {
      "status": "UP",
      "details": {
        "dataService": {
          "status": "UP",
          "details": {
            "failureRate": "-1.0%",
            "failureRateThreshold": "50.0%",
            "slowCallRate": "-1.0%",
            "slowCallRateThreshold": "100.0%",
            "bufferedCalls": 3,
            "slowCalls": 0,
            "slowFailedCalls": 0,
            "failedCalls": 1,
            "notPermittedCalls": 0,
            "state": "CLOSED" 
          }
        }
      }
    },
    "diskSpace": {
      "status": "UP",
      "details": {
        "total": 499963174912,
        "free": 315711868928,
        "threshold": 10485760,
        "exists": true
      }
    },
    "ping": {
      "status": "UP"
    }
  }
}
```

### To Hit the Api 
* curl -X GET http://localhost:8080/name-list
* start the server and client application 
* normal execution without retry hit 
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
* stop the server application then try to hitting the service after some call it will send response from fallback 
* abnormal execution with circuit breaker 
```
{
  "msg": "response from local server :=> remote server is down ",
  "data": [
    "sonu",
    "radha",
    "monu"
  ]
}
```
