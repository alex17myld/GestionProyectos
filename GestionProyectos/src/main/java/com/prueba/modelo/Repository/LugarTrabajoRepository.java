package com.prueba.modelo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.modelo.VO.LugarTrabajo;

public interface LugarTrabajoRepository extends JpaRepository<LugarTrabajo, Integer>{
    LugarTrabajo findById(int id);
}
