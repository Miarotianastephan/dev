package com.dev.models;
import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
@Entity
@Table(name="creditersoldeuser")
public class CreditersoldeuserMi{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idcredit; 
    double montantc; 
    LocalDateTime datec; 
    int idcodecredit; 
    int idsoldeuser; 

    public CreditersoldeuserMi(){ }
    
    public CreditersoldeuserMi(int idcredit, double montantc, LocalDateTime datec, int idcodecredit, int idsoldeuser) {
        setIdcredit(idcredit);
        setMontantc(montantc);
        setDatec(datec);
        setIdcodecredit(idcodecredit);
        setIdsoldeuser(idsoldeuser);
    }
    public int getIdcredit(){
        return this.idcredit;
    }
    public void setIdcredit(int idcredit){
        this.idcredit=idcredit;
    }
    public double getMontantc(){
        return this.montantc;
    }
    public void setMontantc(double montantc){
        this.montantc=montantc;
    }
    public LocalDateTime getDatec(){
        return this.datec;
    }
    public void setDatec(LocalDateTime datec){
        this.datec=datec;
    }
    public int getIdcodecredit(){
        return this.idcodecredit;
    }
    public void setIdcodecredit(int idcodecredit){
        this.idcodecredit=idcodecredit;
    }
    public int getIdsoldeuser(){
        return this.idsoldeuser;
    }
    public void setIdsoldeuser(int idsoldeuser){
        this.idsoldeuser=idsoldeuser;
    }

}
