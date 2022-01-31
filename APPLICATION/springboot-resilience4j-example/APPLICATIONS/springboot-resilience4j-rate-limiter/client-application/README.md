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
* normal execution 
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
* hit more than 2 request within 5 sec 
* abnormal execution with rate limiter 
```
{
  "msg": "response from local server :=> too many request rate limit apply ",
  "data": [
    "sonu",
    "radha",
    "monu"
  ]
}
```
