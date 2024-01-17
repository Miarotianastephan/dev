package com.dev.models;
import java.sql.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dev.exception.ExceptionCar;

import javax.persistence.Entity;
@Entity
@Table(name="annoncerefus")
public class AnnoncerefusMi{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idannoncerefus; 
    Date daterefus; 
    int idadmins; 
    int idannonce; 

    public AnnoncerefusMi(){ }
    
    public AnnoncerefusMi(int idannoncerefus, Date daterefus, int idadmins, int idannonce) {
        setIdannoncerefus(idannoncerefus);
        setDaterefus(daterefus);
        setIdadmins(idadmins);
        setIdannonce(idannonce);
    }
    public AnnoncerefusMi(int idannoncerefus, String daterefus, int idadmins, int idannonce)throws Exception {
        setIdannoncerefus(idannoncerefus);
        setDaterefus(daterefus);
        setIdadmins(idadmins);
        setIdannonce(idannonce);
    }
    public int getIdannoncerefus(){
        return this.idannoncerefus;
    }
    public void setIdannoncerefus(int idannoncerefus){
        this.idannoncerefus=idannoncerefus;
    }
    public Date getDaterefus(){
        return this.daterefus;
    }
    public void setDaterefus(Date daterefus){
        this.daterefus=daterefus;
    }
    public void setDaterefus(String daterefus)throws Exception{
        try{
            Date.valueOf(daterefus);
        }catch(Exception e){ throw new ExceptionCar("date "+daterefus+" invalide"); }
        setDaterefus(Date.valueOf(daterefus));
    }
    public int getIdadmins(){
        return this.idadmins;
    }
    public void setIdadmins(int idadmins){
        this.idadmins=idadmins;
    }
    public int getIdannonce(){
        return this.idannonce;
    }
    public void setIdannonce(int idannonce){
        this.idannonce=idannonce;
    }

}
