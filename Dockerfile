FROM gradle:jdk11 AS package

RUN apt-get update && apt-get install -y \
    curl \
    jq

RUN mkdir -p /app
WORKDIR /app

COPY build.gradle                           .
COPY gradlew                                .
COPY gradlew.bat                            .
COPY settings.gradle                        .
COPY healthcheck.sh                         .
COPY src                                    ./src
COPY build                                  ./build
COPY testng-7.5.jar                         ./build/libs/

RUN gradle build -x test

WORKDIR /app/

ENTRYPOINT ["/bin/sh"]
CMD ["healthcheck.sh"]

FROM fabric8/java-alpine-openjdk11-jre AS testrun

RUN mkdir -p /jar
WORKDIR /jar/

COPY --from=package /app/build/libs/springfield.jar                         .
COPY --from=package /app/build/libs/springfield-dockerized-plain.jar        .
COPY --from=package /app/build/libs                                         ./libs

COPY src/main/resources/application.properties                              ./src/main/resources/application.properties
COPY src/main/resources/encrypted.properties                                ./src/main/resources/encrypted.properties
COPY src/main/resources/datasets                                            ./src/main/resources/datasets
COPY src/main/resources/scripts                                             ./src/main/resources/scripts

COPY src/test/java/com/automation/framework/gui/testng.xml                  ./src/test/java/com/automation/framework/gui/testng.xml
COPY src/test/java/com/automation/framework/gui/testng.xml                  .
COPY src/test/resources/allure.properties                                   ./src/test/resources/allure.properties
COPY src/test/resources/junit-platform.properties                           ./src/test/resources/junit-platform.properties
COPY src/test/resources/schemas                                             ./src/test/resources/schemas

COPY run.sh                                                                 .

WORKDIR /jar/

ENV BROWSER=chrome
ENV HUB_HOST=hub
ENV MODULE=testng.xml

ENTRYPOINT ["/bin/sh"]
CMD ["run.sh"]