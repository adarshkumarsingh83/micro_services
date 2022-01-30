
# springboot-resilience4j-rate-limiter

---


## [client-appication](./client-application)
## [server-application](./server-application)

## To access end point 
* $ curl localhost:9090/list
```
{"msg":"response from remote server","data":["adarsh","radha","amit"]}
```

## To Access endpoitn via client 
* $ curl localhost:8080/name-list
```
{"msg":"response from remote server","data":["adarsh","radha","amit"]}
```

## Shut Down the remote server 
* $ curl localhost:8080/name-list
```
{"msg":"response from local server","data":["sonu","radha","monu"]}
```