package com.prueba.modelo.VO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="titulacion_empleados")

public class TitulacionEmpleado {
    
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
}
