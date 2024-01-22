package com.dev.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.model.categorie.Categorie;
import com.dev.model.models.Model;
import com.dev.model.models.ModelCategorie;
import com.dev.repository.ModelCategorieRepository;
import com.dev.repository.ModelRepository;
import com.dev.service.ModelService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelCategorieRepository modelCategorieRepository;

    @Override
    public List<Model> findAllModel() {
        return modelRepository.findAll();
    }

    @Override
    public Model save(Model model) {
        Model result =  modelRepository.save(model);
        ModelCategorie modelCategorie=new ModelCategorie();
        for(int i=0; i<model.getListeModelCategorie().size(); i++) {
            modelCategorie.setCategorie(model.getListeModelCategorie().get(i).getCategorie());
            // System.out.println(modelCategorie.getCategorie());
            modelCategorie.setModel(model);
            modelCategorieRepository.save(modelCategorie);
        }
        return result;
    }

    @Override
    public Model update(int idModel, String nomModel) {
        Model Model=modelRepository.findById(idModel).get();
        Model.setNomModel(nomModel);
        return modelRepository.save(Model);
    }

    @Override
    public void delete(int idModel) {
        modelRepository.deleteById(idModel);
    }
}
