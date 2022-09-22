FROM maven:3.6.0-jdk-11-slim
LABEL maintainer="suryajit7"

ADD src                                     src
ADD pom.xml                                 pom.xml
RUN mvn clean install spring-boot:repackage

ADD target/springfield-dockerized.jar       springfield-dockerized.jar
ENTRYPOINT ["java", "-jar", "springfield-dockerized.jar"]