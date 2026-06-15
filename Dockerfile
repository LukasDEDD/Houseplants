FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app



COPY pom.xml .
RUN mvn -B dependency:resolve
COPY src ./src

RUN mvn -B package

FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

ENV APP_ENV=production

RUN useradd -m appuser

COPY --from=build /app/target/*.jar app.jar
COPY resources ./resources
RUN chown -R appuser:appuser /app/resources
USER appuser

HEALTHCHECK --interval=30s --timeout=3s --start-period=5s \
CMD test -f "/app/resources/PlantList.txt" || exit 1

CMD ["java", "-jar", "app.jar"]

