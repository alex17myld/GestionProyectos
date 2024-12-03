package com.prueba.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.prueba.modelo.VO.Resultado;

@Component
public class ResultadoAssembler implements RepresentationModelAssembler<Resultado, EntityModel<Resultado>>{

    @Override
	public EntityModel<Resultado> toModel(Resultado resultado) {

		return EntityModel.of(resultado, //
				linkTo(methodOn(ResultadoController.class).one(resultado.getId())).withSelfRel(),
				linkTo(methodOn(ResultadoController.class).allResultados()).withRel("resultados"));
	}
    
}
