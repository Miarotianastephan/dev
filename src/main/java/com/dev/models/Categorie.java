package com.dev.models;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
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
