package com.github.bernabaris.producer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.bernabaris.producer.config.KafkaConfiguration;
import com.github.bernabaris.producer.model.User;
import com.github.bernabaris.producer.repository.MongoRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MongoService {

    @Autowired
    private MongoRepository mongoRepository;

    @Autowired
    private KafkaConfiguration kafkaConfiguration;

    public List<User> getAllDocumentsAsJson() {
        List<Document> documents = mongoRepository.getAllDocuments();

        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Pretty print için Gson

        return documents.stream()
                .map(document -> {
                    String uglyJsonString = document.toJson();
                    JsonElement jsonElement = JsonParser.parseString(uglyJsonString);
                    User user = gson.fromJson(jsonElement, User.class);
                    user.setName(document.getString("Name:"));
                    user.setAddress(document.getString("Address:"));
                    user.setEmail(document.getString("Email:"));
                    user.setPhone(document.getString("Phone:"));
                    user.setCompany(document.getString("Company:"));
                    user.setCountry(document.getString("Country:"));
                    return user;
                })
                .collect(Collectors.toList());
    }

    List<User> oldUserList = new ArrayList<>();

    @Scheduled(fixedDelay = 10000)
    public void checkForNewDocuments() {
        List<User> users = getAllDocumentsAsJson();
        users.removeAll(oldUserList);
        if (users.isEmpty()) {
            log.info("No new user.");
        } else {
            users.forEach(user -> {
                log.info("New user: {}", user);
                kafkaConfiguration.sendMessage(user.toString());
                oldUserList.add(user);
            });
        }
    }
}
