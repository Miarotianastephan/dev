package com.dev.model.message;

import java.security.Timestamp;
import java.util.ArrayList;

import org.bson.Document;

import com.dev.model.user.User;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Message {
    String idMessage;
    User userSend;
    User userReceive;
    String contenu;
    int typemessage;
    Timestamp dateHeureMessage;

    public void envoyerMessage(String idSender, String idReceive) {
        ArrayList<String> result=new ArrayList<String>();
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {

            // Accédez à la base de données et à la collection
            MongoDatabase database = mongoClient.getDatabase("voitureoccas");
            MongoCollection<Document> collection = database.getCollection("message");

            // Exécutez une requête pour récupérer des documents
            collection.find().forEach(document -> {
                // Traitez chaque document ici
                result.add(document.toJson());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
