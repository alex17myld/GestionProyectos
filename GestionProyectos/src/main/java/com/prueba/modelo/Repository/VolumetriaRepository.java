package com.prueba.modelo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.modelo.VO.Volumetria;

@Repository
public interface VolumetriaRepository extends JpaRepository<Volumetria, Integer> {
    Volumetria findById(int id);
}
