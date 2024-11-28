package com.prueba.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.prueba.modelo.VO.Proyecto;

@Component
public class ProyectosAssembler implements RepresentationModelAssembler<Proyecto, EntityModel<Proyecto>> {
	
	public EntityModel<Proyecto> toModelPagin(Proyecto proyecto, int page) {

		return EntityModel.of(proyecto, //
				linkTo(methodOn(ProyectosController.class).one(proyecto.getId())).withSelfRel(),
				linkTo(methodOn(ProyectosController.class).allProyectosPagin(page)).withSelfRel(),
		        linkTo(methodOn(ProyectosController.class).allProyectosPagin(page)).withRel("prev").expand(),
		        linkTo(methodOn(ProyectosController.class).allProyectosPagin(page)).withRel("next").expand());
	}

	@Override
	public EntityModel<Proyecto> toModel(Proyecto proyecto) {
		// TODO Auto-generated method stub
		return null;
	}
}
