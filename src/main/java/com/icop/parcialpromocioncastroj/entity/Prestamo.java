package com.icop.parcialpromocioncastroj.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "prestamo")
@Getter
@Setter
@NoArgsConstructor
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tituloLibro;
    private LocalDate fechaPrestamo;
    private boolean devuelto = false;

    //muchos prestamos pueden permanecer al mismo socio 
    //nuestro lado dueño de la relaicon
    @ManyToOne
    @JoinColumn(name = "socio_id", nullable = false)
    private Socio socio;
}