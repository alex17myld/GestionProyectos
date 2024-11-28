package com.prueba.controller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.modelo.Repository.ClienteRepository;
import com.prueba.modelo.VO.Cliente;



@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente save(Cliente newCliente) {
		return repository.save(newCliente);
	}

	public Optional<Cliente> findById(Long id) {
		return repository.findById(id);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public Cliente findByid(Long id) {
		return repository.findByid(id);
	}
}
