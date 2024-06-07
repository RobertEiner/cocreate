FROM eclipse-temurin:17-jdk-focal

EXPOSE 8080

ADD target/sb-cocreate.jar sb-cocreate.jar

ENTRYPOINT ["java", "-jar", "sb-cocreate.jar"]

