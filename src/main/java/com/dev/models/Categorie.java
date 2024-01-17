package com.dev.models;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
@Entity
@Table(name="categorievoiture")
public class Categorie{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idcategorie; 
    String nomcategorie; 

    public Categorie(){ }
    public Categorie(int idcategorie,String nomcategorie){
        setIdcategorie(idcategorie);
        setNomcategorie(nomcategorie);
    }
    public int getIdcategorie(){
        return this.idcategorie;
    }
    public void setIdcategorie(int idcategorie){
        this.idcategorie=idcategorie;
    }
    public String getNomcategorie(){
        return this.nomcategorie;
    }
    public void setNomcategorie(String nomcategorie){
        this.nomcategorie=nomcategorie;
    }

}
