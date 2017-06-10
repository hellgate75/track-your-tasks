FROM hellgate75/oracle-server-jre:8

MAINTAINER Fabrizio Torelli <hellgate75@gmail.com>

ENV APP_ROOT=/root/app \
  PATH=/root/bin:/root/maven/apache-maven-3.5.0/bin:$PATH \
  APP_ENV=DEV

RUN mkdir /root/bin
COPY app/run-ms.sh /root/bin/run-ms
RUN mkdir -p /root/app/code
COPY app/application-dev.properties /root/app/application-dev.properties
COPY src /root/app/code/src
COPY pom.xml /root/app/code/pom.xml

RUN yum install -y wget curl
COPY target/track-your-tasks-0.0.1-SNAPSHOT-spring-boot.jar /root/app/track-your-tasks.jar
#RUN mkdir /root/maven
#RUN wget http://www-eu.apache.org/dist/maven/maven-3/3.5.0/binaries/apache-maven-3.5.0-bin.tar.gz -O /root/maven/apache-maven.tar.gz
#WORKDIR /root/maven
#RUN tar -xzvf apache-maven.tar.gz && rm -f /root/app/apache-maven.tar.gz
#RUN echo "export PATH=/root/maven/apache-maven-3.5.0/bin:/root/bin:$PATH" >> /root/.bashrc

WORKDIR /root/app

ENTRYPOINT ["/bin/bash", "-c",  "chmod 777 /root/bin/run-ms && run-ms"]
EXPOSE 8090