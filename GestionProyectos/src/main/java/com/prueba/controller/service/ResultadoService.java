package com.prueba.controller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.prueba.modelo.Repository.ResultadoRepository;
import com.prueba.modelo.VO.Resultado;

public class ResultadoService {
    @Autowired
	private ResultadoRepository repository;
	
	
	public List<Resultado> findAll() {
		return repository.findAll();
	}
	
	public Resultado save(Resultado newProyecto) {
		return repository.save(newProyecto);
	}
	
	public Optional<Resultado> findById(Long id) {
		return repository.findById(id);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	public Resultado findByid(Long id) {
		return repository.findByid(id);
	}
}
