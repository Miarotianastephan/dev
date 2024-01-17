package com.dev.models;
import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
@Entity
@Table(name="tokenuser")
public class TokenuserMi{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idtokenuser; 
    String token; 
    LocalDateTime datedebut; 
    LocalDateTime dateexp; 
    int etats; 
    int iduser; 

    public TokenuserMi(){ }
    
    public int getIdtokenuser(){
        return this.idtokenuser;
    }
    public void setIdtokenuser(int idtokenuser){
        this.idtokenuser=idtokenuser;
    }
    public String getToken(){
        return this.token;
    }
    public void setToken(String token){
        this.token=token;
    }
    public LocalDateTime getDatedebut(){
        return this.datedebut;
    }
    public void setDatedebut(LocalDateTime datedebut){
        this.datedebut=datedebut;
    }
    public LocalDateTime getDateexp(){
        return this.dateexp;
    }
    public void setDateexp(LocalDateTime dateexp){
        this.dateexp=dateexp;
    }
    public int getEtats(){
        return this.etats;
    }
    public void setEtats(int etats){
        this.etats=etats;
    }
    public int getIduser(){
        return this.iduser;
    }
    public void setIduser(int iduser){
        this.iduser=iduser;
    }

}
