#!/bin/bash

echo && echo "==================== Build Packages ======================"
cd ../ && mvn clean package -Dmaven.test.skip=true && cd -

echo && echo "==================== Collect packages ===================="
./collect_packages.sh*

echo && echo "==================== Start Docker ========================"
docker-compose up --build

