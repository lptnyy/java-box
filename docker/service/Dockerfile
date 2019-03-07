FROM cantara/alpine-openjdk-jdk8:latest
COPY service service
COPY application.yml service/config/application.yml
RUN  mkdir config
COPY zk.properties config/zk.properties
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar service/java-box-api-service.jar" ]
