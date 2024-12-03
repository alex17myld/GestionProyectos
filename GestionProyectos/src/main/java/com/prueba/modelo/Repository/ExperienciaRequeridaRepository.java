package com.prueba.modelo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.modelo.VO.ExperienciaRequerida;

public interface ExperienciaRequeridaRepository extends JpaRepository<ExperienciaRequerida, Integer> {
    ExperienciaRequerida findById(int id);
}
