# Kafka Manager

A Micronaut application that provides a REST API to interact with Kafka.

## Prerequisites

- Java 23 or later
- Maven 3.6 or later
- Kafka running locally on port 9092 (or update the configuration accordingly)

## Building the Application

```bash
mvn clean package
```

## Running the Application

```bash
java -jar target/kafka-manager-1.0-SNAPSHOT.jar
```

## API Endpoints

### List Kafka Topics

```
GET http://localhost:8080/api/kafka/topics
```

This endpoint returns a list of all Kafka topics in the cluster.

## Configuration

The application can be configured by modifying `src/main/resources/application.yml`. The default configuration assumes Kafka is running locally on port 9092. 