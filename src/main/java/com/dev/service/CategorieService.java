package com.dev.service;

import java.util.List;

import com.dev.model.categorie.Categorie;

public interface CategorieService {
    public List<Categorie> findAllCategorie();

    public Categorie save(Categorie categorie);

    public Categorie update(int idCategorie, String nomCategorie);

    public void delete(int idCategorie);

    public List<Categorie> findAllCategorieById(int[] idCategorie);
}
