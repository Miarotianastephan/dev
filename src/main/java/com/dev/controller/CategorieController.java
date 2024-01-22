package com.dev.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.model.categorie.Categorie;
import com.dev.model.service.CategorieService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/main")
public class CategorieController {
    @Autowired
    CategorieService categorieService;
    
    @GetMapping(path = "/allCategorie", produces = "application/json")
    public List<Categorie> getAllCategorie() {
        return categorieService.findAll();
    }

    @GetMapping(path = "/findCategorieById/{idCategorie}", produces = "application/json")
    public Optional<Categorie> findById(@PathVariable int idCategorie) {
        try {
            return categorieService.findById(idCategorie);
        } catch (Exception e) {
            throw e;
        }
    }
    
    @PostMapping(path = "/newCategorie")
    public void insertNewMarque(@RequestBody Categorie categorie) {
        categorieService.save(categorie);
    }

    
    // @PostMapping(path = "/findCategorieByNom")
    // public List<Object[]> findCategorieByNom(@RequestParam String nomCategorie) {
    //     System.out.println(nomCategorie);
    //     return categorieService.findByNom(nomCategorie);
    // }
}
