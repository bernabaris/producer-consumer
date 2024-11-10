# kafka-producer-consumer

Simple CDC Application Development Project with Apache Kafka

This project includes two Spring Boot applications:

Application A: pulls data from MongoDB and sends it to Apache Kafka. (producer)

Application B: consumes this data from Kafka. (consumer)

## Setup
To run the project, follow these steps:

1. Clone the project:
   ```sh
   git clone https://github.com/bernabaris/producer-consumer.git
   ```
2. Add consumer and producer apps as maven project.

3. Start the Kafka and MongoDB services, use the following command in the docker/kafka & docker/mongodb folder.
 ```sh
   docker compose up -d
   ```
4. Create docker images in the consumer and producer directories.
```sh
   docker build -t producer:1.0.0 .
   docker build -t consumer:1.0.0 .
   ```
5. You should see JAR files generated in both consumer/target and producer/target directories.