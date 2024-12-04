package com.prueba.modelo.VO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "experiencia_requerida")
public class ExperienciaRequerida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255) // Configuración para asegurar la validez del campo.
    private String nombre;
}
