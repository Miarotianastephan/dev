package com.dev.repository;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.dev.models.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
//-- idannonce | prixvente | descriptions | statusvente | etat | dateannonce | idlieu | nomlieu | idvoitureinfo | nomvoiture | nombreplace | kilometrage | transmission | vitesse | iduser | nomuser | prenomuser | idcarburant | nomcarburant | idmarque | nommarque | idmodel | nommodel | idcategorie | nomcategorie | datevente | datemodifstatus
@Repository
public class AnnoncedetailMi_vRep  {
//idannoncefavoris
// ---statusvente : 0 : vendu /10 : non vendu
// ---etat : 0:encour demande / 10 :accepter / 20: refuser
    @PersistenceContext
    private EntityManager entityManager;

    public List<AnnoncedetailMi_v> executerRequeteNative(String requete) {
        // Utilisation de createNativeQuery sans spécifier explicitement le type
        return (List<AnnoncedetailMi_v>)entityManager.createNativeQuery(requete, AnnoncedetailMi_v.class).getResultList();
    }
    public List<AnnoncedetailMi_v> getByNotIduser(int iduser, int nbaffiche, int numlineBeforeFirst) {
        String query="select ad_v.*, af.idannoncefavoris from annoncedetail_v as ad_v "+ 
        "left join annoncefavoris as af on (ad_v.idannonce=af.idannonce and af.iduser= "+iduser+") "+
        "where ad_v.iduser!= "+iduser+" and ad_v.statusvente=10 and ad_v.etat=10 "+
        "order by ad_v.idannonce ASC,ad_v.idcategorie ASC,ad_v.idannoncephoto ASC,ad_v.dateannonce ASC "+
        "LIMIT "+nbaffiche+" OFFSET "+numlineBeforeFirst;
        return executerRequeteNative(query);
    }
    public List<AnnoncedetailMi_v> getByIduser(int iduser, int nbaffiche, int numlineBeforeFirst) {
        String query="select ad_v.*, af.idannoncefavoris from annoncedetail_v as ad_v "+
        "left join annoncefavoris as af on (ad_v.idannonce=af.idannonce and af.iduser= "+iduser+") "+
        "where ad_v.iduser= "+iduser+" "+
        "order by ad_v.idannonce ASC,ad_v.idcategorie ASC,ad_v.idannoncephoto ASC,ad_v.dateannonce ASC "+
        "LIMIT "+nbaffiche+" OFFSET "+numlineBeforeFirst;
        return executerRequeteNative(query);
    }
    public List<AnnoncedetailMi_v> getEncours(int nbaffiche, int numlineBeforeFirst) {
        String query="select ad_v.*,0 as idannoncefavoris from annoncedetail_v as ad_v"+ 
        "where ad_v.etat=0 order by ad_v.idannonce ASC,ad_v.idcategorie ASC,ad_v.idannoncephoto ASC,ad_v.dateannonce ASC "+
        "LIMIT "+nbaffiche+" OFFSET "+numlineBeforeFirst;
        return executerRequeteNative(query);
    }

}