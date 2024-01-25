package com.dev.controller;

import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.body.*;
import com.dev.exception.ExceptionCar;
import com.dev.models.*;
import com.dev.service.AnnonceMiSer;
import com.dev.service.AnnoncedetailMi_vSer;
import com.dev.service.AnnoncefavorisMiSer;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/adminmir")
public class AdminMiController {
    
    @Autowired
    private AnnonceMiSer annonceMiSer;
    @Autowired
    private AnnoncedetailMi_vSer annoncedetailMi_vSer;
    @Autowired
    private AnnoncefavorisMiSer annoncefavorisMiSer;
    @Autowired
    
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
    @GetMapping("/getAnnoncesNonValider")
    public Hashtable <String,Object> getAnnoncesNonValider(@RequestParam int nbaffiche,@RequestParam int numlinebeforefirst) {
        Hashtable <String,Object> response=new Hashtable<>();
        try{
            List<AnnoncedetailMi_v> lstA= annoncedetailMi_vSer.getAllEncoursByNbafficheByNumlineBeforFirst(nbaffiche,numlinebeforefirst );
            List<AnnonceBodyMi> lstAB=new AnnonceBodyMi().createListByListAnnoncedetailMi_v(lstA);
            response.put("status",200);
            response.put("message","ok");
            if(lstAB!=null){if(lstAB.isEmpty()==false){
                response.put("data",lstAB);
            }}
        }catch(Exception e){
            e.printStackTrace();
            response.put("status",500);
            response.put("message","error");
            response.put("cause",e.getMessage());
        }
        
        return response;
    }

    //Mandeha
    @GetMapping("/validerAnnonce")
    public Hashtable <String,Object> validerAnnonce( @RequestParam int idadmin,@RequestParam int idannonce) {   
        Hashtable <String,Object> response=new Hashtable<>();
        
        try{
            annonceMiSer.valider(idannonce,idadmin);
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
    @GetMapping("/refuserAnnonce") 
    public Hashtable <String,Object> refuserAnnonce( @RequestParam int idadmin,@RequestParam int idannonce) {  
        Hashtable <String,Object> response=new Hashtable<>();  
        try{
          annonceMiSer.refuser(idannonce, idadmin);
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


    //-------------------recherche
    @GetMapping("/searchOnNonValider")
    public Hashtable <String,Object> searchOnNonValider( @RequestBody Search search) {
        Hashtable <String,Object> response=new Hashtable<>(); 
        try{
            List<AnnoncedetailMi_v> lstA= annoncedetailMi_vSer.getSearchAllEncoursByNbafficheByNumlineBeforFirst(search.getNbaffiche(), search.getNumlineBeforeFirst(), search.getWord(), search.getIdmarque(), search.getIdmodel(), search.getIdcarburant(), search.getNbplace(), search.getPrix1(), search.getPrix2(), search.getIdcategories());
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

}
