FROM eclipse-temurin:17-jdk-focal

# create the working directory for the container
WORKDIR /app

# copy the jar file into the workdir
COPY target/sb-cocreate.jar sb-cocreate.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "sb-cocreate.jar"]

