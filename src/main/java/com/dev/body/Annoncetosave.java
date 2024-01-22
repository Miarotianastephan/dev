package com.dev.body;

import org.springframework.web.multipart.MultipartFile;

public class Annoncetosave {
    int idvoitureinfo;
    int idlieu;
    double prixvente;
    String description;
    MultipartFile[] files;

    public Annoncetosave() {
    }

    public Annoncetosave(int idvoitureinfo, int idlieu, double prixvente, String description, MultipartFile[] files) {
        setIdvoitureinfo(idvoitureinfo);
        setIdlieu(idlieu);
        setPrixvente(prixvente);
        setDescription(description);
        setFiles(files);
    }
    public int getIdvoitureinfo() {
        return idvoitureinfo;
    }
    public void setIdvoitureinfo(int idvoitureinfo) {
        this.idvoitureinfo = idvoitureinfo;
    }
    public int getIdlieu() {
        return idlieu;
    }
    public void setIdlieu(int idlieu) {
        this.idlieu = idlieu;
    }
    public double getPrixvente() {
        return prixvente;
    }
    public void setPrixvente(double prixvente) {
        this.prixvente = prixvente;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile[] getFiles() {
        return files;
    }
    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }
     

}
