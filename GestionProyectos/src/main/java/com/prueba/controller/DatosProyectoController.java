package com.prueba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.controller.service.DatosProyectoService;
import com.prueba.modelo.VO.CertificacionRequerida;
import com.prueba.modelo.VO.Entregable;
import com.prueba.modelo.VO.ExperienciaRequerida;
import com.prueba.modelo.VO.FacturacionAnual;
import com.prueba.modelo.VO.FortalezaTecnologia;
import com.prueba.modelo.VO.Idioma;
import com.prueba.modelo.VO.LugarTrabajo;
import com.prueba.modelo.VO.TitulacionEmpleado;
import com.prueba.modelo.VO.Volumetria;

@RestController
public class DatosProyectoController {

    @Autowired
    private DatosProyectoService datosProyectoService;

    @GetMapping("/titulacion-empleados")
    public List<TitulacionEmpleado> getTitulacionEmpleados() {
        return datosProyectoService.getAllTitulacionEmpleados();
    }

    @GetMapping("/lugares-trabajo")
    public List<LugarTrabajo> getLugaresTrabajo() {
        return datosProyectoService.getAllLugaresTrabajo();
    }

    @GetMapping("/idiomas")
    public List<Idioma> getIdiomas() {
        return datosProyectoService.getAllIdiomas();
    }

    @GetMapping("/fortalezas-tecnologicas")
    public List<FortalezaTecnologia> getFortalezasTecnologicas() {
        return datosProyectoService.getAllFortalezasTecnologicas();
    }

    @GetMapping("/facturaciones-anuales")
    public List<FacturacionAnual> getFacturacionesAnuales() {
        return datosProyectoService.getAllFacturacionesAnuales();
    }

    @GetMapping("/certificaciones-requeridas")
    public List<CertificacionRequerida> getCertificacionesRequeridas() {
        return datosProyectoService.getAllCertificacionesRequeridas();
    }

    @GetMapping("/volumetrias")
    public List<Volumetria> getVolumetrias() {
        return datosProyectoService.getAllVolumetrias();
    }

    @GetMapping("/entregables")
    public List<Entregable> getEntregables() {
        return datosProyectoService.getAllEntregables();
    }

    @GetMapping("/experiencias-requeridas")
    public List<ExperienciaRequerida> getExperienciasRequeridas() {
        return datosProyectoService.getAllExperienciasRequeridas();
    }
}

