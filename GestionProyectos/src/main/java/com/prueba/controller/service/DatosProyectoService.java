package com.prueba.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.prueba.modelo.Repository.*;
import com.prueba.modelo.VO.*;

@Service
public class DatosProyectoService {

    @Autowired
    private TitulacionEmpleadoRepository titulacionEmpleadoRepository;

    @Autowired
    private LugarTrabajoRepository lugarTrabajoRepository;

    @Autowired
    private IdiomaRepository idiomaRepository;

    @Autowired
    private FortalezaTecnologicaRepository fortalezaTecnologicaRepository;

    @Autowired
    private FacturacionAnualRepository facturacionAnualRepository;

    @Autowired
    private CertificacionRequeridaRepository certificacionRequeridaRepository;

    @Autowired
    private VolumetriaRepository volumetriaRepository;

    @Autowired
    private EntregableRepository entregableRepository;

    @Autowired
    private ExperienciaRequeridaRepository experienciaRequeridaRepository;

    // Métodos para obtener todos los registros

    public List<TitulacionEmpleado> getAllTitulacionEmpleados() {
        return titulacionEmpleadoRepository.findAll();
    }

    public List<LugarTrabajo> getAllLugaresTrabajo() {
        return lugarTrabajoRepository.findAll();
    }

    public List<Idioma> getAllIdiomas() {
        return idiomaRepository.findAll();
    }

    public List<FortalezaTecnologia> getAllFortalezasTecnologicas() {
        return fortalezaTecnologicaRepository.findAll();
    }

    public List<FacturacionAnual> getAllFacturacionesAnuales() {
        return facturacionAnualRepository.findAll();
    }

    public List<CertificacionRequerida> getAllCertificacionesRequeridas() {
        return certificacionRequeridaRepository.findAll();
    }

    public List<Volumetria> getAllVolumetrias() {
        return volumetriaRepository.findAll();
    }

    public List<Entregable> getAllEntregables() {
        return entregableRepository.findAll();
    }

    public List<ExperienciaRequerida> getAllExperienciasRequeridas() {
        return experienciaRequeridaRepository.findAll();
    }
}
}
