FROM openjdk:17
EXPOSE 8080:8080
RUN mkdir /app
COPY ./build/libs/*-all.jar /app/glide-ktor.jar
ENTRYPOINT ["java","-jar","/app/glide-ktor.jar"]
