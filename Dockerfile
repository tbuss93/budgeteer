FROM openjdk:8-jdk
RUN mkdir /app
COPY . /app
WORKDIR /app
RUN ./gradlew build
EXPOSE 8080
ENTRYPOINT ["./gradlew"]
CMD ["bootRun"]
