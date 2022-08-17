FROM openjdk:11-jre
EXPOSE 8085
VOLUME /tmp
ADD /target/*.jar summer-activity.jar
ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /summer-activity.jar"]
