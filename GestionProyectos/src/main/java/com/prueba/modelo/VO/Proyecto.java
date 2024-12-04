package com.prueba.modelo.VO;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "tecnologias")

@Entity
@Table(name = "proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "nombre_proyecto", nullable = false)
    private String nombreProyecto;

    @Column(nullable = false)
    private int duracion;

    @Column(nullable = false)
    private Double presupuesto;

    @Column(name = "numero_perfiles_requeridos", nullable = false)
    private int numeroPerfilesRequeridos;

    @ManyToMany
    @JoinTable(
        name = "proyectos_tecnologias",
        joinColumns = @JoinColumn(name = "id_proyecto"),
        inverseJoinColumns = @JoinColumn(name = "id_tecnologia")
    )
    private Set<Tecnologia> tecnologias;

    @Column(name = "recursos_asignados")
    private String recursosAsignados; // Cambiado a String (almacenado como CSV en la base de datos)

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    private Date fechaFin;

    @Column(name = "fecha_registro", updatable = false, insertable = false)
    private Timestamp fechaRegistro;

    @Column(name = "solvencia_economica_empresa")
    private String solvenciaEconomicaEmpresa;

    @ManyToOne
    @JoinColumn(name = "certificaciones_requeridas_id")
    private CertificacionRequerida certificacionRequerida;

    @ManyToOne
    @JoinColumn(name = "precio_hora_id")
    private PrecioHora precioHora;

    @ManyToOne
    @JoinColumn(name = "fortaleza_tecnologica_id")
    private FortalezaTecnologia fortalezaTecnologica;

    @ManyToOne
    @JoinColumn(name = "experiencia_requerida_id")
    private ExperienciaRequerida experienciaRequerida;

    @ManyToOne
    @JoinColumn(name = "facturacion_anual_id")
    private FacturacionAnual facturacionAnual;

    @ManyToOne
    @JoinColumn(name = "entregables_id")
    private Entregable entregable;

    @ManyToOne
    @JoinColumn(name = "idiomas_id")
    private Idioma idioma;

    @ManyToOne
    @JoinColumn(name = "lugar_trabajo_id")
    private LugarTrabajo lugarTrabajo;

    @ManyToOne
    @JoinColumn(name = "volumetria_id")
    private Volumetria volumetria;

    @ManyToOne
    @JoinColumn(name = "titulaciones_empleados_id")
    private TitulacionEmpleado titulacionEmpleado;

    @Column(name = "porcentaje_exito")
    private double porcentajeExito;

    @Column(nullable = false)
    private boolean exito;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Proyecto proyecto = (Proyecto) o;
        return Objects.equals(id, proyecto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
