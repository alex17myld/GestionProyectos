package com.prueba.controller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prueba.modelo.Repository.PrediccionesRepository;
import com.prueba.modelo.VO.Prediccion;

@Service
public class PrediccionesService {
	private PrediccionesRepository repository;

	public List<Prediccion> findAll() {
		return repository.findAll();
	}

	public Prediccion save(Prediccion newPrediccion) {
		return repository.save(newPrediccion);
	}

	public Optional<Prediccion> findById(Long id) {
		return repository.findById(id);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public Prediccion findByid(Long id) {
		return repository.findByid(id);
	}
}
