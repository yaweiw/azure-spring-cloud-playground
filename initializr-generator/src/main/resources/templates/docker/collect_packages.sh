#!/bin/bash

target_dir=../
packages_dir=packages
basename=$(basename $(pwd))
execute_script=docker_run.sh

if [ -d $packages_dir ]; then
    rm -rf $packages_dir
fi

mkdir $packages_dir
cp -v $execute_script $packages_dir

for dir in $(ls $target_dir)
do
    sub_dir=$target_dir/$dir

    if [[ $sub_dir = *$basename* ]]; then
        continue
    elif [ -d $sub_dir ]; then
        cp -v $sub_dir/target/*.jar $packages_dir
    fi
done
