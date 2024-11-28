package com.prueba.modelo.VO;


import java.util.Objects;
import java.util.Set;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
@Table(name = "Tecnologias")
public class Tecnologia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	@Column(name = "frecuencia_uso")
	private Long frecuenciaUso;
	@ManyToMany
    @JoinTable(
            name = "Proyectos_Tecnologias", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "id_tecnologia"), // Llave foránea hacia Estudiante
            inverseJoinColumns = @JoinColumn(name = "id_proyecto") // Llave foránea hacia Curso
        )
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
