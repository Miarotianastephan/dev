package com.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.exception.ExceptionCar;
import com.dev.models.Codecredit_vMi;
import com.dev.models.CreditersoldeuserMi;
import com.dev.models.SoldeuserMi;
import com.dev.repository.CreditersoldeuserMiRep;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class CreditersoldeuserMiSer {

    @Autowired
    private CreditersoldeuserMiRep repository;
    @Autowired
    private Codecredit_vMiSer codecredit_vMiSer;
    @Autowired
    private SoldeuserMiSer soldeuserMiSer;
    @Autowired
    private CreditersoldeuserMiRep creditersoldeuserMiRep;
    

    public CreditersoldeuserMi save(CreditersoldeuserMi Creditersoldeuser) {
        return repository.save(Creditersoldeuser);
    }
    public List<CreditersoldeuserMi> getAll() {
        return repository.findAll();
    }
    public CreditersoldeuserMi[] getTabAll(){
        List<CreditersoldeuserMi> lst=getAll();
        if(lst==null){ return null; }
        if(lst.isEmpty()==true){ return null; }
        CreditersoldeuserMi[] tabs=new CreditersoldeuserMi[lst.size()];
        for(int i=0;i<lst.size();i++){
            tabs[i]=lst.get(i);
        }
        return tabs;
    }
    //jerena hoe code misy sy mbola disponible ve
    //alaina ny compte n'le user-->inserena + updatesolde
    @Transactional
    public void crediterByIduserByCodecredit(int iduser,String codecredit)throws Exception{
        LocalDateTime lc=LocalDateTime.now();
        int code=0;
        try{
        code=Integer.valueOf(codecredit);
        }catch(Exception e){ throw new ExceptionCar("code credit "+codecredit+" invalide"); }
        Codecredit_vMi codecredit_vmi = codecredit_vMiSer.getByCode(code);
        if(codecredit_vmi==null){
            throw new ExceptionCar("code de credit invalide");
        }
        //CreditersoldeuserMi(int idcredit, double montantc, LocalDateTime datec, int idcodecredit, int idsoldeuser)
        SoldeuserMi soldeusermi=soldeuserMiSer.getSoldeByIduser(iduser);
        if(soldeusermi==null){ throw new ExceptionCar("compte solde user null"); }
        CreditersoldeuserMi creditersoldeuser=new CreditersoldeuserMi(0, codecredit_vmi.getValeur(), lc, codecredit_vmi.getIdcodecredit(),soldeusermi.getIdsoldeuser());
        creditersoldeuserMiRep.save(creditersoldeuser);
        soldeusermi.setSolde(soldeusermi.getSolde()+codecredit_vmi.getValeur());
    }
    // Méthode pour récupérer un  par son ID
    public Optional<CreditersoldeuserMi> getById(int id) {
        return repository.findById(id);
    }
    // Méthode pour supprimer un  par son ID
    public void delete(int id) {
        repository.deleteById(id);
    }
}
