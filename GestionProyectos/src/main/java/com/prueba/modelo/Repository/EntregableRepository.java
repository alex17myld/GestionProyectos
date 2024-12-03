package com.prueba.modelo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.modelo.VO.Entregable;


@Repository
public interface EntregableRepository extends JpaRepository<Entregable, Integer>{
    Entregable findById(int id);
}
