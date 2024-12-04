package com.prueba.modelo.VO;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "resultados")
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultado")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_proyecto", nullable = false)
    private Proyecto proyecto;

    @Column(name = "resultado", nullable = false)
    private double resultado;

    @Column(name = "fecha_resultado", nullable = false)
    private Date fechaResultado;
}
