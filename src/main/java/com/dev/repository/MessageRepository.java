package com.dev.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.dev.model.message.Message;
import com.dev.model.user.User;
import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    public List<Message> findByUserSendAndUserReceive(User userSend, User userReceive);
}
