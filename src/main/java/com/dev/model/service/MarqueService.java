package com.dev.model.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dev.model.marque.Marque;

@Repository
public interface MarqueService extends JpaRepository<Marque, Integer> {
    @Query("SELECT m.idMarque, m.nomMarque from Marque m where m.nomMarque like %:nomMarque%")
    List<Object[]> findByNom(@Param("nomMarque") String nomMarque);
}
