package com.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.models.RegletauxMi;
import com.dev.repository.RegletauxMiRep;
import java.util.List;
import java.util.Optional;
@Service
public class RegletauxMiSer {

    @Autowired
    private RegletauxMiRep repository;
    
    public RegletauxMi save(RegletauxMi Regletaux) {
        return repository.save(Regletaux);
    }
    public List<RegletauxMi> getAll() {
        return repository.findAll();
    }
    public RegletauxMi[] getTabAll(){
        List<RegletauxMi> lst=getAll();
        if(lst==null){ return null; }
        if(lst.isEmpty()==true){ return null; }
        RegletauxMi[] tabs=new RegletauxMi[lst.size()];
        for(int i=0;i<lst.size();i++){
            tabs[i]=lst.get(i);
        }
        return tabs;
    }
    public double getTauxCommision(){
       List<Double> tc= repository.getTauxCommision();
       if(tc.isEmpty()==true){ return 0; }
       else return tc.get(0);
    }
    // Méthode pour récupérer un  par son ID
    public Optional<RegletauxMi> getById(int id) {
        return repository.findById(id);
    }
    // Méthode pour supprimer un  par son ID
    public void delete(int id) {
        repository.deleteById(id);
    }
}
