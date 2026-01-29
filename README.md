# Kafka Sample Application - Spring Boot and Apache Kafka

## Overview
This project is a sample application demonstrating the integration of Spring Boot with Apache Kafka. It showcases how to
set up a simple Kafka producer and consumer using Spring Boot's capabilities.

## Features
- Spring Boot 4.0.2
- Apache Kafka integration
- RESTful API for sending messages to Kafka
- WebSocket support for real-time message updates
- Kafka consumer that listens for messages and processes them
- Lombok for boilerplate code reduction
- Thymeleaf for server-side rendering
- Docker support for easy deployment
- Maven build configuration

## Prerequisites
- Java 17 or higher
- Apache Kafka server running locally or remotely
- Maven 3.6 or higher
- Docker (optional, for containerization)
- Git (for cloning the repository)
- An IDE such as IntelliJ IDEA or Eclipse (optional, for development)
- cURL or Postman (for testing the REST API)
- Web browser (for testing WebSocket functionality)

## Getting Started

1. **Clone the repository:**
```bash
git clone https://github.com/MrAasoo/kafka-spring-boot.git
cd kafka-spring-boot
```

2. **Build the project using Maven:**
```bash
mvn clean install
```
3. **Run the application:** 
```bash
mvn spring-boot:run
```

4. **Run Kafka locally (if not already running):**

* For Linux/MacOS:
```bash
# Start Zookeeper
./bin/zookeeper-server-start.sh ./config/zookeeper.properties
# Start Kafka server
./kafka-server-start.sh ./config/server.properties
```
* For Windows:
```bash
# Start Zookeeper
.\bin\windows\zookeeper-server-start.bat .\config\windows\zookeeper.properties
# Start Kafka server
.\bin\windows\kafka-server-start.bat .\config\windows\server.properties
```

## Access the application
- Open your web browser and navigate to `http://localhost:8080` to access the application interface.
- REST API:
  - GET `http://localhost:8080/info`
  - POST `http://localhost:8080/app/chat`
- WebSocket endpoint: 
  - CONNECT `ws://localhost:8080/ws-chat/websocket`
  - SUBSCRIBE `/topic/messages`
  - SEND `/app/chat`

## Testing the Application
You can test the REST API using cURL or Postman. Here’s an example using cURL:
```bash
curl -X POST \
  http://localhost:8080/app/chat \
  -H 'Content-Type: application/json' \
  -d '{
    "sender": "User1",
    "content": "Hello, Kafka!"
}'
```

## Kafka Configuration
Make sure to configure your Kafka server settings in the `application.properties` or `application.yml`file located in `src/main/resources/`. Update the following properties as needed:
```yml
spring:  
  kafka:
    bootstrap-servers: localhost:9092
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
    consumer:
      group-id: chat-group
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
      properties:
        # Allows deserializing your Message objects
        spring.json.trusted.packages: "*"
```


## Reference Documentation
For further reference, please consider the following sections: [HELP.md](HELP.md)
