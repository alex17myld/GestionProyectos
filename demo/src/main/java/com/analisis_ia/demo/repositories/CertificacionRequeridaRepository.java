package com.analisis_ia.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.analisis_ia.demo.models.CertificacionRequerida;

@Repository
public interface CertificacionRequeridaRepository extends JpaRepository<CertificacionRequerida, Integer> {
    
}
