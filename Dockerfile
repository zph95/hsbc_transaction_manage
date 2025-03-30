FROM openjdk:21-jdk-slim
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 安装Maven
RUN apt-get update && apt-get install -y maven

RUN mkdir -p /usr/src/app
# 复制源码到容器中
COPY . /usr/src/app

# 设置工作目录
WORKDIR /usr/src/app

# 使用Maven打包项目
RUN mvn clean package -DskipTests

# 暴露端口 8080
EXPOSE 8080

# 运行打包后的jar文件
ENTRYPOINT ["java","-Xmx512m","-jar","/usr/src/app/target/hsbc-0.0.1-SNAPSHOT.jar"]