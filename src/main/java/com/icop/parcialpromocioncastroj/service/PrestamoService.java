package com.icop.parcialpromocioncastroj.service;

import com.icop.parcialpromocioncastroj.dto.*;
import com.icop.parcialpromocioncastroj.entity.*;
import com.icop.parcialpromocioncastroj.repository.*;
import com.icop.parcialpromocioncastroj.exception.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final SocioRepository socioRepository;

    public PrestamoService(PrestamoRepository prestamoRepository,SocioRepository socioRepository) {
        this.prestamoRepository = prestamoRepository;
        this.socioRepository = socioRepository;
    }

    //convertimos la entidad en en dto
    private ResponsePrestamoDto convertir(Prestamo prestamo) {

        Socio socio = prestamo.getSocio();

        return new ResponsePrestamoDto(prestamo.getId(),prestamo.getTituloLibro(),prestamo.getFechaPrestamo(),prestamo.isDevuelto(),socio.getNombre() + " " + socio.getApellido() );
    }

    //insertar nuevo prestamo
    @Transactional
    public ResponsePrestamoDto registrar(RequestPrestamoDto dto) {

        //validar q exista el socio 
        Socio socio = socioRepository.findById(dto.getSocioId()).orElseThrow(() -> new RecursoNoEncontradoException("Socio no encontrado con id: " + dto.getSocioId()));

        //verificar qeu el libro no este prestado 
        if (prestamoRepository.existsByTituloLibroAndDevueltoFalse(dto.getTituloLibro())) {
            throw new ReglaNegocioException("El libro " + dto.getTituloLibro() + " ya se encuentra prestado");
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setTituloLibro(dto.getTituloLibro());
        prestamo.setFechaPrestamo(dto.getFechaPrestamo());
        prestamo.setDevuelto(false);
        prestamo.setSocio(socio);

        prestamoRepository.save(prestamo);

        return convertir(prestamo);
    }

    //listar prestamos de un socio
    @Transactional(readOnly = true)
    public List<ResponsePrestamoDto> listarPorSocio(Long id) {

        //verificar que socio exista 
        Socio socio = socioRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Socio no encontrado con id: " + id));

        return socio.getPrestamos().stream().map(this::convertir).toList();
    }
}
