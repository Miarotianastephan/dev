package com.dev.service.implementation;

import java.util.Optional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.model.message.Message;
import com.dev.model.user.User;
import com.dev.repository.MessageRepository;
import com.dev.repository.UserRepository;
import com.dev.service.UserService;
import com.dev.utils.TokenUtils;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public User loginUser(User user) {
        String mail = user.getMail();
        String mdp = user.getMotdepasse();
        // test statique
        ArrayList<User> data = statiqueData();
        for(User u : data){
            if( (u.getMail().equalsIgnoreCase(mail)==true) && (u.getMotdepasse().equalsIgnoreCase(mdp)==true) ){
                return u;
            }
        }
        return null;
    }

    // Ajout donnée statique 
    public static ArrayList<User> statiqueData(){
        ArrayList<User> data = new ArrayList<>();
        // data.add(new User(1,"Jean","Doe","mail1","pass1"));
        // data.add(new User(2,"Marc","Doe","mail2","pass2"));
        return data;
    }

    @Override
    public Message envoyerMessage(User userSend, User userReceive, String contenu, Timestamp dateTime)
    throws Exception {
        Message message=new Message(null, userSend, userReceive, contenu, 1, dateTime.toString());
        messageRepository.save(message);
        return message;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByMail(email);
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

	@Override
	public List<Message> findByUserSendAndUserReceive(User userSend, User userReceive) {
        return messageRepository.findByUserSendAndUserReceive(userSend, userReceive);
	}
}
