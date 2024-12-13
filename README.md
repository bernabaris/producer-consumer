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
7. You should see logs similar to the following in the producer and consumer applications, indicating successful data transfer.
### producer
   ```sh
   2024-11-13T14:51:37.373Z  INFO 1 --- [producer] [   scheduling-1] c.g.b.producer.service.MongoService      : New user: User(name=Sandra Taylor, address=03625 Lane Viaduct
Mariashire, ID 31599, email=anitaroberts@example.org, phone=7553201772, company=Ware PLC, country=Rwanda)
Message User(name=Sandra Taylor, address=03625 Lane Viaduct
Mariashire, ID 31599, email=anitaroberts@example.org, phone=7553201772, company=Ware PLC, country=Rwanda) has been sucessfully sent to the topic: my-topic
2024-11-13T14:51:47.496Z  INFO 1 --- [producer] [   scheduling-1] c.g.b.producer.service.MongoService      : No new user.
   ```
### consumer
   ```sh
   2024-11-13T14:42:57.596Z  INFO 1 --- [consumer] [ntainer#0-0-C-1] o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-1-1, groupId=1] Discovered group coordinator kafka:9092 (id: 2147482646 rack: null)
Received message: User(name=Sandra Taylor, address=03625 Lane Viaduct
Mariashire, ID 31599, email=anitaroberts@example.org, phone=7553201772, company=Ware PLC, country=Rwanda)
   ```
This project was developed to learn Apache Kafka, thanks to my study coach. @tunahansezen 💫 
