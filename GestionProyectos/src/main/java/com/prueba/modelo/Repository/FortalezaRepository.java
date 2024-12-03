package com.prueba.modelo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.prueba.modelo.VO.FortalezaTecnologia;

@Repository
public interface FortalezaRepository extends JpaRepository<FortalezaTecnologia, Integer> {
    
}