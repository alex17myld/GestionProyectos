package com.prueba.modelo.VO;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString(exclude = "tecnologias")

@Entity
@Table(name = "proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Cliente cliente;

    @Column(name = "nombre_proyecto")
    private String nombreProyecto;
    private int duracion;
    private Double presupuesto;
    @Column(name = "numero_perfiles_requeridos")
    private int numeroPerfilesRequeridos;

    @ManyToMany
    @JoinTable(name = "Proyectos_Tecnologias", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "id_proyecto"), // Llave foránea hacia Estudiante
            inverseJoinColumns = @JoinColumn(name = "id_tecnologia") // Llave foránea hacia Curso
    )
    private Set<Tecnologia> tecnologias;

    @Column(name = "recursos_asignados")
    private List<String> recursosAsignados;
    private Date fechaInicio;
    private Date fechaFin;
    private Timestamp fechaRegistro;
    // nuevo
    private String solvenciaEconomicaEmpresa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "certificaciones_requeridas_id")
    private CertificacionRequerida certificacionRequerida;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "precio_hora_id")
    private PrecioHora precioHora;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fortaleza_tecnologica_id")
    private FortalezaTecnologia fortalezaTecnologica;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "experiencia_requerida_id")
    private ExperienciaRequerida experienciaRequerida;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "facturacion_anual_id")
    private FacturacionAnual facturacionAnual;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "entregables_id")
    private Entregable entregable;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idiomas_id")
    private Idioma idioma;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lugar_trabajo_id")
    private LugarTrabajo lugarTrabajo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "volumetria_id")
    private Volumetria volumetria;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "titulaciones_empleados_id")
    private TitulacionEmpleado titulacionEmpleado;

    @Column(name = "porcentaje_exito")
    private double porcentajeExito;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Proyecto project = (Proyecto) o;
        return id != null && Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
