package com.dev.models;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

import jakarta.persistence.Entity;

import javax.imageio.ImageIO;

import com.dev.exception.ExceptionCar;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;


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
    public byte[] compress(byte[] originalImageData,int taille) throws IOException {
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(originalImageData));
        // Créer un flux de sortie pour stocker l'image compressée
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while (outputStream.size() > taille * 1024) { // 150 Ko
            // Réinitialiser le flux de sortie
            outputStream.reset();

            // Écrire l'image compressée dans le flux de sortie : la il le compresse
            ImageIO.write(originalImage, "jpeg", outputStream);
        }
        // Convertir l'image compressée en tableau de bytes
        ImageIO.write(originalImage, "jpeg", outputStream);
        byte[] compressedImageData = outputStream.toByteArray();
        return compressedImageData;
    }
    public void compressPhotoAndSet()throws Exception{
        byte[] photobyte=Base64.getDecoder().decode(this.photo);
        long tailleEnBytes = photobyte.length;
        double tailleEnMo = (double) tailleEnBytes / (1024 * 1024);
        if(tailleEnMo>=20){ throw new ExceptionCar("taille du fichier ne dois pas depasser de 20 Mo"); }
        byte[] photobytecompress=compress(photobyte, 150);
        String photocompress=Base64.getEncoder().encodeToString(photobytecompress);
        setPhoto(photocompress);
    }

}
