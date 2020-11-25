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
components="components"

#服务所在文件夹（需要修改）
service_dirname="background"
#子服务名称（需要修改）
sub_service_name="bus-server"
#子服务jar包名称（需要修改）
service_jar_name="bus-server-1.0-SNAPSHOT.jar"
#jdk版本（需要修改）
jdk_version="jdk1.8.0_161"

jdk_dir="${proc_dir}/${components}"
jdk_path="${jdk_dir}/${jdk_version}"
proc_name="${proc_dir}/${service_dirname}/${sub_service_name}/${service_jar_name}"
#2>&1的意思是把标准错误（2）重定向到标准输出（1）
#cd ${proc_dir}/${service_dirname}/${sub_service_name}的目的：在服务文件夹运行，满足项目中的相对路径./，如日志文件
proc_startup="cd ${proc_dir}/${service_dirname}/${sub_service_name} && ${jdk_path}/bin/java -server -jar ${proc_name} >/dev/null 2>&1 &"

echo "script_file_abs_path" + ${script_file_abs_path}
echo "script_dir_abs_path" + ${script_dir_abs_path}
echo "proc_dir" + ${proc_dir}
echo "jdk_path" + ${jdk_path}
echo "proc_name" + ${proc_name}
echo "proc_startup" + ${proc_startup}

function proc_id(){
    pid=$(ps aux | grep ${proc_name} | grep -v grep | awk '{print $2}');
}

echo ""
echo "----------启动------------"
echo ""

if [ ! -d "${jdk_path}" ]
then
    echo "解压jdk"
    tar -xzf ${jdk_path}.tar.gz -C ${jdk_dir}
fi
chmod +x ${jdk_path}/bin/*

proc_id 
if [ -n "${pid}" ]
then
    echo "pid": ${pid}
    echo "Starting ${sub_service_name}:connot start ${sub_service_name}:${sub_service_name} (pid ${pid}) is already running.[failed]";
else
   eval ${proc_startup}
   echo ""
   sleep 2;
   proc_id
   [ -z "${pid}" ] &&  echo "Starting ${sub_service_name}:[failed]" || echo "Starting ${sub_service_name}: [ok]. pid:${pid}";
fi
