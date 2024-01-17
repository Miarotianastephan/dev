package com.dev.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;

import com.dev.body.*;
import com.dev.exception.ExceptionCar;
import com.dev.models.AnnonceBodyMi;
import com.dev.models.AnnoncedetailMi_v;
import com.dev.service.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/mir")
public class MirController {
    
    @Autowired
    private VoitureinfoMiSer serviceVoitureinfoMiSer;
    @Autowired
    private AnnonceMiSer annonceMiSer;
    @Autowired
    private AnnoncedetailMi_vSer annoncedetailMi_vSer;
    @Autowired
    private AnnoncefavorisMiSer annoncefavorisMiSer;
    @Autowired
    private CreditersoldeuserMiSer creditersoldeuserMiSer;
    
    @Value("${file.path}")
    private String imageDirectory;

    @GetMapping(path = "/hello" , produces = "application/json")
    public String getHello(){
        return "Hello All !!";
    }
    @PostMapping("/ajoutinfocar")
    public ResponseEntity<String> ajoutinfocar( @RequestBody InfoCar infoCar) {
        String message="";
        try{
            serviceVoitureinfoMiSer.saveByInfoCar(infoCar);
        }catch(ExceptionCar ec){
            message=ec.getMessage();
            ec.printStackTrace();
        }catch(Exception e){
            message="error";
            e.printStackTrace();
        }
        return ResponseEntity.ok(message);
    }

    @PostMapping("/ajoutannonce") 
    public ResponseEntity<String> ajoutannonce( @RequestBody Annoncesave annoncesave, @RequestPart("files") MultipartFile[] mfiles) {
        String message="";
        try{
            annonceMiSer.insertAnnonce(annoncesave, mfiles,imageDirectory);
        }catch(ExceptionCar ec){
            message=ec.getMessage();
            ec.printStackTrace();
        }catch(Exception e){
            message="error";
            e.printStackTrace();
        }
        return ResponseEntity.ok(message);
    }
    @PostMapping("/getPubAnnoces") //les annonces sauf mes annonces
    public ResponseEntity< List<AnnonceBodyMi> > getPubAnnoces( @RequestParam int iduser,@RequestParam int nbaffiche,@RequestParam int numLineBeforeFirst) {
        List<AnnoncedetailMi_v> lstA= annoncedetailMi_vSer.getAllByNotIduserByNbafficheByNumlineBeforFirst(iduser, nbaffiche, numLineBeforeFirst);
        List<AnnonceBodyMi> lstAB=new AnnonceBodyMi().createListByListAnnoncedetailMi_v(lstA);
        return ResponseEntity.ok(lstAB);
    }
    @PostMapping("/getMesAnnoces")
    public ResponseEntity< List<AnnonceBodyMi> > getMesAnnoces( @RequestParam int iduser,@RequestParam int nbaffiche,@RequestParam int numLineBeforeFirst) {
        List<AnnoncedetailMi_v> lstA= annoncedetailMi_vSer.getAllByIduserByNbafficheByNumlineBeforFirst(iduser, nbaffiche, numLineBeforeFirst);
        List<AnnonceBodyMi> lstAB=new AnnonceBodyMi().createListByListAnnoncedetailMi_v(lstA);
        return ResponseEntity.ok(lstAB);
    }

