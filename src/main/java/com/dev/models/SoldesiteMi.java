package com.dev.models;
import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
@Entity
@Table(name="soldesite")
public class SoldesiteMi{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idsoldesite;
    double solde;
    LocalDateTime dateupdate; 

    public SoldesiteMi(){ }
    
    public int getIdsoldesite(){
        return this.idsoldesite;
    }
    public void setIdsoldesite(int idsoldesite){
        this.idsoldesite=idsoldesite;
    }
    public double getSolde(){
        return this.solde;
    }
    public void setSolde(double solde){
        this.solde=solde;
    }
    public LocalDateTime getDateupdate(){
        return this.dateupdate;
    }
    public void setDateupdate(LocalDateTime dateupdate){
        this.dateupdate=dateupdate;
    }

}
