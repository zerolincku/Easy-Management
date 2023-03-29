#!/bin/sh
Path="/opt/management"
File="management-1.0.1.jar"

# 如果路径不存在，创建目录
if [ ! -d "$Path" ]; then
  mkdir -p "$Path"
fi

# 判断文件是否存在 
if [ -f "$Path/$File" ]; then
  # 备份文件如果存在，删除备份文件
  if [ -f "$Path/$File.bak" ]; then
    rm -f "$Path/$File.bak"
  fi
  # 备份项目
  mv "$Path/$File" "$Path/$File.bak"
fi
# 上传制品文件
cp target/management-*.jar "$Path/$File"
systemctl restart management
