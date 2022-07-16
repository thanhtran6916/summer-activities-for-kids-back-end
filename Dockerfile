FROM maven:latest
RUN mkdir /summer-activity-backend
WORKDIR /summer-activity-backend
COPY . .
EXPOSE 7200
CMD ["mvn", "spring-boot:run"]
