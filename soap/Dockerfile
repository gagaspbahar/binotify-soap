FROM maven:3.6.1-jdk-11
COPY . /java-app
WORKDIR /java-app
# RUN mvn install
RUN mvn clean compile assembly:single
CMD ["java", "-jar", "target/soap-1.0-SNAPSHOT-jar-with-dependencies.jar"]