package com.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev.models.AnnoncedetailMi_v;
import com.dev.repository.AnnoncedetailMiPersoRep;
import com.dev.repository.AnnoncedetailMi_vRep;

import java.sql.Date;
import java.util.List;
@Service
public class AnnoncedetailMi_vSer {

    @Autowired
    private AnnoncedetailMi_vRep annonceRepository;
    @Autowired
    private AnnoncedetailMiPersoRep annoncedetaipersoRepository;
    public List<AnnoncedetailMi_v> getAllByNotIduserByNbafficheByNumlineBeforFirst(int iduser,int nbaffiche,int numlineBeforeFirst ){
        return annonceRepository.getByNotIduser(iduser,nbaffiche,numlineBeforeFirst);
    }//getByIduser(@Param("iduser")int iduser,@Param("nbaffiche")int nbaffiche,@Param("numlineBeforeFirst")int numlineBeforeFirst ) 
    public List<AnnoncedetailMi_v> getAllByIduserByNbafficheByNumlineBeforFirst(int iduser,int nbaffiche,int numlineBeforeFirst ){
        return annonceRepository.getByIduser(iduser,nbaffiche,numlineBeforeFirst);
    }
    public List<AnnoncedetailMi_v> getAllEncoursByNbafficheByNumlineBeforFirst(int nbaffiche,int numlineBeforeFirst ){
        return annonceRepository.getEncours(nbaffiche, numlineBeforeFirst);
    }
    public String createSuiteRequeteRechercher(String word,int idmarque,int idmodel,int idcarburant,int nbplace,double prix1,double prix2,int[] idcategories)
    {
        String suitquery=" and ";
        if(word!=null){
            String wordNoSpace=word.replaceAll(" ","");
            if(wordNoSpace.compareTo("")!=0){
                String[] colint={ "ad_v.nombreplace" };
                String[] coldouble={ "ad_v.nombreplace","ad_v.prixvente","ad_v.kilometrage","ad_v.vitesse" };
                String[] coldate={"ad_v.dateannonce"};
                String[] colvarchar={ "ad_v.descriptions","ad_v.nomlieu","ad_v.nomvoiture","ad_v.nomuser","ad_v.prenomuser","ad_v.nomcarburant","ad_v.nommarque","ad_v.nommodel","ad_v.nomcategorie"};
                String[] colsearch=colvarchar;
                String compar="=";
                String entour="\'";
                try{    
                    Integer.valueOf(wordNoSpace);
                    colsearch=colint;    
                    compar="=";
                    entour="";   
                }catch(Exception e){  
                    try{    
                        Double.valueOf(wordNoSpace);
                        colsearch=coldouble; 
                        compar="=";
                        entour="";
                    }catch(Exception e1){ 
                        try{    
                            Date.valueOf(wordNoSpace);
                            colsearch=coldate; 
                            compar="=";
                            entour="\'";
                        }catch(Exception e2){ //rehafa tsy castable int,double,date
                            colsearch=colvarchar;
                            compar="like";
                            entour="%";
                        }
                    }
                }
                suitquery=suitquery+" ("+colsearch[0]+" "+compar+" "+entour+""+word+""+entour;
                for(int i=1;i<colsearch.length;i++){
                    suitquery=suitquery+" or "+colsearch[i]+" "+compar+" "+entour+""+word+""+entour;
                }
                suitquery=suitquery+") and";
            }
        }
        if(idmarque!=0){ suitquery=suitquery+" ad_v.idmarque="+idmarque+" and"; }
        if(idmodel!=0){ suitquery=suitquery+" ad_v.idmodel="+idmodel+" and"; }
        if(idcarburant!=0){ suitquery=suitquery+" ad_v.idcarburant="+idcarburant+" and"; }
        if(nbplace!=0){ suitquery=suitquery+" ad_v.nbplace="+nbplace+" and"; }
        if(prix1>0 || prix2>0){ suitquery=suitquery+" ad_v.prixvente  between "+prix1+" and "+prix2+" and"; }
        if(idcategories!=null){ 
            for(int i=0;i<idcategories.length;i++){
                if(i==0){ suitquery=suitquery+"("; }
                suitquery=suitquery+" ad_v.idcategorie="+idcategories[i]+" or ";
            }
            if(idcategories.length!=0){
                suitquery=suitquery.substring(0,suitquery.length()-3)+")"; //esorina le "or "
            }
        }
        if(suitquery.substring(suitquery.length()-3,suitquery.length()).compareToIgnoreCase("and")==0){ 
            suitquery=suitquery.substring(0,suitquery.length()-3); //esorina ny "and" 
        }
        if(suitquery.replaceAll(" ","").compareToIgnoreCase("and")==0){ suitquery=""; } //raha tsy nisy recherche mihintsy
        return suitquery;

    }

    public List<AnnoncedetailMi_v> getSearchAllByNotIduserByNbafficheByNumlineBeforFirst(int iduser,int nbaffiche,int numlineBeforeFirst,String word,int idmarque,int idmodel,int idcarburant,int nbplace,double prix1,double prix2,int[] idcategories){
        String suitequery=createSuiteRequeteRechercher(word, idmarque, idmodel, idcarburant, nbplace, prix1, prix2, idcategories);
        return annoncedetaipersoRepository.getSearchByNotIduser(iduser, nbaffiche, numlineBeforeFirst, suitequery);
    }//getByIduser(@Param("iduser")int iduser,@Param("nbaffiche")int nbaffiche,@Param("numlineBeforeFirst")int numlineBeforeFirst ) 

    public List<AnnoncedetailMi_v> getSearchAllByIduserByNbafficheByNumlineBeforFirst(int iduser,int nbaffiche,int numlineBeforeFirst,String word,int idmarque,int idmodel,int idcarburant,int nbplace,double prix1,double prix2,int[] idcategories ){
        String suitequery=createSuiteRequeteRechercher(word, idmarque, idmodel, idcarburant, nbplace, prix1, prix2, idcategories);
        return annoncedetaipersoRepository.getSearchByIduser(iduser, nbaffiche, numlineBeforeFirst, suitequery);
    }

    public List<AnnoncedetailMi_v> getSearchAllEncoursByNbafficheByNumlineBeforFirst(int nbaffiche,int numlineBeforeFirst,String word,int idmarque,int idmodel,int idcarburant,int nbplace,double prix1,double prix2,int[] idcategories ){
        String suitequery=createSuiteRequeteRechercher(word, idmarque, idmodel, idcarburant, nbplace, prix1, prix2, idcategories);
        return annoncedetaipersoRepository.getSearchEncours(nbaffiche, numlineBeforeFirst, suitequery);
    }
}
