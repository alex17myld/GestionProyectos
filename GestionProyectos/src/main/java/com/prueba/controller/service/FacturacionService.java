package com.prueba.controller.service;

import com.prueba.modelo.Repository.FacturacionAnualRepository;
import com.prueba.modelo.VO.FacturacionAnual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FacturacionService {

    @Autowired
    private FacturacionAnualRepository facturacionAnualRepository;

    public Optional<FacturacionAnual> findById(Integer id) {
        return facturacionAnualRepository.findById(id); // Usa el método heredado de JpaRepository
    }

    public FacturacionAnual save(FacturacionAnual facturacionAnual) {
        return facturacionAnualRepository.save(facturacionAnual);
    }

    public void deleteById(Integer id) {
        facturacionAnualRepository.deleteById(id); // Usa el método heredado de JpaRepository
    }

    public Iterable<FacturacionAnual> findAll() {
        return facturacionAnualRepository.findAll(); // Usa el método heredado de JpaRepository
    }
}
