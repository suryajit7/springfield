FROM maven:3.6.0-jdk-11-slim AS package
LABEL maintainer="suryajit7"

RUN apt-get update && apt-get install -y wget

RUN mkdir -p /app
WORKDIR /app

COPY pom.xml                          .
COPY healthcheck.sh                   .
RUN mvn -e -B dependency:resolve

COPY src                              ./src

RUN mvn clean install spring-boot:repackage -DskipTests

FROM fabric8/java-alpine-openjdk11-jre AS testrun

RUN mkdir -p /jar
WORKDIR /jar/

COPY --from=package /app/target/springfield-dockerized.jar      .
WORKDIR /jar/

ENTRYPOINT ["java", "-jar", "springfield-dockerized.jar"]