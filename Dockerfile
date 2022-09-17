FROM openjdk:11
LABEL maintainer="suryajit7"
ADD target/springfield-dockerized.jar springfield-dockerized.jar
ENTRYPOINT ["java", "-jar", "springfield-dockerized.jar"]