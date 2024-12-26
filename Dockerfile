FROM ubuntu:20.04

# 安装必要的依赖
RUN apt-get update && apt-get install -y \
    libstdc++6 \
    && rm -rf /var/lib/apt/lists/*

# 将构建好的原生可执行文件复制到容器中
COPY target/emby_link /app/emby_link

# 设置工作目录
WORKDIR /app

# 暴露端口
EXPOSE 8080

# 启动应用
#ENTRYPOINT ["/app/emby_link"]