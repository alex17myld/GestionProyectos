package com.analisis_ia.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.analisis_ia.demo.models.Entregable;

@Repository
public interface EntregableRepository extends JpaRepository<Entregable, Integer> {
    
}
