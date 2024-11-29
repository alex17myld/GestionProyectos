package com.prueba.controller;

import org.springframework.web.bind.annotation.RestController;

import com.prueba.controller.service.ProyectosService;
import com.prueba.modelo.VO.Proyecto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
public class IAController {
    
    private final ProyectosService service;
    private final ProyectosAssembler assembler;

    IAController(ProyectosService service, ProyectosAssembler assembler) {
		this.service = service;
		this.assembler = assembler;
	}

    @GetMapping("/IA")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    /*"certificaciones_requeridas",
            "precio_hora",
            "fortaleza_tecnologica",
            "experiencia_requerida",
            "numero_perfiles_requeridos",
            "curriculums" */

    @PostMapping("/IA")
    public Proyecto postMethodName(@RequestBody Proyecto proyecto) {
        //TODO: process POST request
        EntityModel<Proyecto> proyectoGuardado = assembler.toModel(service.save(proyecto));

        return proyecto;
    }
    
}
