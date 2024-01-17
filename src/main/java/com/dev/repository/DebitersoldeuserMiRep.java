package com.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.models.*;;

public interface DebitersoldeuserMiRep extends JpaRepository<DebitersoldeuserMi, Integer> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
    // // ---select * from Debitersoldeuser where iduser='' and etats=0;-->throw new ExceptionCar("session plus valide");
    // @Query(value = "select tauxpourcent from Debitersoldeuser where coderegle=\'C000\' " , nativeQuery = true )
    // List <Double> getTauxCommisionByIduser() ;
}