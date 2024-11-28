package com.prueba.controller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prueba.modelo.Repository.TecnologiaRepository;
import com.prueba.modelo.VO.Tecnologia;

@Service
public class TecnologiaService {

private TecnologiaRepository repository;
	
	
	public List<Tecnologia> findAll() {
		return repository.findAll();
	}
	
	public Tecnologia save(Tecnologia newTecnologia) {
		return repository.save(newTecnologia);
	}
	
	public Optional<Tecnologia> findById(Long id) {
		return repository.findById(id);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	public Tecnologia findByid(Long id) {
		return repository.findByid(id);
	}
}
