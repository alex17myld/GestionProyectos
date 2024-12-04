package com.prueba.modelo.VO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "precio_hora") // Nombre de la tabla coincide con el SQL
public class PrecioHora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Cambiado a Integer para manejar valores nulos antes de persistir

    @Column(nullable = false, length = 255) // Agrega restricciones a la columna
    private String nombre;
}
