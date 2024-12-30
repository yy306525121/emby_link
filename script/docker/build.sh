#!/bin/bash
set -e

VERSION=1.0.0
echo 版本：$VERSION

## 第一步：拷贝jar包
echo "拷贝jar包"
cp ../../target/emby_link.jar emby_link.jar
echo "拷贝jar包完成"
echo "-----------------------------"
echo "开始删除旧镜像"
docker rmi registry.cn-beijing.aliyuncs.com/akafra/emby_link || true
echo "删除旧镜像成功"
echo "-----------------------------"
echo "开始构建新镜像"
docker build -t registry.cn-beijing.aliyuncs.com/akafra/emby_link .
echo "成功构建新镜像"
echo "-----------------------------"
echo "清除拷贝的jar包"
rm emby_link.jar
echo "成功清除拷贝的jar包"
echo "-----------------------------"
echo "开始推送镜像"
docker push registry.cn-beijing.aliyuncs.com/akafra/emby_link
echo "成功推送镜像"
echo "-----------------------------"
echo "开始推送tag镜像"
docker tag registry.cn-beijing.aliyuncs.com/akafra/emby_link registry.cn-beijing.aliyuncs.com/akafra/emby_link:$VERSION
docker push registry.cn-beijing.aliyuncs.com/akafra/emby_link:$VERSION
echo "成功推送tag镜像"