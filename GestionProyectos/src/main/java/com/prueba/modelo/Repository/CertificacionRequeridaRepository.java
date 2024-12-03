package com.prueba.modelo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.modelo.VO.CertificacionRequerida;

@Repository
public interface CertificacionRequeridaRepository extends JpaRepository<CertificacionRequerida, Integer>{
    CertificacionRequerida findById(int id);    
}
