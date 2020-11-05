#!/bin/sh
Path="/opt/management"
File="/opt/management/management-0.0.1.jar"
BakFile="/opt/management/management-0.0.1.jar.bak"

# 如果路径不存在，创建目录
if [ ! -d "$Path"]; then
mkdir "$Path"
fi

# 判断文件是否存在
if [ ! -f "$File" ]; then
    # 备份文件如果存在，删除备份文件
    if [ ! -f "$BakFile" ]; then
        rm -f "$BakFlie"
    fi
    mv "$File" "$BakFlie"
fi