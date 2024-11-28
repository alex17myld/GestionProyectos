package com.prueba.modelo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.modelo.VO.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	public Cliente findByid(long id);
}
