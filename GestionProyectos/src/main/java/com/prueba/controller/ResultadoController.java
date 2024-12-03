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

import com.prueba.controller.service.ResultadoService;
import com.prueba.modelo.VO.Resultado;

@RestController
public class ResultadoController {

	//Clase importante
	private final ResultadoService service;
	private final ResultadoAssembler assembler;

	ResultadoController(ResultadoService service, ResultadoAssembler assembler) {
		this.service = service;
		this.assembler = assembler;
	}
	
	//Para recoger todos los proyectos
	@GetMapping("/resultados")
	CollectionModel<EntityModel<Resultado>> allResultados() {

		List<EntityModel<Resultado>> resultados = service.findAll().stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());

		return CollectionModel.of(resultados, linkTo(methodOn(ResultadoController.class).allResultados()).withSelfRel());
	}
	// end::get-aggregate-root[]

	@PostMapping("/resultado")
	ResponseEntity<?> newProyecto(@RequestBody Resultado newResultado) {
		EntityModel<Resultado> resultado = assembler.toModel(service.save(newResultado));
		return ResponseEntity.created(resultado.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(resultado);
	}

	// Single item

	@GetMapping("/resultado/{id}")
	EntityModel<Resultado> one(@PathVariable Long id) {

		Resultado resultado = service.findByid(id);
		// .orElseThrow(() -> new EmployeeNotFoundException(id));

		return assembler.toModel(resultado);
	}

	@PutMapping("/resultado/{id}")
	ResponseEntity<?> replaceProyecto(@RequestBody Resultado newResultado, @PathVariable Long id) {

		Resultado updatedResultado = service.findById(id).map(resultado -> {
            resultado.setId(newResultado.getId());
			resultado.setProyecto(newResultado.getProyecto());
			resultado.setResultado(newResultado.getResultado());
			resultado.setFechaResultado(newResultado.getFechaResultado());

			
			return service.save(resultado);
		}).orElseGet(() -> {
			return service.save(newResultado);
		});

		EntityModel<Resultado> resultado = assembler.toModel(updatedResultado);
		return ResponseEntity //
				.created(resultado.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(resultado);
	}

//	@DeleteMapping("/resultado/{id}")
//	ResponseEntity<?> deleteResultado(@PathVariable Long id) {
//		service.deleteById(id);
//		return ResponseEntity.noContent().build();
//	}
}
