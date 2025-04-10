FROM eclipse-temurin:21.0.6_7-jre-ubi9-minimal
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY build/libs/slibs-0.0.1-SNAPSHOT.jar slibs.jar
EXPOSE 3000
ENTRYPOINT exec java $JAVA_OPTS -jar slibs.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar slibs.jar
