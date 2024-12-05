package com.analisis_ia.demo.controllers;

import com.analisis_ia.demo.models.*;
import com.analisis_ia.demo.services.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/form")
public class FormController {

    @Autowired
    private FormService formService;

    @GetMapping("/certificaciones")
    public List<CertificacionRequerida> getCertificaciones() {
        return formService.getAllCertificaciones();
    }

    @GetMapping("/entregables")
    public List<Entregable> getEntregables() {
        return formService.getAllEntregables();
    }

    @GetMapping("/experiencias")
    public List<ExperienciaRequerida> getExperiencias() {
        return formService.getAllExperiencias();
    }

    @GetMapping("/facturaciones")
    public List<FacturacionAnual> getFacturaciones() {
        return formService.getAllFacturaciones();
    }

    @GetMapping("/fortalezas")
    public List<FortalezaTecnologica> getFortalezas() {
        return formService.getAllFortalezas();
    }

    @GetMapping("/idiomas")
    public List<Idioma> getIdiomas() {
        return formService.getAllIdiomas();
    }

    @GetMapping("/lugares-trabajo")
    public List<LugarTrabajo> getLugaresTrabajo() {
        return formService.getAllLugaresTrabajo();
    }

    @GetMapping("/precios-hora")
    public List<PrecioHora> getPreciosHora() {
        return formService.getAllPreciosHora();
    }

    @GetMapping("/proyectos")
    public List<Proyecto> getProyectos() {
        return formService.getAllProyectos();
    }

    @GetMapping("/resultados")
    public List<Resultado> getResultados() {
        return formService.getAllResultados();
    }

    @GetMapping("/tecnologias")
    public List<Tecnologia> getTecnologias() {
        return formService.getAllTecnologias();
    }

    @GetMapping("/titulaciones")
    public List<TitulacionEmpleado> getTitulaciones() {
        return formService.getAllTitulaciones();
    }

    @GetMapping("/volumetrias")
    public List<Volumetria> getVolumetrias() {
        return formService.getAllVolumetrias();
    }
}
