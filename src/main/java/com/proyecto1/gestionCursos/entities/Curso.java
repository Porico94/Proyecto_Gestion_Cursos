package com.proyecto1.gestionCursos.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="cursos")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128, nullable = false)
    private String titulo;

    @Column(length = 256)
    private String descripcion;

    @Column(nullable = false)
    private int nivel;

    @Column(name="estado_publicacion")
    private boolean isPublicado;
}
