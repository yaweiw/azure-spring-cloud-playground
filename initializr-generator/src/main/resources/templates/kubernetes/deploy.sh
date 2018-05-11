#!/bin/bash

echo && echo "==================== Build packages, push images and deploy to Azure Kubernetes Service ========================="
cd ..
mvn clean package -Dmaven.test.skip=true -Pkubernetes
mvn dockerfile:build -Pkubernetes
mvn dockerfile:push -Pkubernetes
cd kubernetes
kubectl apply -f kubernetes.yaml