package com.prueba.modelo.VO;

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
@ToString(exclude = "proyectos")

@Entity
@Table(name = "tecnologias") // Nombre de tabla en minúsculas por convención
public class Tecnologia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "frecuencia_uso", nullable = true)
    private Long frecuenciaUso;

    @ManyToMany(mappedBy = "tecnologias", fetch = FetchType.LAZY)
    private Set<Proyecto> proyectos;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tecnologia tecnologia = (Tecnologia) o;
        return id != null && Objects.equals(id, tecnologia.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
