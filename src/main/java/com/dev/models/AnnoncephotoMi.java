package com.dev.models;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
@Entity
@Table(name="annoncephoto")
public class AnnoncephotoMi{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idannoncephoto; 
    String photo; 
    int idannonce; 

    public AnnoncephotoMi(int idannoncephoto,String photo,int idannonce){
        setIdannoncephoto(idannoncephoto);
        setPhoto(photo);
        setIdannonce(idannonce);
    }
    
    public int getIdannoncephoto(){
        return this.idannoncephoto;
    }
    public void setIdannoncephoto(int idannoncephoto){
        this.idannoncephoto=idannoncephoto;
    }
    public String getPhoto(){
        return this.photo;
    }
    public void setPhoto(String photo){
        this.photo=photo;
    }
    public int getIdannonce(){
        return this.idannonce;
    }
    public void setIdannonce(int idannonce){
        this.idannonce=idannonce;
    }

}
