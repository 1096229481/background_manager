#!/bin/bash

script_file=$(readlink -f $0)
script_dir=$(dirname ${script_file})

#执行重启bus_server脚本
sh ${script_dir}/restart_bus_server.sh