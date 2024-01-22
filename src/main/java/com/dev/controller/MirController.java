package com.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.body.*;
import com.dev.exception.ExceptionCar;
import com.dev.models.*;
import com.dev.service.VoitureinfoMiSer;
import com.dev.service.AnnonceMiSer;
import com.dev.service.AnnoncedetailMi_vSer;
import com.dev.service.AnnoncefavorisMiSer;
import com.dev.service.CreditersoldeuserMiSer;
import com.dev.service.SoldeuserMiSer;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/mir")
public class MirController {
    
    @Autowired
    private VoitureinfoMiSer voitureinfoMiSer;
    @Autowired
    private AnnonceMiSer annonceMiSer;
    @Autowired
    private AnnoncedetailMi_vSer annoncedetailMi_vSer;
    @Autowired
    private AnnoncefavorisMiSer annoncefavorisMiSer;
    @Autowired
    private CreditersoldeuserMiSer creditersoldeuserMiSer;
    @Autowired
    private SoldeuserMiSer soldeuserMiSer;
    
    @Value("${file.path}")
    private String imageDirectory;

    @GetMapping(path = "/hello" , produces = "application/json")
    public String getHello(){
        return "Hello Mirado!!";
    }
    @GetMapping("/hello1")
    public String getHello1(){
        return "Hello All !!";
    }
    //Mandeha
    @GetMapping("/ajoutinfocar")
    public ResponseEntity<String> ajoutinfocar( @RequestBody InfoCar infoCar) {
        
        String message="";
        try{
            voitureinfoMiSer.saveByInfoCar(infoCar);
        }catch(ExceptionCar ec){
            message=ec.getMessage();
            ec.printStackTrace();
        }catch(Exception e){
            message="error";
            e.printStackTrace();
        }
        return ResponseEntity.ok(message);
    }

    //mandeha
    @GetMapping("/creercompte")
    public ResponseEntity<String>  creercompte(@RequestParam int iduser){
        String message="";
        try{
            soldeuserMiSer.createSoldeUserIfExiste(iduser);
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
    public ResponseEntity<String> ajoutannonce( @RequestBody Annoncetosave annoncetosave) {
        System.out.println("tonga");
        String message="";
        try{
            Annoncesave annoncesave=new Annoncesave(annoncetosave.getIdvoitureinfo(), annoncetosave.getIdlieu(), annoncetosave.getPrixvente(), annoncetosave.getDescription(), null);
            annonceMiSer.insertAnnonce(annoncesave,annoncetosave.getFiles());
        }catch(ExceptionCar ec){
            message=ec.getMessage();
            ec.printStackTrace();
        }catch(Exception e){
            message="error";
            e.printStackTrace();
        }
        return ResponseEntity.ok(message);
    }
    @GetMapping("/getPubAnnoces") //les annonces sauf mes annonces
    public ResponseEntity< List<AnnonceBodyMi> > getPubAnnoces( @RequestParam int iduser,@RequestParam int nbaffiche,@RequestParam int numLineBeforeFirst) {
        List<AnnoncedetailMi_v> lstA= annoncedetailMi_vSer.getAllByNotIduserByNbafficheByNumlineBeforFirst(iduser, nbaffiche, numLineBeforeFirst);
        List<AnnonceBodyMi> lstAB=new AnnonceBodyMi().createListByListAnnoncedetailMi_v(lstA);
        return ResponseEntity.ok(lstAB);
    }
    @GetMapping("/getMesAnnoces")
    public ResponseEntity< List<AnnonceBodyMi> > getMesAnnoces( @RequestParam int iduser,@RequestParam int nbaffiche,@RequestParam int numLineBeforeFirst) {
        List<AnnoncedetailMi_v> lstA= annoncedetailMi_vSer.getAllByIduserByNbafficheByNumlineBeforFirst(iduser, nbaffiche, numLineBeforeFirst);
        List<AnnonceBodyMi> lstAB=new AnnonceBodyMi().createListByListAnnoncedetailMi_v(lstA);
        return ResponseEntity.ok(lstAB);
    }
    
    @GetMapping("/toFavoris")
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

    @GetMapping("/getAnnoncesNonValider")
    public ResponseEntity< List<AnnonceBodyMi> > getAnnoncesNonValider( @RequestParam int iduser,@RequestParam int nbaffiche,@RequestParam int numlineBeforeFirst) {
        List<AnnoncedetailMi_v> lstA= annoncedetailMi_vSer.getAllEncoursByNbafficheByNumlineBeforFirst(nbaffiche,numlineBeforeFirst );
        List<AnnonceBodyMi> lstAB=new AnnonceBodyMi().createListByListAnnoncedetailMi_v(lstA);
        return ResponseEntity.ok(lstAB);
    }

    @GetMapping("/validerAnnonce")
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

    @GetMapping("/refuserAnnonce") 
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

    @GetMapping("/changerStatusAnnonce") 
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
    @GetMapping("/creditercompte")
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
    @GetMapping("/searchOnNonValider")
    public ResponseEntity< List<AnnonceBodyMi> > searchOnNonValider( @RequestBody Search search) {
        List<AnnoncedetailMi_v> lstA= annoncedetailMi_vSer.getSearchAllEncoursByNbafficheByNumlineBeforFirst(search.getNbaffiche(), search.getNumlineBeforeFirst(), search.getWord(), search.getIdmarque(), search.getIdmodel(), search.getIdcarburant(), search.getNbplace(), search.getPrix1(), search.getPrix2(), search.getIdcategories());
        List<AnnonceBodyMi> lstAB=new AnnonceBodyMi().createListByListAnnoncedetailMi_v(lstA);
        return ResponseEntity.ok(lstAB);
    }
    @GetMapping("/searchOnPubAnnonce") //les annonces sauf mes annonces
    public ResponseEntity< List<AnnonceBodyMi> > searchOnPubAnnonce( @RequestBody Search search) {
        List<AnnoncedetailMi_v> lstA= annoncedetailMi_vSer.getSearchAllByNotIduserByNbafficheByNumlineBeforFirst(search.getIduser(),search.getNbaffiche(), search.getNumlineBeforeFirst(), search.getWord(), search.getIdmarque(), search.getIdmodel(), search.getIdcarburant(), search.getNbplace(), search.getPrix1(), search.getPrix2(), search.getIdcategories());
        List<AnnonceBodyMi> lstAB=new AnnonceBodyMi().createListByListAnnoncedetailMi_v(lstA);
        return ResponseEntity.ok(lstAB);
    }
    @GetMapping("/searchOnMesAnnonces")
    public ResponseEntity< List<AnnonceBodyMi> > searchOnMesAnnonces( @RequestBody Search search) {
        List<AnnoncedetailMi_v> lstA= annoncedetailMi_vSer.getSearchAllByIduserByNbafficheByNumlineBeforFirst(search.getIduser(),search.getNbaffiche(), search.getNumlineBeforeFirst(), search.getWord(), search.getIdmarque(), search.getIdmodel(), search.getIdcarburant(), search.getNbplace(), search.getPrix1(), search.getPrix2(), search.getIdcategories());
        List<AnnonceBodyMi> lstAB=new AnnonceBodyMi().createListByListAnnoncedetailMi_v(lstA);
        return ResponseEntity.ok(lstAB); 
    }
}
