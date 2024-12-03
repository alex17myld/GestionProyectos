package com.prueba.modelo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.prueba.modelo.VO.ExperienciaRequerida;

@Repository
public interface ExperienciaRepository extends JpaRepository<ExperienciaRequerida, Integer> {
    
}