package com.prueba.modelo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.modelo.VO.CertificacionRequerida;

@Repository
public interface CertificacionRepository extends JpaRepository<CertificacionRequerida, Integer> {
    
}
