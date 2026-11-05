package com.icop.parcialpromocioncastroj.repository;

import com.icop.parcialpromocioncastroj.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    //buscamos si hay un prestamo para ese titulo
    boolean existsByTituloLibroAndDevueltoFalse(String tituloLibro);
}