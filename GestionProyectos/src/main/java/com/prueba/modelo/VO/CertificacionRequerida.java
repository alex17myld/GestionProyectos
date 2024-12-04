package com.prueba.modelo.VO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "certificaciones_requeridas")
public class CertificacionRequerida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Usar Integer en lugar de int para permitir valores nulos si es necesario.

    @Column(nullable = false, length = 255) // Agregar restricciones según la base de datos.
    private String nombre;
}
