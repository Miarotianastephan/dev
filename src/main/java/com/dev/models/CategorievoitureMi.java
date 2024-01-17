package com.dev.models;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
@Entity
@Table(name="categorievoiture")
public class CategorievoitureMi{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idcategorievoiture; 
    int idcategorie; 
    int idvoitureinfo;

    public CategorievoitureMi(){ }
    public CategorievoitureMi(int idcategorievoiture,int idcategorie,int idvoitureinfo){
        setIdcategorievoiture(idcategorievoiture);
        setIdcategorie(idcategorie);
        setIdvoitureinfo(idvoitureinfo);
    }
    public int getIdcategorievoiture(){
        return this.idcategorievoiture;
    }
    public void setIdcategorievoiture(int idcategorievoiture){
        this.idcategorievoiture=idcategorievoiture;
    }
    public int getIdcategorie(){
        return this.idcategorie;
    }
    public void setIdcategorie(int idcategorie){
        this.idcategorie=idcategorie;
    }
    public int getIdvoitureinfo(){
        return this.idvoitureinfo;
    }
    public void setIdvoitureinfo(int idvoitureinfo){
        this.idvoitureinfo=idvoitureinfo;
    }

}
