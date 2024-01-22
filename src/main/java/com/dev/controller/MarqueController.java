package com.dev.controller;

import org.springframework.web.bind.annotation.RestController;

import com.dev.model.marque.Marque;
import com.dev.model.service.MarqueService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/main")
public class MarqueController {
    @Autowired
    MarqueService marqueService;

    @GetMapping(path = "/allMarque", produces = "application/json")
    public List<Marque> getAllMarque() {
        return marqueService.findAll();
    }

    @GetMapping(path = "/findMarqueById/{idMarque}", produces = "application/json")
    public Optional<Marque> findById(@PathVariable int idMarque) {
        try {
            return marqueService.findById(idMarque);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping(path = "/findMarqueByNom")
    public List<Object[]> findMarqueByNom(@RequestParam String nomMarque) {
        System.out.println(nomMarque);
        return marqueService.findByNom(nomMarque);
    }
    
    @PostMapping(path = "/newMarque")
    public Marque insertNewMarque(@RequestBody Marque marque) {
        Marque nouvelleMarque = marqueService.save(marque);
        return nouvelleMarque;
    }

    @GetMapping("/path")
    public String getMethodName() {
        return "hello";
    }
    
}
