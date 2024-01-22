package com.dev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.model.categorie.Categorie;
import com.dev.service.CategorieService;

import lombok.RequiredArgsConstructor;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categorie")
public class CategorieController {
    private final CategorieService categorieService;

    @GetMapping(path = "/allCategorie", produces = "application/json")
    public List<Categorie> getAllCategorie() {
        return categorieService.findAllCategorie();
    }

    @PostMapping("/insertCategorie")
    public Categorie insertCategorie(@RequestBody Categorie Categorie) {
        return categorieService.save(Categorie);
    }

    @PostMapping("/updateCategorie")
    public Categorie updateCategorie(@RequestParam int idCategorie, @RequestParam String nomCategorie) {
        return categorieService.update(idCategorie, nomCategorie);
    }

    @PostMapping("/deleteCategorie")
    public void deleteCategorie(@RequestParam int idCategorie) {
        categorieService.delete(idCategorie);
    }
}
