package com.prueba.modelo.Repository;



import com.prueba.modelo.VO.Volumetria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolumetriaRepository extends JpaRepository<Volumetria, Integer> { // Cambiado a Integer
}

