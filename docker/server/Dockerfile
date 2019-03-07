FROM cantara/alpine-openjdk-jdk8:latest
COPY server server
COPY application.yml server/config/application.yml
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar server/java-box-spring-boot.jar" ]
