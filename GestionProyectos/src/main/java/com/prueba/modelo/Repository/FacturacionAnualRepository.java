package com.prueba.modelo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.modelo.VO.FacturacionAnual;


public interface  FacturacionAnualRepository extends JpaRepository<FacturacionAnual, Integer>{
    FacturacionAnual findById(int id);
}
