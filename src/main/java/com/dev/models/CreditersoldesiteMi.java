package com.dev.models;
import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
@Entity
@Table(name="creditersoldesite")
public class CreditersoldesiteMi{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idcredit; 
    double montantc; 
    LocalDateTime datec; 
    int iddebit; 
    int idmotif; 
    int idsoldesite; 

    public CreditersoldesiteMi(){ }
    public CreditersoldesiteMi(int idcredit, double montantc, LocalDateTime datec, int iddebit, int idmotif, int idsoldesite) {
        setIdcredit(idcredit);
        setMontantc(montantc);;
        setDatec(datec);
        setIddebit(iddebit);
        setIdmotif(idmotif);
        setIdsoldesite(idsoldesite);
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
    public int getIddebit(){
        return this.iddebit;
    }
    public void setIddebit(int iddebit){
        this.iddebit=iddebit;
    }
    public int getIdmotif(){
        return this.idmotif;
    }
    public void setIdmotif(int idmotif){
        this.idmotif=idmotif;
    }
    public int getIdsoldesite(){
        return this.idsoldesite;
    }
    public void setIdsoldesite(int idsoldesite){
        this.idsoldesite=idsoldesite;
    }

}
