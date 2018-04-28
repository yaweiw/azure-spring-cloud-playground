#!/bin/bash

packages_dir="packages"
time_wait=15

java -jar $packages_dir/demo.cloud-config-server-0.0.1-SNAPSHOT.jar &
sleep $time_wait

java -jar $packages_dir/demo.cloud-eureka-server-0.0.1-SNAPSHOT.jar &
sleep $time_wait

for file in $(ls $packages_dir)
do
    if [[ $file = *.jar ]]; then
        if [[ $file = *cloud-config-server* ]]; then
           continue
        elif [[ $file = *cloud-eureka-server* ]]; then
           continue
        elif [[ $file = *cloud-gateway* ]]; then
           continue
        fi

        java -jar $packages_dir/$file &
        sleep $time_wait
    fi
done

java -jar $packages_dir/demo.cloud-gateway-0.0.1-SNAPSHOT.jar &
sleep $time_wait

heartbeat=5
count=0

while true
do
    echo "docker_run.sh heartbeat -^v- $count"
    count=$[$count+1]

    sleep $heartbeat
done
