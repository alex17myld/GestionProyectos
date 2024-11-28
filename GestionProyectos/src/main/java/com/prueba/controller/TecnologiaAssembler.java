package com.prueba.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.prueba.modelo.VO.Tecnologia;

@Component
public class TecnologiaAssembler implements RepresentationModelAssembler<Tecnologia, EntityModel<Tecnologia>> {

	@Override
	public EntityModel<Tecnologia> toModel(Tecnologia tecnologia) {

		return EntityModel.of(tecnologia, //
				linkTo(methodOn(TecnologiaController.class).one(tecnologia.getId())).withSelfRel(),
				linkTo(methodOn(TecnologiaController.class).allTecnologias()).withRel("tecnologia"));
	}
}
