FROM maven:3.6.0-jdk-11 AS package

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

COPY --from=package /app/target/dockerized-springfield.jar          .
COPY --from=package /app/target/dockerized-springfield-tests.jar    .
COPY --from=package /app/target/libs                                ./libs

COPY src/main/resources                                             ./src/main/resources/
COPY src/test/resources                                             ./src/test/resources/

COPY testng.xml                                                     .
COPY run.sh                                                         .

WORKDIR /jar/

ENV BROWSER=chrome
ENV HUB_HOST=hub
ENV MODULE=testng.xml

ENTRYPOINT ["/bin/sh"]
CMD ["run.sh"]