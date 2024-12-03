package com.prueba.modelo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.modelo.VO.Idioma;

public interface  IdiomaRepository extends JpaRepository<Idioma, Integer>{
    Idioma findById(int id);
}
