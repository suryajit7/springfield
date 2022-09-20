FROM maven:3.6.0-jdk-11-slim AS package
LABEL maintainer="suryajit7"

RUN apt-get update && apt-get install -y \
    curl \
    jq

RUN mkdir -p /app
WORKDIR /app

COPY pom.xml                          .
COPY healthcheck.sh                   .
RUN mvn -e -B dependency:resolve

COPY src                              ./src
RUN mvn package -DskipTests

WORKDIR /app/

ENTRYPOINT ["/bin/sh"]
CMD ["healthcheck.sh"]

FROM fabric8/java-alpine-openjdk11-jre AS testrun

RUN mkdir -p /jar
WORKDIR /jar/

COPY --from=package /app/target/springfield-dockerized.jar      .
COPY --from=package /app/target/libs                            ./libs

COPY src/main/resources/application.properties                  ./src/main/resources/application.properties
COPY src/test/resources/junit-platform.properties               ./src/test/resources/junit-platform.properties

WORKDIR /jar/

ENV BROWSER=chrome
ENV HUB_HOST=hub
ENV MODULE=testng.xml

ENTRYPOINT ["java", "-jar", "springfield-dockerized.jar"]