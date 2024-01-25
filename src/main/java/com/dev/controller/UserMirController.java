package com.dev.controller;

import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/api/usermir")
public class UserMirController {
    
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
    public Hashtable <String,Object> ajoutinfocar( @RequestBody InfoCar infoCar) {
        Hashtable <String,Object> response=new Hashtable<>();
        
        try{
            voitureinfoMiSer.saveByInfoCar(infoCar);
            response.put("status",200);
            response.put("message","succes");
        }catch(ExceptionCar ec){
            ec.printStackTrace();
            response.put("status",500);
            response.put("message",ec.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            response.put("status",500);
            response.put("message","error");
            response.put("cause",e.getMessage());
        }
        return response;
    }

    //mandeha
    @GetMapping("/creercompte")
    public Hashtable <String,Object>  creercompte(@RequestParam int iduser){
        Hashtable <String,Object> response=new Hashtable<>();
        
        try{
            soldeuserMiSer.createSoldeUserIfExiste(iduser);
            response.put("status",200);
            response.put("message","succes");
        }catch(ExceptionCar ec){
            ec.printStackTrace();
            response.put("status",500);
            response.put("message",ec.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            response.put("status",500);
            response.put("message","error");
            response.put("cause",e.getMessage());
        }
        return response;
    }
    
    @PostMapping("/ajoutannonce2") 
    public Hashtable <String,Object> ajoutannonce2( @RequestParam int idvoitureinfo,@RequestParam int idlieu,@RequestParam double prixvente,@RequestParam String description,@RequestParam MultipartFile[] files) {
        Hashtable <String,Object> response=new Hashtable<>();
        
        try{
            Annoncesave annoncesave=new Annoncesave(idvoitureinfo, idlieu, prixvente, description, files);
            annonceMiSer.insertAnnonce2(annoncesave);
            response.put("status",200);
            response.put("message","succes");
        }catch(ExceptionCar ec){
            ec.printStackTrace();
            response.put("status",500);
            response.put("message",ec.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            response.put("status",500);
            response.put("message","error");
            response.put("cause",e.getMessage());
        }
        return response;
    }

    //Mandeha  
    @GetMapping(path="/getPubAnnonces",produces = "application/json") //les annonces sauf mes annonces
    public Hashtable<String,Object> getPubAnnoces( @RequestParam int iduser,@RequestParam int nbaffiche,@RequestParam int numlinebeforefirst) {
        Hashtable <String,Object> response=new Hashtable<>();
        try{
            List<AnnoncedetailMi_v> lstA= annoncedetailMi_vSer.getAllByNotIduserByNbafficheByNumlineBeforFirst(iduser, nbaffiche, numlinebeforefirst);
            List<AnnonceBodyMi> lstAB=new AnnonceBodyMi().createListByListAnnoncedetailMi_v(lstA);
            response.put("status",200);
            response.put("message","ok");
            if(lstAB!=null){if(lstAB.isEmpty()==false){
                response.put("data",lstAB);
            }}
        }catch (Exception e){
            e.printStackTrace();
            response.put("status",500);
            response.put("message",e.getMessage());
        }
        return response;
    }
    //Mandeha
    @GetMapping("/getMesAnnoces")
    public Hashtable <String,Object> getMesAnnoces( @RequestParam int iduser,@RequestParam int nbaffiche,@RequestParam int numlinebeforefirst) {
        Hashtable <String,Object> response=new Hashtable<>();
        try{
            List<AnnoncedetailMi_v> lstA= annoncedetailMi_vSer.getAllByIduserByNbafficheByNumlineBeforFirst(iduser, nbaffiche, numlinebeforefirst);
            List<AnnonceBodyMi> lstAB=new AnnonceBodyMi().createListByListAnnoncedetailMi_v(lstA);
            response.put("status",200);
            response.put("message","ok");
            if(lstAB!=null){if(lstAB.isEmpty()==false){
                response.put("data",lstAB);
            }}
        }catch (Exception e){
            e.printStackTrace();
            response.put("status",500);
            response.put("message",e.getMessage());
        }
        return response;
    }
    //mandeha
    @GetMapping("/toFavoris")
    public Hashtable <String,Object> getPubAnnoces( @RequestParam int iduser,@RequestParam int idannonce) {
        Hashtable <String,Object> response=new Hashtable<>();
        
        try{
            annoncefavorisMiSer.verifeInsert(iduser, idannonce);
        }catch(ExceptionCar ec){
            ec.printStackTrace();
            response.put("status",500);
            response.put("message",ec.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            response.put("status",500);
            response.put("message","error");
            response.put("cause",e.getMessage());
        }
        return response;
    }


    @PostMapping("/creditercompte")
    public Hashtable <String,Object> creditercompte( @RequestParam int iduser,@RequestParam String code) {   
        Hashtable <String,Object> response=new Hashtable<>();
        
        try{
            creditersoldeuserMiSer.crediterByIduserByCodecredit( iduser,code);
            response.put("status",200);
            response.put("message","succes");
        }catch(ExceptionCar ec){
            ec.printStackTrace();
            response.put("status",500);
            response.put("message",ec.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            response.put("status",500);
            response.put("message","error");
            response.put("cause",e.getMessage());
        }
        return response;
    }
    //mandeha
    @GetMapping("/changerStatusAnnonce") 
    public Hashtable <String,Object> changerStatusAnnonce( @RequestParam int iduser,@RequestParam int idannonce,@RequestParam String datevente) {  
        Hashtable <String,Object> response=new Hashtable<>(); 
        
        try{
            annonceMiSer.changerstatusMyAnnonce(iduser, idannonce, datevente);
            response.put("status",200);
            response.put("message","succes");
        }
        catch(ExceptionCar ec){
            ec.printStackTrace();
            response.put("status",500);
            response.put("message",ec.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("status",500);
            response.put("message","error");
            response.put("cause",e.getMessage());
        }
        return response;
    }
    
    @GetMapping("/searchOnPubAnnonce") //les annonces sauf mes annonces
    public Hashtable <String,Object> searchOnPubAnnonce( @RequestBody Search search) {
        Hashtable <String,Object> response=new Hashtable<>(); 
        try{
            List<AnnoncedetailMi_v> lstA= annoncedetailMi_vSer.getSearchAllByNotIduserByNbafficheByNumlineBeforFirst(search.getIduser(),search.getNbaffiche(), search.getNumlineBeforeFirst(), search.getWord(), search.getIdmarque(), search.getIdmodel(), search.getIdcarburant(), search.getNbplace(), search.getPrix1(), search.getPrix2(), search.getIdcategories());
            List<AnnonceBodyMi> lstAB=new AnnonceBodyMi().createListByListAnnoncedetailMi_v(lstA);
            response.put("status",200);
            response.put("message","ok");
            if(lstAB!=null){if(lstAB.isEmpty()==false){
                response.put("data",lstAB);
            }}
        }
        catch (Exception e){
            e.printStackTrace();
            response.put("status",500);
            response.put("message",e.getMessage());
        }
        return response;
    }
    @GetMapping("/searchOnMesAnnonces")
    public Hashtable <String,Object> searchOnMesAnnonces( @RequestBody Search search) {
        Hashtable <String,Object> response=new Hashtable<>(); 
        try{
            List<AnnoncedetailMi_v> lstA= annoncedetailMi_vSer.getSearchAllByIduserByNbafficheByNumlineBeforFirst(search.getIduser(),search.getNbaffiche(), search.getNumlineBeforeFirst(), search.getWord(), search.getIdmarque(), search.getIdmodel(), search.getIdcarburant(), search.getNbplace(), search.getPrix1(), search.getPrix2(), search.getIdcategories());
            List<AnnonceBodyMi> lstAB=new AnnonceBodyMi().createListByListAnnoncedetailMi_v(lstA);
            response.put("status",200);
            response.put("message","ok");
            if(lstAB!=null){if(lstAB.isEmpty()==false){
                response.put("data",lstAB);
            }}
        }
        catch (Exception e){
            e.printStackTrace();
            response.put("status",500);
            response.put("message",e.getMessage());
        }
        return response; 
    }
}
