package com.prueba.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.controller.service.PrediccionesService;
import com.prueba.modelo.VO.Prediccion;

@RestController
public class PrediccionesController {
	private final PrediccionesService service;
	private final PrediccionesAssembler assembler;

	PrediccionesController(PrediccionesService service, PrediccionesAssembler assembler) {
		this.service = service;
		this.assembler = assembler;
	}

	@GetMapping("/predicciones")
	CollectionModel<EntityModel<Prediccion>> allPredicciones() {

		List<EntityModel<Prediccion>> predicciones = service.findAll().stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());

		return CollectionModel.of(predicciones, linkTo(methodOn(PrediccionesController.class).allPredicciones()).withSelfRel());
	}
	// end::get-aggregate-root[]

	@PostMapping("/predicciones")
	ResponseEntity<?> newPrediccion(@RequestBody Prediccion newPrediccion) {
		EntityModel<Prediccion> prediccion = assembler.toModel(service.save(newPrediccion));
		return ResponseEntity.created(prediccion.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(prediccion);
	}

	// Single item

	@GetMapping("/predicciones/{id}")
	EntityModel<Prediccion> one(@PathVariable Long id) {

		Prediccion prediccion = service.findByid(id);
		// .orElseThrow(() -> new EmployeeNotFoundException(id));

		return assembler.toModel(prediccion);
	}

//	@PutMapping("/predicciones/{id}")
//	ResponseEntity<?> replacePrediccion(@RequestBody Prediccion newPrediccion, @PathVariable Long id) {
//		
//		Prediccion updatedPredicciones =  service.findById(id).map(prediccion -> {
//			prediccion.setProyecto(newPrediccion.getProyecto());
//			prediccion.setProbabilidadExito(newPrediccion.getProbabilidadExito());
//			prediccion.setFechaPrediccion(newPrediccion.getFechaPrediccion());
//			
//			return service.save(prediccion);
//		}).orElseGet(() -> {
//			return service.save(newPrediccion);
//		});
//		
//		EntityModel<Prediccion> prediccion = assembler.toModel(updatedPredicciones);
//		return ResponseEntity //
//			      .created(prediccion.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
//			      .body(prediccion);
//	}
//
//	@DeleteMapping("/predicciones/{id}")
//	ResponseEntity<?> deletePrediccion(@PathVariable Long id) {
//		service.deleteById(id);
//		 return ResponseEntity.noContent().build();
//	}
}
