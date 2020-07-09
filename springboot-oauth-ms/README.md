# SPRINGBOOT OAUTH JWT MICROSERVICE ARCHITECTURE PROJECT 
* eureka, api gateway, config server, done
* jwt Oauth2 server  done 
* resource server with (api aggregator,person,address) done
* hystrix and hystrix dashboard tbc 
* spring boot admin tbd
* sluth and zipkin  done 


### generate the jwt.jsk file with below steps

========================================================================================================================
### Generate key store file inside the springboot-oauth2-auth-server resource dir 
  this will generate jwt.jks which contains public and private keys
*  $ cd springboot-oauth2-auth-server/src/main/resources/
*  $ keytool -genkeypair -alias jwt -keyalg RSA --keypass password -keystore jwt.jks --storepass password

### EXAMPLE
*  $ keytool -genkeypair -alias jwt -keyalg RSA --keypass password -keystore jwt.jks --storepass password
````
 What is your first and last name?
   [Unknown]:  adarsh kumar
 What is the name of your organizational unit?
   [Unknown]:  espak org
 What is the name of your organization?
   [Unknown]:  espark edu
 What is the name of your City or Locality?
   [Unknown]:  dallas
 What is the name of your State or Province?
   [Unknown]:  tx
 What is the two-letter country code for this unit?
   [Unknown]:  us
 Is CN=adarsh kumar, OU=espak org, O=espark edu, L=dallas, ST=tx, C=us correct?
   [no]:  yes

 Warning:
 The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore jwt.jks -destkeystore jwt.jks -deststoretype pkcs12".
````
### EXAMPLE
* $ keytool -importkeystore -srckeystore jwt.jks -destkeystore jwt.jks -deststoretype pkcs12
````
Enter source keystore password:
Entry for alias jwt successfully imported.
Import command completed:  1 entries successfully imported, 0 entries failed or cancelled

Warning:
Migrated "jwt.jks" to Non JKS/JCEKS. The JKS keystore is backed up as "jwt.jks.old".
$
````

### EXAMPLE
* $ keytool -list -rfc --keystore jwt.jks | openssl x509 -inform pem -pubkey
````
Enter keystore password:  password
-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtTjXLEch9eU6ujI9CJ2S
b2Ba/q9KXtYAUtNNzBhULspEafg0SQRxPGAM3q5B42kXak5kFy2zCgB6sdJLUUfK
rKYIHThn0JICQcVvCSsLXvINhezjmwnKzo1stk0fThH44HspQUh7qSj51Wo/SDKs
toi0C+WUP20vR1F2L9jh7ezMkb4oGO3CjGTvg9wAAOPl0R+AQFCzNXJE4MieO9iM
qEAMPE4Xsn6RbjcQJtk0lKr1EJ+Vm5uk+8w5GURDzCldbaxccvyzgxY9YcvYedXE
pcdxA+dvG73pOauEUFWoXnGiNeC4rwuG16dz595a1iIt6JwPriWEGMaK2ADVdGhJ
ZwIDAQAB
-----END PUBLIC KEY-----
-----BEGIN CERTIFICATE-----
MIIDdTCCAl2gAwIBAgIEYi+IBDANBgkqhkiG9w0BAQsFADBrMQswCQYDVQQGEwJ1
czELMAkGA1UECBMCdHgxDzANBgNVBAcTBmRhbGxhczETMBEGA1UEChMKZXNwYXJr
IGVkdTESMBAGA1UECxMJZXNwYWsgb3JnMRUwEwYDVQQDEwxhZGFyc2gga3VtYXIw
HhcNMjAwNDE5MDUyMDEzWhcNMjAwNzE4MDUyMDEzWjBrMQswCQYDVQQGEwJ1czEL
MAkGA1UECBMCdHgxDzANBgNVBAcTBmRhbGxhczETMBEGA1UEChMKZXNwYXJrIGVk
dTESMBAGA1UECxMJZXNwYWsgb3JnMRUwEwYDVQQDEwxhZGFyc2gga3VtYXIwggEi
MA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC1ONcsRyH15Tq6Mj0InZJvYFr+
r0pe1gBS003MGFQuykRp+DRJBHE8YAzerkHjaRdqTmQXLbMKAHqx0ktRR8qspggd
OGfQkgJBxW8JKwte8g2F7OObCcrOjWy2TR9OEfjgeylBSHupKPnVaj9IMqy2iLQL
5ZQ/bS9HUXYv2OHt7MyRvigY7cKMZO+D3AAA4+XRH4BAULM1ckTgyJ472IyoQAw8
TheyfpFuNxAm2TSUqvUQn5Wbm6T7zDkZREPMKV1trFxy/LODFj1hy9h51cSlx3ED
528bvek5q4RQVahecaI14LivC4bXp3Pn3lrWIi3onA+uJYQYxorYANV0aElnAgMB
AAGjITAfMB0GA1UdDgQWBBRgGOuhSdpGtx93wAej8z0BZrBRnDANBgkqhkiG9w0B
AQsFAAOCAQEAnYrSpFB0WZIY0ziM7xjBH8gLZKqMGkXhCuobTDSXzQZb5b2K3xne
y5gonR0AHUMElH5vRp1GHBetI3I0NONSEJ0qkCbf/ZnQmnUlTePfOWWm0V+6VJdC
14XnQGG/+6HIqOiOu4260CMloJsUGLTbM4AKiLMRB+cewxqRapg/2uOGYkWGOryv
pQBaEvBlZfyGB1e3HjfavpkEkQaU1ZpzyuaHMWKOGcYUPVkYjiiFEGI7npkGXgHo
JpO47n2DK+QvO0t5sdFCQUnx1Q6qjUIXEBv3invCdevq48Wso6hQ1YHGVvXRaA+E
bMZf3PhvwjM4pNaHd6vYu/lwfMM5ZtUMsw==
-----END CERTIFICATE----- 
```` 
### Moving to PKCS12
  this cmd will prompt for key-store password after execution this cmd will generate jwt.jks copy that file to resource dir of application

