package com.dev.models;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
@Entity
@Table(name="motif")
public class MotifMi{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idmotif; 
    String nommotif; 
    int codemotif; 

    public MotifMi(){ }
    
    public int getIdmotif(){
        return this.idmotif;
    }
    public void setIdmotif(int idmotif){
        this.idmotif=idmotif;
    }
    public String getNommotif(){
        return this.nommotif;
    }
    public void setNommotif(String nommotif){
        this.nommotif=nommotif;
    }
    public int getCodemotif(){
        return this.codemotif;
    }
    public void setCodemotif(int codemotif){
        this.codemotif=codemotif;
    }

}
