package com.github.bernabaris.producer.controller;

import com.github.bernabaris.producer.config.KafkaConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    private final KafkaConfiguration kafkaConfiguration;

    public ProducerController(KafkaConfiguration kafkaConfiguration) {
        this.kafkaConfiguration = kafkaConfiguration;
    }

    @PostMapping("/send")
    public void sendMessageToKafka(@RequestBody String message) {
        kafkaConfiguration.sendMessage(message);
    }
    
}
