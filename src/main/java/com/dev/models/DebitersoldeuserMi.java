package com.dev.models;
import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
@Entity
@Table(name="debitersoldeuser")
public class DebitersoldeuserMi{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int iddebit; 
    double montantd; 
    LocalDateTime dated; 
    int idmotif; 
    int idsoldeuser; 

    public DebitersoldeuserMi(){ }
    
    public DebitersoldeuserMi(int iddebit, double montantd, LocalDateTime dated, int idmotif, int idsoldeuser) {
        setIddebit(iddebit);
        setMontantd(montantd);
        setDated(dated);
        setIdmotif(idmotif);
        setIdsoldeuser(idsoldeuser);
    }

    public int getIddebit(){
        return this.iddebit;
    }
    public void setIddebit(int iddebit){
        this.iddebit=iddebit;
    }
    public double getMontantd(){
        return this.montantd;
    }
    public void setMontantd(double montantd){
        this.montantd=montantd;
    }
    public LocalDateTime getDated(){
        return this.dated;
    }
    public void setDated(LocalDateTime dated){
        this.dated=dated;
    }
    public int getIdmotif(){
        return this.idmotif;
    }
    public void setIdmotif(int idmotif){
        this.idmotif=idmotif;
    }
    public int getIdsoldeuser(){
        return this.idsoldeuser;
    }
    public void setIdsoldeuser(int idsoldeuser){
        this.idsoldeuser=idsoldeuser;
    }

}
