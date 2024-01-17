package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dev.models.*;;

public interface VoitureinfoMiRep extends JpaRepository<VoitureinfoMi, Integer> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
    // 
}