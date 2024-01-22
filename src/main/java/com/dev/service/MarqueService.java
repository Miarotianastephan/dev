package com.dev.service;

import java.util.List;

import com.dev.model.marque.Marque;


public interface MarqueService {
    public List<Marque> findAllMarque();

    public Marque save(Marque marque);

    public Marque update(int idMarque, String nomMarque);
    
    public void delete(int idMarque);
}
