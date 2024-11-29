package com.prueba.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.controller.service.ProyectosService;
import com.prueba.modelo.VO.Proyecto;

@RestController
public class ProyectosController {

	//Clase importante
	private final ProyectosService service;
	private final ProyectosAssembler assembler;

	ProyectosController(ProyectosService service, ProyectosAssembler assembler) {
		this.service = service;
		this.assembler = assembler;
	}

	//Para recoger los proyectos y mostrarlos en paginas
	@GetMapping("/proyectosPag")
	public CollectionModel<EntityModel<Proyecto>> allProyectosPagin(@RequestParam(defaultValue = "0") int page) {
		//int size = service.findAll().size();
		int size = 10;
		Pageable pageable = PageRequest.of(page, size);
		Page<Proyecto> proyectosPage = service.getAllProducts(pageable);

		List<EntityModel<Proyecto>> proyectos = proyectosPage.getContent().stream().map(assembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(proyectos,
				linkTo(methodOn(ProyectosController.class).allProyectosPagin(page)).withSelfRel());
	}
	
	//Para recoger todos los proyectos
	@GetMapping("/proyectos")
	CollectionModel<EntityModel<Proyecto>> allProyectos() {

		List<EntityModel<Proyecto>> proyectos = service.findAll().stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());

		return CollectionModel.of(proyectos, linkTo(methodOn(ProyectosController.class).allProyectos()).withSelfRel());
	}
	// end::get-aggregate-root[]

	@PostMapping("/proyecto")
	ResponseEntity<?> newProyecto(@RequestBody Proyecto newProyecto) {
		EntityModel<Proyecto> proyecto = assembler.toModel(service.save(newProyecto));
		return ResponseEntity.created(proyecto.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(proyecto);
	}

	// Single item

	@GetMapping("/proyecto/{id}")
	EntityModel<Proyecto> one(@PathVariable Long id) {

		Proyecto proyecto = service.findByid(id);
		// .orElseThrow(() -> new EmployeeNotFoundException(id));

		return assembler.toModel(proyecto);
	}

	@PutMapping("/proyecto/{id}")
	ResponseEntity<?> replaceProyecto(@RequestBody Proyecto newProyecto, @PathVariable Long id) {

		Proyecto updatedProyecto = service.findById(id).map(proyecto -> {
			proyecto.setCliente(newProyecto.getCliente());
			proyecto.setNombreProyecto(newProyecto.getNombreProyecto());
			proyecto.setDuracion(newProyecto.getDuracion());
			proyecto.setPresupuesto(newProyecto.getPresupuesto());
			proyecto.setTecnologias(newProyecto.getTecnologias());
			proyecto.setRecursosAsignados(newProyecto.getRecursosAsignados());
			proyecto.setResultado(newProyecto.getResultado());
			proyecto.setFechaInicio(newProyecto.getFechaInicio());
			proyecto.setFechaFin(newProyecto.getFechaFin());
			proyecto.setFechaRegistro(newProyecto.getFechaRegistro());
			proyecto.setSolvenciaEconomicaEmpresa(newProyecto.getSolvenciaEconomicaEmpresa());
			proyecto.setCertificacionesRequeridas(newProyecto.getCertificacionesRequeridas());
			proyecto.setPrecioHora(newProyecto.getPrecioHora());
			proyecto.setFortalezaTecnologica(newProyecto.getFortalezaTecnologica());
			proyecto.setExperienciaRequerida(newProyecto.getExperienciaRequerida());
			proyecto.setNumeroPerfilesRequeridos(newProyecto.getNumeroPerfilesRequeridos());
			proyecto.setCurriculums(newProyecto.getCurriculums());
			proyecto.setIdiomas(newProyecto.getIdiomas());
			proyecto.setLugarTrabajo(newProyecto.getLugarTrabajo());
			proyecto.setFacturacionAnual(newProyecto.getFacturacionAnual());
			proyecto.setVolumetria(newProyecto.getVolumetria());
			proyecto.setEntregablesOferta(newProyecto.getEntregablesOferta());
			
			return service.save(proyecto);
		}).orElseGet(() -> {
			return service.save(newProyecto);
		});

		EntityModel<Proyecto> proyecto = assembler.toModel(updatedProyecto);
		return ResponseEntity //
				.created(proyecto.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(proyecto);
	}

//	@DeleteMapping("/proyecto/{id}")
//	ResponseEntity<?> deleteProyecto(@PathVariable Long id) {
//		service.deleteById(id);
//		return ResponseEntity.noContent().build();
//	}
}