* $ keytool -importkeystore -srckeystore jwt.jks -destkeystore jwt.jks -deststoretype pkcs12

### export the public key
```
  after execution of this cmd copy the public key from save for resource server config
  ---- BEGIN PUBLIC KEY ------
  			XXX
  			XXX
  ----- END PUBLIC KEY -------
```
* $ keytool -list -rfc --keystore jwt.jks | openssl x509 -inform pem -pubkey

NOTE :
  for help get check the file keystore-execution.txt
========================================================================================================================


### Start the Eureka Server ###
* $ cd springboot-eureka-server
* $ mvn clean package 
* $ mvn spring-boot:run 
* http://localhost:8761


###  Start the config server ####
* $ cd springboot-config-server
* $ mvn clean package 
* $ mvn spring-boot:run 


#### Start the Auth server ###
* $ cd springboot-oauth2-auth-server
* $ mvn clean package 
* $ mvn spring-boot:run 


### Start the api gateway  
* $ cd springboot-api-gateway 
* $ mvn clean package 
* $ mvn spring-boot:run 


### Start the employee Service ###
* $ cd employee-service
* $ mvn clean package 
* $ mvn spring-boot:run 

#### Start the address service #######
* $ cd address-service
* $ mvn clean package 
* $ mvn spring-boot:run 

### Start the api service #######
* $ cd api-service
* $ mvn clean package 
* $ mvn spring-boot:run 

* $ curl --location --request POST 'localhost:8080/oauth/token' \
--header 'Authorization: Basic ZXNwYXJrLWFwcDpzZWNyZXQ=' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=password' \
--data-urlencode 'username=adarsh' \
--data-urlencode 'password=adarsh'

* $ curl --location --request GET 'localhost:8080/oauth/check_token?token=<TOKEN-VALUE>' \
--header 'Authorization: Basic ZXNwYXJrLWFwcDpzZWNyZXQ='

* $ curl --location --request POST 'localhost:8080/oauth/token' \
  --header 'Authorization: Basic ZXNwYXJrLWFwcDpzZWNyZXQ=' \
  --header 'Content-Type: application/x-www-form-urlencoded' \
  --data-urlencode 'grant_type=password' \
  --data-urlencode 'username=radha' \
  --data-urlencode 'password=radha'

* $ curl --location --request GET 'localhost:8080/oauth/check_token?token=<TOKEN-VALUE>' \
   --header 'Authorization: Basic ZXNwYXJrLWFwcDpzZWNyZXQ='
   
   
* $ curl -v http://localhost:8080/api/message  -H "Authorization: Bearer <Token Value >"  
   
* $ curl -v  http://localhost:8080/api/message -H "Authorization: Bearer <Token Value >"
```Response
{
  "ESPARK-ADDRESS-SERVICE": "Hello from Espark Address ServiceESPARK-HEADER-FROM-API-SERVICE",
  "ESPARK-EMPLOYEE-SERVICE": "Hello from Espark Employee Service ESPARK-HEADER-FROM-API-SERVICE",
  "ESPARK-API-SERVICES": "Hello from Espark Api Service ESPARK-HEADER-FOR-API-SERVICE"
}
```
* $ curl -v  http://localhost:8080/api/address/1 -H "Authorization: Bearer <Token Value >"
```Response
{
  "id": 1,
  "address": {
    "id": 1,
    "streetName": "jpb street",
    "cityName": "jbp",
    "country": "india",
    "type": "ADDRESS"
  }
}
```

* $ curl -v  http://localhost:8080/api/employee/1 -H "Authorization: Bearer <Token Value >"
```Response
{
  "id": 1,
  "employee": {
    "id": 1,
    "firstName": "adarsh",
    "lastName": "kumar",
    "career": "It",
    "type": "EMPLOYEE"
  }
}
```
* $ curl -v  http://localhost:8080/api/details/1 -H "Authorization: Bearer <Token Value >"
```Response
{
  "id": 1,
  "address": {
    "id": 1,
    "streetName": "jpb street",
    "cityName": "jbp",
    "country": "india",
    "type": "ADDRESS"
  },
  "employee": {
    "id": 1,
    "firstName": "adarsh",
    "lastName": "kumar",
    "career": "It",
    "type": "EMPLOYEE"
  }
}
```
* $ curl -v  http://localhost:8080/api/details -H "Authorization: Bearer <Token Value >"
```Response
[
  {
    "id": 1,
    "address": {
      "id": 1,
      "streetName": "jpb street",
      "cityName": "jbp",
      "country": "india",
      "type": "ADDRESS"
    },
    "employee": {
      "id": 1,
      "firstName": "adarsh",
      "lastName": "kumar",
      "career": "It",
      "type": "EMPLOYEE"
    }
  },
  {
    "id": 2,
    "address": {
      "id": 2,
      "streetName": "hyd street",
      "cityName": "hyd",
      "country": "india",
      "type": "ADDRESS"
    },
    "employee": {
      "id": 2,
      "firstName": "radha",
      "lastName": "singh",
      "career": "IT",
      "type": "EMPLOYEE"
    }
  },
  {
    "id": 3,
    "address": {
      "id": 3,
      "streetName": "delhi street",
      "cityName": "delhi",
      "country": "india",
      "type": "ADDRESS"
    },
    "employee": {
      "id": 3,
      "firstName": "amit",
      "lastName": "kumar",
      "career": "Finance",
      "type": "EMPLOYEE"
    }
  }
]
```