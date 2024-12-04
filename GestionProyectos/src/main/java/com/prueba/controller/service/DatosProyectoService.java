package com.prueba.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.modelo.Repository.CertificacionRequeridaRepository;
import com.prueba.modelo.Repository.EntregableRepository;
import com.prueba.modelo.Repository.ExperienciaRequeridaRepository;
import com.prueba.modelo.Repository.FacturacionAnualRepository;
import com.prueba.modelo.Repository.FortalezaTecnologicaRepository;
import com.prueba.modelo.Repository.IdiomaRepository;
import com.prueba.modelo.Repository.LugarTrabajoRepository;
import com.prueba.modelo.Repository.TitulacionEmpleadoRepository;
import com.prueba.modelo.Repository.VolumetriaRepository;
import com.prueba.modelo.VO.CertificacionRequerida;
import com.prueba.modelo.VO.Entregable;
import com.prueba.modelo.VO.ExperienciaRequerida;
import com.prueba.modelo.VO.FacturacionAnual;
import com.prueba.modelo.VO.FortalezaTecnologia;
import com.prueba.modelo.VO.Idioma;
import com.prueba.modelo.VO.LugarTrabajo;
import com.prueba.modelo.VO.TitulacionEmpleado;
import com.prueba.modelo.VO.Volumetria;

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
