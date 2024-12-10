package com.analisis_ia.demo.controllers;

import com.analisis_ia.demo.models.*;
import com.analisis_ia.demo.services.FormService;
import com.analisis_ia.demo.services.ProyectoDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/form")
public class FormController {

    @Autowired
    private FormService formService;

    @Autowired
    private ProyectoDTOService proyectoDTOService;

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

    /**
     * Endpoint actualizado para devolver ProyectoDTO
     */
    @GetMapping("/proyectos")
    public ResponseEntity<List<ProyectoDTO>> getProyectos() {
        List<ProyectoDTO> proyectosDTO = proyectoDTOService.getAllProyectos();
        return ResponseEntity.ok(proyectosDTO);
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
    @PostMapping("/agregarResultado")
    public Resultado postMethodName(@RequestBody Resultado resultado) {
        Resultado newResultado = new Resultado();
        newResultado.setExito(resultado.getExito());
        newResultado.setProyecto(resultado.getProyecto());
        return formService.setResultado(newResultado);
    }
    
}
