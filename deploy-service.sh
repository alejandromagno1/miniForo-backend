#!/bin/sh
sudo systemctl stop mini-foro.service && echo "stop service"
sudo pkill -f 'java' && echo "java dead " || "java no dead "

cd /apps
sudo rm -rf miniForo-backend
sudo git clone https://github.com/alejandromagno1/miniForo-backend.git && echo "cloned"

cd miniForo-backend/
sudo chmod 777 gradlew
sudo chmod 777 start-service.sh
sudo ./gradlew build -x test && echo "builded"
sudo chmod 500 /apps/miniForo-backend/build/libs/miniforo-0.0.1-SNAPSHOT.jar

sudo pkill -f 'java' && echo "java dead " || "java no dead "
sudo systemctl restart mini-foro.service
exit