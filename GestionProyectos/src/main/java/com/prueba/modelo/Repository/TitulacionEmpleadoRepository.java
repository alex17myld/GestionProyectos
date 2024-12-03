package com.prueba.modelo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.modelo.VO.TitulacionEmpleado;

public interface TitulacionEmpleadoRepository extends JpaRepository<TitulacionEmpleado, Integer>{
    TitulacionEmpleado findById(int id);
}
