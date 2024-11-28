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

import com.prueba.controller.service.TecnologiaService;
import com.prueba.modelo.VO.Tecnologia;


@RestController
public class TecnologiaController {
	private final TecnologiaService service;
	private final TecnologiaAssembler assembler;

	TecnologiaController(TecnologiaService service, TecnologiaAssembler assembler) {
		this.service = service;
		this.assembler = assembler;
	}

	@GetMapping("/tecnologia")
	CollectionModel<EntityModel<Tecnologia>> allTecnologias() {

		List<EntityModel<Tecnologia>> tecnologias = service.findAll().stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());

		return CollectionModel.of(tecnologias, linkTo(methodOn(TecnologiaController.class).allTecnologias()).withSelfRel());
	}
	// end::get-aggregate-root[]

	@PostMapping("/tecnologia")
	ResponseEntity<?> newTecnologia(@RequestBody Tecnologia newTecnologia) {
		EntityModel<Tecnologia> tecnologia = assembler.toModel(service.save(newTecnologia));
		return ResponseEntity.created(tecnologia.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(tecnologia);
	}

	// Single item

	@GetMapping("/tecnologia/{id}")
	EntityModel<Tecnologia> one(@PathVariable Long id) {

		Tecnologia tecnologia = service.findByid(id);
		// .orElseThrow(() -> new EmployeeNotFoundException(id));

		return assembler.toModel(tecnologia);
	}

//	@PutMapping("/tecnologia/{id}")
//	ResponseEntity<?> replaceTecnologiao(@RequestBody Tecnologia newTecnologia, @PathVariable Long id) {
//		
//		Tecnologia updatedTecnologia =  service.findById(id).map(tecnologia -> {
//			tecnologia.setNombre(newTecnologia.getNombre());
//			tecnologia.setFrecuenciaUso(newTecnologia.getFrecuenciaUso());
//			tecnologia.setProyectos(newTecnologia.getProyectos());
//
//			return service.save(tecnologia);
//		}).orElseGet(() -> {
//			return service.save(newTecnologia);
//		});
//		
//		EntityModel<Tecnologia> tecnologia = assembler.toModel(updatedTecnologia);
//		return ResponseEntity //
//			      .created(tecnologia.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
//			      .body(tecnologia);
//	}
//
//	@DeleteMapping("/tecnologia/{id}")
//	ResponseEntity<?> deleteTecnologia(@PathVariable Long id) {
//		service.deleteById(id);
//		 return ResponseEntity.noContent().build();
//	}

}
