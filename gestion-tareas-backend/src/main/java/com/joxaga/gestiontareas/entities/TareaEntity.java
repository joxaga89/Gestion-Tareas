package com.joxaga.gestiontareas.entities;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "tareas")

public class TareaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String titulo;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private boolean completado;
}
