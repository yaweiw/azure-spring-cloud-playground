@ECHO OFF

cd ../ && mvn clean package -Dmaven.test.skip=true -Pfull && cd initializr-service && mvn azure-webapp:deploy
