package com.dev.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.model.categorie.Categorie;
import com.dev.model.models.Model;
import com.dev.model.models.ModelCategorie;



@Service
public interface ModelService {
    public List<Model> findAllModel();

    public Model save(Model model);

    public Model update(int idModel, String nomModel);
    
    public void delete(int idModel);
}
