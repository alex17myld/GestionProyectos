package com.analisis_ia.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "proyectos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int duracion;

    private Date fechaFin;
    private Date fechaInicio;
    private Date fechaRegistro;

    private String nombreProyecto;
    private Double presupuesto;

    @Column(columnDefinition = "TINYINT")
    private Byte resultado;

    private String cliente;
    private String solvenciaEconomicaEmpresa;
    private int numeroPerfilesRequeridos;

    @ManyToOne(fetch = FetchType.LAZY) // Relación de muchos a uno
    @JoinColumn(name = "certificaciones_requeridas_id", referencedColumnName = "id")
    private CertificacionRequerida certificacionRequerida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entregables_id", referencedColumnName = "id")
    private Entregable entregable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "experiencia_requerida_id", referencedColumnName = "id")
    private ExperienciaRequerida experienciaRequerida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facturacion_anual_id", referencedColumnName = "id")
    private FacturacionAnual facturacionAnual;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fortaleza_tecnologica_id", referencedColumnName = "id")
    private FortalezaTecnologica fortalezaTecnologica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idiomas_id", referencedColumnName = "id")
    private Idioma idioma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lugar_trabajo_id", referencedColumnName = "id")
    private LugarTrabajo lugarTrabajo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "precio_hora_id", referencedColumnName = "id")
    private PrecioHora precioHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "titulaciones_empleados_id", referencedColumnName = "id")
    private TitulacionEmpleado titulacionEmpleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "volumetria_id", referencedColumnName = "id")
    private Volumetria volumetria;

    private Double porcentajeExito;

    @ManyToMany
    @JoinTable(
        name = "proyectos_tecnologias",
        joinColumns = @JoinColumn(name = "id_proyecto"),
        inverseJoinColumns = @JoinColumn(name = "id_tecnologia")
    )
    @JsonIgnore
    private Set<Tecnologia> tecnologias = new HashSet<>();
    
}
