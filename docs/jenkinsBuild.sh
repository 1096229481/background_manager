#!/bin/bash
echo "开始执行shell脚本"
#打开jenkins工作目录下git拉取的项目代码目录
cd /usr/lib/jenkins/workspace/backgroundManager

#将打包目录重命名为执行目录
mv package/ background

#将打包好的文件夹压缩
zip -q -r background.zip background/

#将压缩文件复制到运行目录下
cp background.zip /usr/service/backgroundmanager/background.zip

#进入运行目录下
cd /usr/service/backgroundmanager

# 压缩备份现在运行的执行目录
zip -q -r background-$(date +%Y%m%d-%H%M) background

#解压打包的目录覆盖运行的执行目录
unzip -o background.zip

#移除压缩文件，不移除的话，下次执行上面cp的时候需要手动y覆盖
rm -rf background.zip

#进入执行脚本目录
cd script

#执行重启所有的脚本
sh restart_all.sh

echo "执行shell脚本结束"