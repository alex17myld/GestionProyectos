package com.prueba.modelo.VO;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "predicciones") // Nombre en minúscula para mantener consistencia con las convenciones
public class Prediccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id", nullable = false)
    private Proyecto proyecto;

    @Column(name = "probabilidad_exito", nullable = false)
    private Double probabilidadExito;

    @Column(name = "fecha_prediccion", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime fechaPrediccion;
}
