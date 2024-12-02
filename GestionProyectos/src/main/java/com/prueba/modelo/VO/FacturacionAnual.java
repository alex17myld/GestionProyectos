package com.prueba.modelo.VO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="facturacion_anual")
public class FacturacionAnual {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
}
