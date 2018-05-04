@ECHO OFF

cd ../ && mvn clean package -Dmaven.test.skip=true -Pfull && cd initializr-service
docker push plincar.azurecr.io/azure-spring-cloud:latest && mvn azure-webapp:deploy
