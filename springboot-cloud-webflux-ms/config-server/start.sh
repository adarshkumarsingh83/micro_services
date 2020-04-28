
#!/bin/bash
cd ./config-server &
echo  pwd &
nohup mvn spring-boot:run &
exit 0