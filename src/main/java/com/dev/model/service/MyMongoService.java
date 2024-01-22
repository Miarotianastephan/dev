package com.dev.model.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class MyMongoService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MyMongoService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // Ajoutez des méthodes pour effectuer des opérations MongoDB ici
}
