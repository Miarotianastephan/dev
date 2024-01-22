package com.dev.model.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dev.model.categorie.Categorie;


@Repository
public interface CategorieService  extends JpaRepository<Categorie, Integer> {
    // @Query("SELECT c.idCategorie, c.nomCategorie from categorie c where c.nomcategorie like %:nomCategorie%")
    // List<Object[]> findByNom(@Param("nomCategorie") String nomCategorie);
}
