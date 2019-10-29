# 基础镜像(项目运行环境)
FROM registry-vpc.cn-hangzhou.aliyuncs.com/pro-mam/java-base:8
# 拷贝jar包到镜像(Jar包名称与项目打包后Jar包名称保持一致)
ADD *.jar /app.jar
# 开放项目端口
EXPOSE 8080
# 项目启动
ENTRYPOINT java -jar $JAVA_OPTS /app.jar