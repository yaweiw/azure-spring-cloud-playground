#!/bin/bash

echo && echo "==================== Build Packages ========================="
cd ../ && mvn clean package -Dmaven.test.skip=true

echo && echo "==================== Build docker images ===================="
mvn dockerfile:build

echo && echo "==================== Start Docker ==========================="
cd docker && docker-compose up --build