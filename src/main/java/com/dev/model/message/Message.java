package com.dev.model.message;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;

import com.dev.model.user.User;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "message")
public class Message {
    @Id
    String idMessage;
    User userSend;
    User userReceive;
    String contenu;
    int typemessage;
    String dateHeureMessage;
}
