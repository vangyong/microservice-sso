# 指定基础镜像  
FROM frolvlad/alpine-oraclejdk8:slim
# 指定作者名和E-mail  
MAINTAINER wangyong "vangyong@126.com"  
#指定挂载点  
VOLUME /tmp  
#将本地文件或目录复制到由dockerfile构建的镜像中  
ADD microservice-common.jar microservice-common.jar  
#设置环境变量  
#ENV JAVA_OPTS=""  
#提供容器运行的默认命令  
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/microservice-common.jar"] 