package com.prueba.modelo.VO;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Set;


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
    
    @ManyToMany
    @JoinTable(
            name = "Proyectos_Tecnologias", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "id_proyecto"), // Llave foránea hacia Estudiante
            inverseJoinColumns = @JoinColumn(name = "id_tecnologia") // Llave foránea hacia Curso
        )
    private Set<Tecnologia> tecnologias;
    
    @Column(name = "recursos_asignados")
    private List<String> recursosAsignados; 
    private Resultado resultado; 
    private Date fechaInicio; 
    private Date fechaFin; 
    private Timestamp fechaRegistro;
    //nuevo
    private String solvenciaEconomicaEmpresa;
    private Boolean certificacionesRequeridas;
    private Double precioHora;
    private String fortalezaTecnologica;
    private String experienciaRequerida;
    private int numeroPerfilesRequeridos;
    private String titulacionesEmpleados;
    private Boolean idiomas;
    private String lugarTrabajo;
    private Double facturacionAnual;
    private Boolean volumetria;
    private String entregablesOferta;
    
    private enum Resultado{
    	EXITO,
    	FRACASO
    }
    
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