    @GetMapping("/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws IOException {
        Path imagePath = Paths.get(imageDirectory).resolve(imageName);
        Resource resource = new UrlResource(imagePath.toUri());
        if (resource.exists() && resource.isReadable()) {
            return ResponseEntity.ok().body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/toFavoris")
    public ResponseEntity< String > getPubAnnoces( @RequestParam int iduser,@RequestParam int idannonce) {

        String message="";
        try{
            annoncefavorisMiSer.verifeInsert(iduser, idannonce);
        }catch(ExceptionCar ec){
            message=ec.getMessage();
            ec.printStackTrace();
        }catch(Exception e){
            message="error";
            e.printStackTrace();
        }
        return ResponseEntity.ok(message);
    }

    @PostMapping("/getAnnoncesNonValider")
    public ResponseEntity< List<AnnonceBodyMi> > getAnnoncesNonValider( @RequestParam int iduser,@RequestParam int nbaffiche,@RequestParam int numlineBeforeFirst) {
        List<AnnoncedetailMi_v> lstA= annoncedetailMi_vSer.getAllEncoursByNbafficheByNumlineBeforFirst(nbaffiche,numlineBeforeFirst );
        List<AnnonceBodyMi> lstAB=new AnnonceBodyMi().createListByListAnnoncedetailMi_v(lstA);
        return ResponseEntity.ok(lstAB);
    }

    @PostMapping("/validerAnnonce")
    public ResponseEntity< String > validerAnnonce( @RequestParam int idadmin,@RequestParam int idannonce,@RequestParam String date) {   
        String message="";
        try{
            annonceMiSer.valider(idannonce, date, idadmin);
        }catch(ExceptionCar ec){
            message=ec.getMessage();
            ec.printStackTrace();
        }catch(Exception e){
            message="error";
            e.printStackTrace();
        }
        return ResponseEntity.ok(message);
    }

    @PostMapping("/refuserAnnonce") 
    public ResponseEntity< String > refuserAnnonce( @RequestParam int idadmin,@RequestParam int idannonce,@RequestParam String date) {   
        String message="";
        try{
          annonceMiSer.refuser(idannonce, date, idadmin);
        }catch(ExceptionCar ec){
            message=ec.getMessage();
            ec.printStackTrace();
        }catch(Exception e){
            message="error";
            e.printStackTrace();
        }
        return ResponseEntity.ok(message);
    }

    @PostMapping("/changerStatusAnnonce") 
    public ResponseEntity< String > changerStatusAnnonce( @RequestParam int iduser,@RequestParam int idannonce,@RequestParam String datevente) {   
        String message="";
        try{
            annonceMiSer.changerstatusMyAnnonce(iduser, idannonce, datevente);
        }
        catch(ExceptionCar ec){
            message=ec.getMessage();
            ec.printStackTrace();
        }
        catch(Exception e){
            message="error";
            e.printStackTrace();
        }
        return ResponseEntity.ok(message);
    }
    @PostMapping("/creditercompte")
    public ResponseEntity< String > creditercompte( @RequestParam int iduser,@RequestParam String code) {   
        String message="";
        try{
            creditersoldeuserMiSer.crediterByIduserByCodecredit( iduser,code);
        }catch(ExceptionCar ec){
            message=ec.getMessage();
            ec.printStackTrace();
        }catch(Exception e){
            message="error";
            e.printStackTrace();
        }
        return ResponseEntity.ok(message);
    }
    //-------------------recherche
    @PostMapping("/searchOnNonValider")
    public ResponseEntity< List<AnnonceBodyMi> > searchOnNonValider( @RequestBody Search search) {
        List<AnnoncedetailMi_v> lstA= annoncedetailMi_vSer.getSearchAllEncoursByNbafficheByNumlineBeforFirst(search.getNbaffiche(), search.getNumlineBeforeFirst(), search.getWord(), search.getIdmarque(), search.getIdmodel(), search.getIdcarburant(), search.getNbplace(), search.getPrix1(), search.getPrix2(), search.getIdcategories());
        List<AnnonceBodyMi> lstAB=new AnnonceBodyMi().createListByListAnnoncedetailMi_v(lstA);
        return ResponseEntity.ok(lstAB);
    }
    @PostMapping("/searchOnPubAnnonce") //les annonces sauf mes annonces
    public ResponseEntity< List<AnnonceBodyMi> > searchOnPubAnnonce( @RequestBody Search search) {
        List<AnnoncedetailMi_v> lstA= annoncedetailMi_vSer.getSearchAllByNotIduserByNbafficheByNumlineBeforFirst(search.getIduser(),search.getNbaffiche(), search.getNumlineBeforeFirst(), search.getWord(), search.getIdmarque(), search.getIdmodel(), search.getIdcarburant(), search.getNbplace(), search.getPrix1(), search.getPrix2(), search.getIdcategories());
        List<AnnonceBodyMi> lstAB=new AnnonceBodyMi().createListByListAnnoncedetailMi_v(lstA);
        return ResponseEntity.ok(lstAB);
    }
    @PostMapping("/searchOnMesAnnonces")
    public ResponseEntity< List<AnnonceBodyMi> > searchOnMesAnnonces( @RequestBody Search search) {
        List<AnnoncedetailMi_v> lstA= annoncedetailMi_vSer.getSearchAllByIduserByNbafficheByNumlineBeforFirst(search.getIduser(),search.getNbaffiche(), search.getNumlineBeforeFirst(), search.getWord(), search.getIdmarque(), search.getIdmodel(), search.getIdcarburant(), search.getNbplace(), search.getPrix1(), search.getPrix2(), search.getIdcategories());
        List<AnnonceBodyMi> lstAB=new AnnonceBodyMi().createListByListAnnoncedetailMi_v(lstA);
        return ResponseEntity.ok(lstAB); 
    }
}
