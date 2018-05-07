@ECHO OFF

cd ../ && mvn clean package -Dmaven.test.skip=true -Pfull && cd initializr-service
docker push ${your-acr-username}/azure-spring-cloud:latest && mvn azure-webapp:deploy
