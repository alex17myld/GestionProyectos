package com.prueba.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.prueba.modelo.VO.Cliente;

@Component
public class ClienteAssembler implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {

	@Override
	public EntityModel<Cliente> toModel(Cliente clientes) {

		return EntityModel.of(clientes, //
				linkTo(methodOn(ClienteController.class).one(clientes.getId())).withSelfRel(),
				linkTo(methodOn(ClienteController.class).allClientes()).withRel("cliente"));
	}
}
