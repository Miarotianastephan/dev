package com.dev.repository;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import com.dev.models.*;;

@Repository
public class AnnoncedetailMiPersoRep {


    @PersistenceContext
    private EntityManager entityManager;

    public List<AnnoncedetailMi_v> executerRequeteNative(String requete) {
        // Utilisation de createNativeQuery sans spécifier explicitement le type
        return (List<AnnoncedetailMi_v>)entityManager.createNativeQuery(requete, AnnoncedetailMi_v.class).getResultList();
    }
    public List<AnnoncedetailMi_v> getSearchByNotIduser(int iduser, int nbaffiche, int numlineBeforeFirst, String suitequery ) {
        String query="select ad_v.*, af.idannoncefavoris from annoncedetail_v as ad_v left join annoncefavoris as af on "+
        "(ad_v.idannonce=af.idannonce and af.iduser= "+iduser+") where ad_v.iduser!= "+iduser+" and ad_v.statusvente=10 and ad_v.etat=10 "+
        suitequery+" order by ad_v.idannonce ASC,ad_v.idcategorie ASC,ad_v.idannoncephoto ASC,ad_v.dateannonce ASC LIMIT "+nbaffiche+" OFFSET "+numlineBeforeFirst;
        // Utilisation de createNativeQuery sans spécifier explicitement le type
        return executerRequeteNative(query);
    }
    public List<AnnoncedetailMi_v> getSearchByIduser(int iduser, int nbaffiche, int numlineBeforeFirst, String suitequery ) {
        String query="select ad_v.*, af.idannoncefavoris from annoncedetail_v as ad_v left join annoncefavoris as af on "+
        "(ad_v.idannonce=af.idannonce and af.iduser= "+iduser+") where ad_v.iduser= "+iduser+" "+suitequery+" "+
        suitequery+" order by ad_v.idannonce ASC,ad_v.idcategorie ASC,ad_v.idannoncephoto ASC,ad_v.dateannonce ASC LIMIT "+nbaffiche+" OFFSET "+numlineBeforeFirst;
        // Utilisation de createNativeQuery sans spécifier explicitement le type
        return executerRequeteNative(query);
    }
    public List<AnnoncedetailMi_v> getSearchEncours(int nbaffiche, int numlineBeforeFirst, String suitequery ) {
        String query="select ad_v.*,0 as idannoncefavoris from annoncedetail_v as ad_v "+
        " where ad_v.etat=0 "+suitequery+
        " order by ad_v.idannonce ASC,ad_v.idcategorie ASC,ad_v.idannoncephoto ASC,ad_v.dateannonce ASC LIMIT "+nbaffiche+" OFFSET "+numlineBeforeFirst;
        // Utilisation de createNativeQuery sans spécifier explicitement le type
        return executerRequeteNative(query);
    }


}
