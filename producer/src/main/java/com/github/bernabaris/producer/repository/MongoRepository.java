package com.github.bernabaris.producer.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MongoRepository {

    private static final String DatabaseName = "users";
    private static final String CollectionName = "users";

    @Autowired
    private MongoClient client;

    public List<Document> getAllDocuments() {
        MongoDatabase database = client.getDatabase(DatabaseName);
        MongoCollection<Document> collection = database.getCollection(CollectionName);

        // Return all documents in the collection
        return collection.find().into(new ArrayList<>());
    }
}
