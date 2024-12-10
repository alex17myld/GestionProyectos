package com.analisis_ia.demo.services;



import com.analisis_ia.demo.models.*;
import com.analisis_ia.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService {

    @Autowired
    private CertificacionRequeridaRepository certificacionRequeridaRepository;

    @Autowired
    private EntregableRepository entregableRepository;

    @Autowired
    private ExperienciaRequeridaRepository experienciaRequeridaRepository;

    @Autowired
    private FacturacionAnualRepository facturacionAnualRepository;

    @Autowired
    private FortalezaTecnologicaRepository fortalezaTecnologicaRepository;

    @Autowired
    private IdiomaRepository idiomaRepository;

    @Autowired
    private LugarTrabajoRepository lugarTrabajoRepository;

    @Autowired
    private PrecioHoraRepository precioHoraRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private ResultadoRepository resultadoRepository;

    @Autowired
    private TecnologiaRepository tecnologiaRepository;

    @Autowired
    private TitulacionEmpleadoRepository titulacionEmpleadoRepository;

    @Autowired
    private VolumetriaRepository volumetriaRepository;

    public List<CertificacionRequerida> getAllCertificaciones() {
        return certificacionRequeridaRepository.findAll();
    }

    public List<Entregable> getAllEntregables() {
        return entregableRepository.findAll();
    }

    public List<ExperienciaRequerida> getAllExperiencias() {
        return experienciaRequeridaRepository.findAll();
    }

    public List<FacturacionAnual> getAllFacturaciones() {
        return facturacionAnualRepository.findAll();
    }

    public List<FortalezaTecnologica> getAllFortalezas() {
        return fortalezaTecnologicaRepository.findAll();
    }

    public List<Idioma> getAllIdiomas() {
        return idiomaRepository.findAll();
    }

    public List<LugarTrabajo> getAllLugaresTrabajo() {
        return lugarTrabajoRepository.findAll();
    }

    public List<PrecioHora> getAllPreciosHora() {
        return precioHoraRepository.findAll();
    }

    public List<Proyecto> getAllProyectos() {
        return proyectoRepository.findAll();
    }

    public List<Resultado> getAllResultados() {
        return resultadoRepository.findAll();
    }

    public List<Tecnologia> getAllTecnologias() {
        return tecnologiaRepository.findAll();
    }

    public List<TitulacionEmpleado> getAllTitulaciones() {
        return titulacionEmpleadoRepository.findAll();
    }

    public List<Volumetria> getAllVolumetrias() {
        return volumetriaRepository.findAll();
    }

    public Resultado setResultado(Resultado resultado){
        return resultadoRepository.save(resultado);
    }
}
