#!/bin/bash

cd ../ && mvn clean package -Dmaven.test.skip=true -Pfull && cd -
docker push ${your-acr-username}/azure-spring-cloud:latest && mvn azure-webapp:deploy

