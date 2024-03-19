```
Consul is an open-source key-value store. It is used for use cases such as service discovery, config management, etc. Consul enables detecting the deployment of new services, changes to existing ones, and provides real-time agent health to reduce downtime.
```

$ docker run -d --name consul-1 -p 8500:8500 -e CONSUL_BIND_INTERFACE=eth0 consul

#  To open the consul home page in web 
http://localhost:8500/ui/

$ to build the application 
$ mvn clean package 
$ mvn spring-boot:run 

$ curl http://localhost:8081/api/client/massage
