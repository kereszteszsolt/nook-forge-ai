FROM maven:3.9.16-eclipse-temurin-21 AS build

WORKDIR /workspace/apps/api

COPY apps/api/.mvn .mvn
COPY apps/api/mvnw apps/api/pom.xml ./
COPY packages/brand/brand.json /workspace/packages/brand/brand.json
COPY apps/api/src src

RUN MAVEN_CONFIG= ./mvnw --batch-mode --no-transfer-progress package -DskipTests

FROM eclipse-temurin:21.0.12_8-jre-noble AS runtime

WORKDIR /opt/nookforge

COPY --from=build --chown=10001:10001 /workspace/apps/api/target/nook-forge-api-0.1.0-SNAPSHOT.jar app.jar
COPY --chown=10001:10001 apps/api/docker/healthcheck.sh healthcheck.sh

RUN chmod 0555 healthcheck.sh

USER 10001:10001

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/opt/nookforge/app.jar"]
