#!/bin/bash

cd ../ && mvn clean package -Dmaven.test.skip=true -Pfull && cd - && mvn azure-webapp:deploy
