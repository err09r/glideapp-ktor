FROM openjdk:11
EXPOSE 8080:8080
RUN mkdir /app
COPY ./api/build/libs/*-all.jar /app/glideapp-ktor.jar
ENTRYPOINT ["java","-jar","/app/glideapp-ktor.jar"]
