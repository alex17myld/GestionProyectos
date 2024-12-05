package com.analisis_ia.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="resultados")
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Proyecto proyecto;
    private int exito;
}
