package com.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.models.SoldeuserMi;
import com.dev.repository.SoldeuserMiRep;
import java.util.List;
import java.util.Optional;
@Service
public class SoldeuserMiSer {

    @Autowired
    private SoldeuserMiRep repository;
    
    public SoldeuserMi save(SoldeuserMi Soldeuser) {
        return repository.save(Soldeuser);
    }
    public List<SoldeuserMi> getAll() {
        return repository.findAll();
    }
    public SoldeuserMi[] getTabAll(){
        List<SoldeuserMi> lst=getAll();
        if(lst==null){ return null; }
        if(lst.isEmpty()==true){ return null; }
        SoldeuserMi[] tabs=new SoldeuserMi[lst.size()];
        for(int i=0;i<lst.size();i++){
            tabs[i]=lst.get(i);
        }
        return tabs;
    }
    public SoldeuserMi getSoldeByIduser(int iduser){
        List<SoldeuserMi> ls=repository.getSoldeByIduser(iduser);
        if(ls.isEmpty()==true){ return null; }
        else { return ls.get(0);  }
    }
    // Méthode pour récupérer un  par son ID
    public Optional<SoldeuserMi> getById(int id) {
        return repository.findById(id);
    }
    // Méthode pour supprimer un  par son ID
    public void delete(int id) {
        repository.deleteById(id);
    }
}
