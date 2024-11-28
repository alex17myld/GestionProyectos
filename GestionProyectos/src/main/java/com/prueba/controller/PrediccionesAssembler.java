package com.prueba.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.prueba.modelo.VO.Prediccion;

@Component
public class PrediccionesAssembler implements RepresentationModelAssembler<Prediccion, EntityModel<Prediccion>>{
	
	@Override
	public EntityModel<Prediccion> toModel(Prediccion predicciones) {

		return EntityModel.of(predicciones, //
				linkTo(methodOn(PrediccionesController.class).one(predicciones.getId())).withSelfRel(),
				linkTo(methodOn(PrediccionesController.class).allPredicciones()).withRel("predicciones"));
	}
}
