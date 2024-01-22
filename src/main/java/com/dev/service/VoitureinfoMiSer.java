package com.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.body.InfoCar;
import com.dev.exception.ExceptionCar;
import com.dev.models.CategorievoitureMi;
import com.dev.models.VoitureinfoMi;
import com.dev.repository.CategorievoitureMiRep;
import com.dev.repository.ModelcategorieMiPersoRep;
import com.dev.repository.VoitureinfoMiRep;

import java.util.List;
import java.util.Optional;
@Service
public class VoitureinfoMiSer {

    @Autowired
    private VoitureinfoMiRep voitureinfoRepository;
    @Autowired
    private CategorievoitureMiRep categorievoitureMiRep;
    @Autowired
    private ModelcategorieMiPersoRep modelcategorieMiPersoRep;

    public VoitureinfoMi save(VoitureinfoMi Article) {
        return voitureinfoRepository.save(Article);
    }

    //VoitureinfoMi(int idvoitureinfo,String nomvoiture,int nombreplace,double kilometrage,int transmission,double vitesse,int iduser,int idcarburant,int idmarque,int idmodel)
    @Transactional
    public void saveByInfoCar(InfoCar infoCar)throws Exception {
        VoitureinfoMi voitureinfoMi=new VoitureinfoMi(0, infoCar.getNomvoiture(), infoCar.getNbplace(), infoCar.getKilometrage(), infoCar.getTransmission(), infoCar.getVitesse(), infoCar.getIduser(), infoCar.getIdcarburant(), infoCar.getIdmarque(), infoCar.getIdmodel(),infoCar.getAnneefabrication());
        int[] idcategories=infoCar.getIdcategories();
        if(idcategories==null){ throw new ExceptionCar(""); }
        else if(idcategories.length==0){ throw new ExceptionCar(""); }
        boolean goodCateg=modelcategorieMiPersoRep.isCategoriesOfModel(infoCar.getIdcategories(), infoCar.getIdmodel());
        if(goodCateg==false){ throw new ExceptionCar("un ou plusieur de ce(s) categori(es) sont non conforme a cette model"); }
        voitureinfoMi=voitureinfoRepository.save(voitureinfoMi);
        CategorievoitureMi categorievoitureMi=null;
        for(int i=0;i<idcategories.length;i++){
            categorievoitureMi= new CategorievoitureMi(0,idcategories[i],voitureinfoMi.getIdvoitureinfo());
            categorievoitureMiRep.save(categorievoitureMi);
        }
    }

    // Méthode pour récupérer tous les Articles
    public List<VoitureinfoMi> getAll() {
        return voitureinfoRepository.findAll();
    }
    public VoitureinfoMi[] getTabAll(){
        List<VoitureinfoMi> lstA=getAll();
        if(lstA==null){ return null; }
        if(lstA.isEmpty()==true){ return null; }
        VoitureinfoMi[] articles=new VoitureinfoMi[lstA.size()];
        for(int i=0;i<lstA.size();i++){
            articles[i]=lstA.get(i);
        }
        return articles;
    }
    // Méthode pour récupérer un Article par son ID
    public Optional<VoitureinfoMi> getById(int id) {
        return voitureinfoRepository.findById(id);
    }
    // Méthode pour supprimer un Article par son ID
    public void delete(int id) {
        voitureinfoRepository.deleteById(id);
    }
}
