FROM openjdk:17-jdk-alpine
MAINTAINER Vladislav Zheltyshev
COPY target/pastecase-0.0.1-SNAPSHOT.jar pastecase.jar
ENTRYPOINT ["java", "-jar", "/pastecase.jar"]