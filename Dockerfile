FROM --platform=linux/amd64 openjdk:17-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENV JAVA_OPTS=""
CMD java $JAVA_OPTS -server -jar app.jar
EXPOSE 8081