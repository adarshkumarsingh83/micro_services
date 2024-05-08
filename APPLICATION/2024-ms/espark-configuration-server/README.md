# epsark-configuration-server

----

* to specify the files in various format names 
  * {application}, which maps to spring.application.name on the client side.
  * {profile}, which maps to spring.profiles.active on the client (comma-separated list).
  * {label}, which is a server side feature labelling a "versioned" set of config files.
```
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
```

* for local filesystem 
  * linux example         file://${user.home}/config-repo
  * windows example,      file:///${user.home}/config-repo

* local git repo process 
````
  $ cd $HOME
  $ mkdir config-repo
  $ cd config-repo
  $ git init .
  $ echo info.foo: bar > application.properties
  $ git add -A .
  $ git commit -m "Add application.properties"
````

* To Build
    * mvn clean package

* To run
    * mvn spring-boot:run

* config server url 
  * http://localhost:8090/application-default.properties

* [reference](https://docs.spring.io/spring-cloud-config/reference/server.html)
