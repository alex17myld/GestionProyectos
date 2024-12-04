package com.prueba.controller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.prueba.modelo.Repository.ProyectosRepository;
import com.prueba.modelo.VO.Proyecto;

@Service
public class ProyectosService {
	
	@Autowired
	private ProyectosRepository repository;
	
	
	public List<Proyecto> findAll() {
		return repository.findAll();
	}
	
	public Proyecto save(Proyecto newProyecto) {
		return repository.save(newProyecto);
	}
	
	public Optional<Proyecto> findById(Long id) {
		return repository.findById(id);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	public Proyecto findByid(Long id) {
		return repository.findByid(id);
	}
	
	public Page<Proyecto> getAllProducts(Pageable pageable) {
        return repository.findAllWithPagination(pageable);
    }


	public Proyecto getLastInserted() {
        return repository.findLastInserted();
    }
	
}
