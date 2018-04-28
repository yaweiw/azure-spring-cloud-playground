#!/bin/bash

packages_dir="packages"

java -jar $packages_dir/demo.cloud-config-server-0.0.1-SNAPSHOT.jar &
sleep 15

java -jar $packages_dir/demo.cloud-eureka-server-0.0.1-SNAPSHOT.jar &
sleep 15

for dir in $(ls $packages_dir)
do
    if [[ $dir = *.jar ]]; then
        if [[ $dir = *cloud-config-server* ]]; then
           continue
        elif [[ $dir = *cloud-eureka-server* ]]; then
           continue
        elif [[ $dir = *cloud-gateway* ]]; then
           continue
        fi

        java -jar $packages_dir/$dir &
    fi
done

java -jar $packages_dir/demo.cloud-gateway-0.0.1-SNAPSHOT.jar &

sleep 200

