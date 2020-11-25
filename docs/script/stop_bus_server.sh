#!/bin/bash

export LANG=zh_CN.UTF-8
#脚本的绝对路径
script_file_abs_path=$(readlink -f $0)

#脚本的文件夹绝对路径
script_dir_abs_path=$(dirname ${script_file_abs_path})

#应用文件夹绝对路径
proc_dir=$(dirname ${script_dir_abs_path})

#项目根目录绝对路径
base_dir=$(dirname ${proc_dir})

#服务所在文件夹（需要修改）
service_dirname="background"
#子服务名称（需要修改）
sub_service_name="bus-server"
#子服务jar包名称（需要修改）
service_jar_name="bus-server-1.0-SNAPSHOT.jar"

proc_name="${proc_dir}/${service_dirname}/${sub_service_name}/${service_jar_name}"

function proc_id(){
    pid=$(ps aux | grep ${proc_name} | grep -v grep | awk '{print $2}');
}

echo ""
echo "----------------------"
echo ""

function stop(){
    echo "${sub_service_name} pid: ${pid}"

    for i in 'seq 1 10';
    do 
    proc_id 
    [ -z "${pid}" ] && break
    kill ${pid}
    echo "."
    sleep 2;
    done

    proc_id 
    if [ -z "${pid}" ]
    then
        echo "Stopping ${sub_service_name}:[ ok ]";
    else
        kill -9 ${pid}
    sleep 2;
    proc_id
    if [ -z "${pid}" ]
    then
        echo "Force Stopping ${sub_service_name}:[ ok ]";
    else
         echo "Stopping ${sub_service_name}:[ failed ]";
    fi 
    fi
}

proc_id
[ -z "${pid}" ] &&  echo "Stopping ${sub_service_name}:connot stop ${sub_service_name}:${sub_service_name} is not running. [failed]" || stop;

echo ""