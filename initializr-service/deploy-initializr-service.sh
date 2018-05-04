#!/bin/bash

cd ../ && mvn clean package -Dmaven.test.skip=true -Pfull && cd -
docker push plincar.azurecr.io/azure-spring-cloud:latest && mvn azure-webapp:deploy

