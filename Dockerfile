FROM gradle:8.8-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:11
EXPOSE 8080
EXPOSE 8443
RUN mkdir /app
COPY --from=build /home/gradle/src/api/build/libs/*.jar /app/glideapp-ktor.jar
COPY --from=build /home/gradle/src/ssl_keystore.jks /app/ssl_keystore.jks
ENTRYPOINT ["java","-jar","/app/glideapp-ktor.jar","-sslKeyStore=/app/ssl_keystore.jks"]
