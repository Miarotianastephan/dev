package com.dev.service.implementation;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dev.model.categorie.Categorie;
import com.dev.model.models.Model;
import com.dev.model.models.ModelCategorie;
import com.dev.repository.MarqueRepository;
import com.dev.repository.ModelCategorieRepository;
import com.dev.repository.ModelRepository;
import com.dev.repository.TransmissionRepository;
import com.dev.service.ModelService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelCategorieRepository modelCategorieRepository;
    private final TransmissionRepository transmissionRepository;
    private final MarqueRepository marqueRepository;

    @Override
    public List<Model> findAllModel() {
        return modelRepository.findAll();
    }

    @Override
    public Model save(Model model) {
        Model result=null;
        boolean misy=true;
        if(model.getListeModelCategorie()==null||model.getListeModelCategorie().size()==0) {
            misy=false;
        }
        if(misy) {
            result =  modelRepository.save(model);
            ModelCategorie modelCategorie=new ModelCategorie();
            for(int i=0; i<model.getListeModelCategorie().size(); i++) {
                modelCategorie.setCategorie(model.getListeModelCategorie().get(i).getCategorie());
                // System.out.println(modelCategorie.getCategorie());
                modelCategorie.setModel(model);
                modelCategorieRepository.save(modelCategorie);
            }
        }
        return result;
    }

    @Override
    public Model update(int idModel, String nomModel, double vitesse, int idTransmission, Date dateSortie, int idMarque) {
        Model model=modelRepository.findById(idModel).get();
        model.setNomModel(nomModel);
        model.setVitesse(vitesse);
        model.setTransmission(transmissionRepository.findById(idTransmission).get());
        model.setDateSortie(dateSortie);
        model.setMarque(marqueRepository.findById(idMarque).get());
        return modelRepository.save(model);
    }

    @Override
    public void delete(int idModel) {
        Model model=modelRepository.findById(idModel).get();
        modelCategorieRepository.deleteByModel(model);
        modelRepository.deleteById(idModel);
    }

    @Override
    public Optional<Model> findModelById(int idModel) {
        return modelRepository.findById(idModel);
    }
}
