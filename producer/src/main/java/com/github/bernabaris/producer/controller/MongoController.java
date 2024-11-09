package com.github.bernabaris.producer.controller;

import com.github.bernabaris.producer.model.User;
import com.github.bernabaris.producer.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MongoController {

    @Autowired
    private MongoService mongoService;

    @GetMapping("/documents")
    public List<User> getAllDocuments() {
        return mongoService.getAllDocumentsAsJson();
    }
}
