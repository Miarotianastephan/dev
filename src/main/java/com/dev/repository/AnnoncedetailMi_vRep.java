package com.dev.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.models.*;
//-- idannonce | prixvente | descriptions | statusvente | etat | dateannonce | idlieu | nomlieu | idvoitureinfo | nomvoiture | nombreplace | kilometrage | transmission | vitesse | iduser | nomuser | prenomuser | idcarburant | nomcarburant | idmarque | nommarque | idmodel | nommodel | idcategorie | nomcategorie | datevente | datemodifstatus
public interface AnnoncedetailMi_vRep extends JpaRepository<AnnoncedetailMi_v, Integer> {
//idannoncefavoris
// ---statusvente : 0 : vendu /10 : non vendu
// ---etat : 0:encour demande / 10 :accepter / 20: refuser


    
    @Query(value = "select ad_v.*, af.idannoncefavoris from annoncedetail_v as ad_v left join annoncefavoris as af on (ad_v.idannonce=af.idannonce and af.iduser= :iduser) where ad_v.iduser!= :iduser and ad_v.statusvente=10 and ad_v.etat=10 order by ad_v.idannonce ASC,ad_v.idcategorie ASC,ad_v.idannoncephoto ASC,ad_v.dateannonce ASC LIMIT :nbaffiche OFFSET :numlineBeforeFirst" , nativeQuery = true )
    List <AnnoncedetailMi_v> getByNotIduser(@Param("iduser")int iduser,@Param("nbaffiche")int nbaffiche,@Param("numlineBeforeFirst")int numlineBeforeFirst ) ;

    @Query(value = "select ad_v.*, af.idannoncefavoris from annoncedetail_v as ad_v left join annoncefavoris as af on (ad_v.idannonce=af.idannonce and af.iduser= :iduser) where ad_v.iduser= :iduser order by ad_v.idannonce ASC,ad_v.idcategorie ASC,ad_v.idannoncephoto ASC,ad_v.dateannonce ASC LIMIT :nbaffiche OFFSET :numlineBeforeFirst" , nativeQuery = true )
    List <AnnoncedetailMi_v> getByIduser(@Param("iduser")int iduser,@Param("nbaffiche")int nbaffiche,@Param("numlineBeforeFirst")int numlineBeforeFirst ) ;

    @Query(value = "select ad_v.*,0 as idannoncefavoris from annoncedetail_v as ad_v where ad_v.etat=0 order by ad_v.idannonce ASC,ad_v.idcategorie ASC,ad_v.idannoncephoto ASC,ad_v.dateannonce ASC LIMIT :nbaffiche OFFSET :numlineBeforeFirst" , nativeQuery = true )
    List <AnnoncedetailMi_v> getEncours(@Param("nbaffiche")int nbaffiche,@Param("numlineBeforeFirst")int numlineBeforeFirst ) ;

 // @Query(value = "select datebandecommade from bandecommandedetail group by datebandecommade order by datebandecommade desc" , nativeQuery = true)
    // List<Date> getDateMere();
    // @Query(value = "select nom from article where id_article = :idarticle" , nativeQuery = true)
    // String Namearticle(@Param("idarticle") int idarticle); 

    // en jpa de spring boot, je veux donnee la suite de la requete en parametre dans le repository : @Query(value = "select * from personneview where :suitequery" , nativeQuery = true )
    // List <AnnoncedetailMi_v> getSearchEncours(@Param("suitequery")String suitequery  ) ; , par exemple String suitequery=" idpersonne=3 and age=32", est ce que sa marchera?
    
}