package com.dev.models;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
@Entity
@Table(name="voitureinfo")
public class VoitureinfoMi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idvoitureinfo;
    String nomvoiture;
    int nombreplace;
    double  kilometrage;
    int transmission;
    double vitesse;
    int iduser;
    int idcarburant;
    int idmarque;
    int idmodel;
    int anneefab;
    public VoitureinfoMi() {
    }
    public VoitureinfoMi(int idvoitureinfo,String nomvoiture,int nombreplace,double kilometrage,int transmission,double vitesse,int iduser,int idcarburant,int idmarque,int idmodel,int anneefab){
        setIdvoitureinfo(idvoitureinfo);
        setNomvoiture(nomvoiture);
        setNombreplace(nombreplace);
        setKilometrage(kilometrage);
        setTransmission(transmission);
        setVitesse(vitesse);
        setIduser(iduser);
        setIdcarburant(idcarburant);
        setIdmarque(idmarque);
        setIdmodel(idmodel);
        setAnneefab(anneefab);
    }
    public int getIdvoitureinfo() {
        return idvoitureinfo;
    }
    public void setIdvoitureinfo(int idvoitureinfo) {
        this.idvoitureinfo = idvoitureinfo;
    }
    public String getNomvoiture() {
        return nomvoiture;
    }
    public void setNomvoiture(String nomvoiture) {
        this.nomvoiture = nomvoiture;
    }
    public int getNombreplace() {
        return nombreplace;
    }
    public void setNombreplace(int nombreplace) {
        this.nombreplace = nombreplace;
    }
    public double getKilometrage() {
        return kilometrage;
    }
    public void setKilometrage(double kilometrage) {
        this.kilometrage = kilometrage;
    }
    public int getTransmission() {
        return transmission;
    }
    public void setTransmission(int transmission) {
        this.transmission = transmission;
    }
    public double getVitesse() {
        return vitesse;
    }
    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }
    public int getIduser() {
        return iduser;
    }
    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
    public int getIdcarburant() {
        return idcarburant;
    }
    public void setIdcarburant(int idcarburant) {
        this.idcarburant = idcarburant;
    }
    public int getIdmarque() {
        return idmarque;
    }
    public void setIdmarque(int idmarque) {
        this.idmarque = idmarque;
    }
    public int getIdmodel() {
        return idmodel;
    }
    public void setIdmodel(int idmodel) {
        this.idmodel = idmodel;
    }
    public int getAnneefab() {
        return anneefab;
    }
    public void setAnneefab(int anneefab) {
        this.anneefab = anneefab;
    }
    

}
