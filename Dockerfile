FROM openjdk:11
RUN mkdir /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app-sw-planets.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app-sw-planets.jar"]