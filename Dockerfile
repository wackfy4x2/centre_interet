FROM openjdk:23-jdk-slim
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*
WORKDIR /usr/src/centre_interet
COPY pom.xml .
# RUN mvn dependency:resolve
COPY src ./src
RUN mvn package -DskipTests -B
EXPOSE 8080
CMD ["java", "-jar", "target/centre_interet-0.0.1-SNAPSHOT.jar"]