package com.icop.parcialpromocioncastroj.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "socio")
@Getter
@Setter
@NoArgsConstructor
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String dni;

    //un socio puede tener muchos prestamos 
    @OneToMany(mappedBy = "socio")
    private List<Prestamo> prestamos = new ArrayList<>();
}