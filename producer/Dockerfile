FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

FROM openjdk:17-slim AS runner
WORKDIR /app
COPY --from=build /app/target/producer-0.0.1-SNAPSHOT.jar producer-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "producer-0.0.1-SNAPSHOT.jar"]

EXPOSE 8081
