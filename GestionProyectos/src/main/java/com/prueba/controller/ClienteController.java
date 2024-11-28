package com.prueba.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.prueba.controller.service.ClienteService;
import com.prueba.modelo.VO.Cliente;


@RestController
public class ClienteController {

	@Autowired
	private final ClienteService service;
	@Autowired
	private final ClienteAssembler assembler;

	ClienteController(ClienteService service, ClienteAssembler assembler) {
		this.service = service;
		this.assembler = assembler;
	}

	@GetMapping("/cliente")
	CollectionModel<EntityModel<Cliente>> allClientes() {

		List<EntityModel<Cliente>> clientes = service.findAll().stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());

		return CollectionModel.of(clientes, linkTo(methodOn(ClienteController.class).allClientes()).withSelfRel());
	}
	// end::get-aggregate-root[]

	@PostMapping("/cliente")
	ResponseEntity<?> newCliente(@RequestBody Cliente newCliente) {
		EntityModel<Cliente> cliente = assembler.toModel(service.save(newCliente));
		return ResponseEntity.created(cliente.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(cliente);
	}

	// Single item

	@GetMapping("/cliente/{id}")
	EntityModel<Cliente> one(@PathVariable Long id) {

		Cliente cliente = service.findByid(id);
		// .orElseThrow(() -> new EmployeeNotFoundException(id));

		return assembler.toModel(cliente);
	}

//	@PutMapping("/cliente/{id}")
//	ResponseEntity<?> replaceCliente(@RequestBody Cliente newCliente, @PathVariable Long id) {
//		
//		Cliente updatedCliente =  service.findById(id).map(cliente -> {
//			cliente.setNombre(newCliente.getNombre());
//			
//			return service.save(cliente);
//		}).orElseGet(() -> {
//			return service.save(newCliente);
//		});
//		
//		EntityModel<Cliente> cliente = assembler.toModel(updatedCliente);
//		return ResponseEntity //
//			      .created(cliente.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
//			      .body(cliente);
//	}
//
//	@DeleteMapping("/cliente/{id}")
//	ResponseEntity<?> deleteCliente(@PathVariable Long id) {
//		service.deleteById(id);
//		 return ResponseEntity.noContent().build();
//	}
}
