package com.dev.service.implementation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.model.user.User;
import com.dev.service.UserService;
import com.dev.utils.TokenUtils;


@Service
public class UserServiceImpl implements UserService{

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
    
}
