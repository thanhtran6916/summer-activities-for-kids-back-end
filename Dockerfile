FROM openjdk:11-jre
COPY /target/*.jar /summer-activity.jar
EXPOSE 8085
CMD ["java", "-jar" ,"/summer-activity.jar"]
