package com.prueba.modelo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.modelo.VO.Prediccion;

@Repository
public interface PrediccionesRepository extends JpaRepository<Prediccion, Long> {
	public Prediccion findByid(long id);
}
