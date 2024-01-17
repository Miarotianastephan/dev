package com.dev.models;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.Table;
// import javax.persistence.Entity;
public class AnnoncedetailMi_v{
    int idannonce; 
    double prixvente; 
    String descriptions; 
    int statusvente; 
    int etat; 
    LocalDateTime dateannonce; 
    int idlieu; 
    String nomlieu; 
    int idvoitureinfo; 
    String nomvoiture; 
    int nombreplace; 
    double kilometrage; 
    int transmission; 
    double vitesse; 
    int iduser; 
    String nomuser; 
    String prenomuser; 
    int idcarburant; 
    String nomcarburant; 
    int idmarque; 
    String nommarque; 
    int idmodel; 
    String nommodel; 
    int idcategorie; 
    String nomcategorie; 
    Date datevente; 
    Date datemodifstatus;
    int idannoncefavoris; //0 raha tsy favoriany io
    int idannoncephoto;
    String photo;
    //select ad_v.*,af.idannoncefavoris from annnoncedetail_v as left join annoncefavoris as af on (ad_v.idannonce=af.idannonce and af.iduser= 'iduserconnect' ) ;
    public AnnoncedetailMi_v(){ }
    
    public int getIdannonce(){
        return this.idannonce;
    }
    public void setIdannonce(int idannonce){
        this.idannonce=idannonce;
    }
    public double getPrixvente(){
        return this.prixvente;
    }
    public void setPrixvente(double prixvente){
        this.prixvente=prixvente;
    }
    public String getDescriptions(){
        return this.descriptions;
    }
    public void setDescriptions(String descriptions){
        this.descriptions=descriptions;
    }
    public int getStatusvente(){
        return this.statusvente;
    }
    public void setStatusvente(int statusvente){
        this.statusvente=statusvente;
    }
            // ---statusvente : 0 : vendu /10 : non vendu
    // ---etat : 0:encour demande / 10 :accepter / 20: refuser
    public String getStatusventeString(){
        if(this.statusvente==0){
            return "vendu";
        }else if(this.statusvente==10){
            return "disponible";
        }else{ return ""; }
    }
    public int getEtat(){
        return this.etat;
    }
    public void setEtat(int etat){
        this.etat=etat;
    }
    // ---statusvente : 0 : vendu /10 : non vendu
// ---etat : 0:encour demande / 10 :accepter / 20: refuser
    public String getEtatString(){
        if(this.etat==0){
            return "encour de demande";
        }else if(this.etat==10){
            return "acceptée";
        }else if(this.etat==20){
            return "refusée";
        }else{ return ""; }
    }
    public LocalDateTime getDateannonce(){
        return this.dateannonce;
    }
    public void setDateannonce(LocalDateTime dateannonce){
        this.dateannonce=dateannonce;
    }
    public int getIdlieu(){
        return this.idlieu;
    }
    public void setIdlieu(int idlieu){
        this.idlieu=idlieu;
    }
    public String getNomlieu(){
        return this.nomlieu;
    }
    public void setNomlieu(String nomlieu){
        this.nomlieu=nomlieu;
    }
    public int getIdvoitureinfo(){
        return this.idvoitureinfo;
    }
    public void setIdvoitureinfo(int idvoitureinfo){
        this.idvoitureinfo=idvoitureinfo;
    }
    public String getNomvoiture(){
        return this.nomvoiture;
    }
    public void setNomvoiture(String nomvoiture){
        this.nomvoiture=nomvoiture;
    }
    public int getNombreplace(){
        return this.nombreplace;
    }
    public void setNombreplace(int nombreplace){
        this.nombreplace=nombreplace;
    }
    public double getKilometrage(){
        return this.kilometrage;
    }
    public void setKilometrage(double kilometrage){
        this.kilometrage=kilometrage;
    }
    public int getTransmission(){
        return this.transmission;
    }
    public void setTransmission(int transmission){
        this.transmission=transmission;
    }
    public double getVitesse(){
        return this.vitesse;
    }
    public void setVitesse(double vitesse){
        this.vitesse=vitesse;
    }
    public int getIduser(){
        return this.iduser;
    }
    public void setIduser(int iduser){
        this.iduser=iduser;
    }
    public String getNomuser(){
        return this.nomuser;
    }
    public void setNomuser(String nomuser){
        this.nomuser=nomuser;
    }
    public String getPrenomuser(){
        return this.prenomuser;
    }
    public void setPrenomuser(String prenomuser){
        this.prenomuser=prenomuser;
    }
    public int getIdcarburant(){
        return this.idcarburant;
    }
    public void setIdcarburant(int idcarburant){
        this.idcarburant=idcarburant;
    }
    public String getNomcarburant(){
        return this.nomcarburant;
    }
    public void setNomcarburant(String nomcarburant){
        this.nomcarburant=nomcarburant;
    }
    public int getIdmarque(){
        return this.idmarque;
    }
    public void setIdmarque(int idmarque){
        this.idmarque=idmarque;
    }
    public String getNommarque(){
        return this.nommarque;
    }
    public void setNommarque(String nommarque){
        this.nommarque=nommarque;
    }
    public int getIdmodel(){
        return this.idmodel;
    }
    public void setIdmodel(int idmodel){
        this.idmodel=idmodel;
    }
    public String getNommodel(){
        return this.nommodel;
    }
    public void setNommodel(String nommodel){
        this.nommodel=nommodel;
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
    public Date getDatevente(){
        return this.datevente;
    }
    public void setDatevente(Date datevente){
        this.datevente=datevente;
    }
    public Date getDatemodifstatus(){
        return this.datemodifstatus;
    }
    public void setDatemodifstatus(Date datemodifstatus){
        this.datemodifstatus=datemodifstatus;
    }
    public int getIdannoncefavoris() {
        return idannoncefavoris;
    }
    public void setIdannoncefavoris(int idannoncefavoris) {
        this.idannoncefavoris = idannoncefavoris;
    }   
    public int getIdannoncephoto() {
        return idannoncephoto;
    }
    public void setIdannoncephoto(int idannoncephoto) {
        this.idannoncephoto = idannoncephoto;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public boolean estfavoris(){
        if(idannoncefavoris<=0){ return false; }
        else{ return true; }
    }
}
