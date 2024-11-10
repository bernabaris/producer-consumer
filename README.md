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

3. Start the Kafka and MongoDB services, use the following command.
   ```sh
   cd docker/kafka; docker compose up -d; cd ..
   cd mongodb; docker compose up -d; cd ../..
   ```
4. Create docker images.
   ```sh
   cd producer; docker build -t producer:1.0.0 .; cd ..
   cd consumer; docker build -t consumer:1.0.0 .; cd ..
   ```
5. You should see JAR files generated in both consumer/target and producer/target directories.

6. Use the data_generator.py Python script in the directory to generate a data in the cdc collection in MongoDB.
   ```sh
   cd data-generator
   pip3 install -r requirements.txt
   python3 data_generator.py; cd ..
   ```
