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
* normal execution without circuit breaker 
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
* abnormal execution with circuit breaker 
* stop the server application
* access the same api agian 
```
{
  "msg": "response from local server :=> remote server is down",
  "data": [
    "sonu",
    "radha",
    "monu"
  ]
}
```
### yanl config 
```
1) ‘failure-rate-threshold=80‘ indicates that if 80% of requests are getting failed, open the circuit ie. Make the Circuit Breaker state as Open.
2) ‘sliding-window-size=10‘ indicates that if 80% of requests out of 10 (it means 8) are failing, open the circuit.
3) ‘sliding-window-type=COUNT_BASED‘ indicates that we are using COUNT_BASED sliding window. Another type is TIME_BASED.
4) ‘minimum-number-of-calls=5‘ indicates that we need at least 5 calls to calculate the failure rate threshold.
5) ‘automatic-transition-from-open-to-half-open-enabled=true‘ indicates that don’t switch directly from the open state to the closed state, consider the half-open state also.
6) ‘permitted-number-of-calls-in-half-open-state=4‘ indicates that when on half-open state, consider sending 4 requests. If 80% of them are failing, switch circuit breaker to open state.
7) ‘wait-duration-in-open-state=1s’ indicates the waiting time interval while switching from the open state to the closed state.  
```