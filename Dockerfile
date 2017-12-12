FROM java:openjdk-8-alpine
VOLUME /tmp
ADD target/account-service-1.0.0.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
