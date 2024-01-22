package com.dev.model.user;

import java.sql.Timestamp;

public class User {
    int idUser;
    String nomUser;
    String prenomUser;
    String mailUser;
    Timestamp nee;
    String pwd;
    public User(int idUser, String nomUser, String prenomUser) {
        this.idUser = idUser;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
    }
}
