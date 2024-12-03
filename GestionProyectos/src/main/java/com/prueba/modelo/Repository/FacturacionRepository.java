package com.prueba.modelo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.prueba.modelo.VO.FacturacionAnual;

@Repository
public interface FacturacionRepository extends JpaRepository<FacturacionAnual, Integer> {
    
}