package com.analisis_ia.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "tecnologias")
public class Tecnologia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "frecuencia_uso")
    private int frecuenciaUso;

    @Column(nullable = false, unique = true)
    private String nombre;

    @ManyToMany(mappedBy = "tecnologias", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Proyecto> proyectos; 

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Tecnologia tecnologia = (Tecnologia) obj;
        return id != null && id.equals(tecnologia.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
