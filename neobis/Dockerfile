FROM openjdk:17-alpine
VOLUME /tmp
COPY target/*.jar neobis.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/neobis.jar"]