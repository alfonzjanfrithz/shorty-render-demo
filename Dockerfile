# Step 1: Build the application
# Start with a base image containing Java runtime and Maven
FROM maven:3.8.4-openjdk-17 as build

# Copy the project files to the container
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

# Set the current working directory
WORKDIR /usr/src/app

# Build the application
RUN mvn clean package

# Step 2: Create the final image
FROM openjdk:17

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Copy the JAR file from the build stage
COPY --from=build /usr/src/app/target/interview-url-shorty-0.0.1-SNAPSHOT.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
