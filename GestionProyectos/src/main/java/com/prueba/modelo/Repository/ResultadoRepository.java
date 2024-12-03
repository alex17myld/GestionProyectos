package com.prueba.modelo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.modelo.VO.Resultado;
import java.util.List;


@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Long> {
	Resultado findByid(Long id);
	
}