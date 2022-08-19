FROM adoptopenjdk/openjdk11:alpine-jre
COPY /target/*.jar /summer-activity.jar
EXPOSE 8085
CMD ["java", "-jar" ,"/summer-activity.jar"]
