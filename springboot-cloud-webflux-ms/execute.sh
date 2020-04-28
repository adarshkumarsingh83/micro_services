
#!/bin/bash
echo pwd &
echo 'eureka-server exection' &
./eureka-server/start.sh &
 cd .. &
 echo 'config-server exection' &
./config-server/start.sh &
 cd .. &
 echo 'address-service exection' &
./address-service/start.sh &
 cd .. &
 echo 'employee-service exection' &
./employee-service/start.sh &
 cd .. &
 echo 'api-gateway exection' &
./api-gateway/start.sh &
exit 0