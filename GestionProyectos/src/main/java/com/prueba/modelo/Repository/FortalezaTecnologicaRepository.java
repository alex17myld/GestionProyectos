package com.prueba.modelo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.modelo.VO.FortalezaTecnologia;

public interface FortalezaTecnologicaRepository extends JpaRepository<FortalezaTecnologia, Object>{
    FortalezaTecnologia findById(int id);
}
