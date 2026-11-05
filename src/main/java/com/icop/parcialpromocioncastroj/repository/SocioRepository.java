package com.icop.parcialpromocioncastroj.repository;

import com.icop.parcialpromocioncastroj.entity.Socio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocioRepository extends JpaRepository<Socio, Long> {
    //comprobamos si ya existe un socio 
    boolean existsByDni(String dni);
}