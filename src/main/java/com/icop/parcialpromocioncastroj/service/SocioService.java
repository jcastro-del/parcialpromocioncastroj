package com.icop.parcialpromocioncastroj.service;

import com.icop.parcialpromocioncastroj.dto.*;
import com.icop.parcialpromocioncastroj.entity.Socio;
import com.icop.parcialpromocioncastroj.repository.SocioRepository;
import com.icop.parcialpromocioncastroj.exception.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class SocioService {

    private final SocioRepository socioRepository;

    public SocioService(SocioRepository socioRepository) {
        this.socioRepository = socioRepository;
    }

    private ResponseSocioDto convertir(Socio socio) {
        return new ResponseSocioDto( socio.getNombre() + " " + socio.getApellido(), socio.getDni()
        );
    }

    //devolver todos los socios 
    public List<ResponseSocioDto> listar() {
        return socioRepository.findAll().stream().map(this::convertir).toList();
    }

    //buscar socio por el id 
    public ResponseSocioDto buscarPorId(Long id) {
        //si el socio existe lanzamos un error 
       Socio socio = socioRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Socio no encontrado con id: " + id));

        return convertir(socio);
    }

    //insertar socio nuevo 
    public ResponseSocioDto registrar(RequestSocioDto dto) {

        //verificar que dni no este registrado
        if (socioRepository.existsByDni(dto.getDni())) {
            throw new ReglaNegocioException("Ya existe un socio con el DNI: " + dto.getDni());
        }

        //creamos un socion con los datos del dto
        Socio socio = new Socio();
        socio.setNombre(dto.getNombre());
        socio.setApellido(dto.getApellido());
        socio.setDni(dto.getDni());

        socioRepository.save(socio);

        return convertir(socio);
    }

    //eliminar un socio
    @Transactional
    public void eliminar(Long id) {
        //buscamos y verificamso q el socio exista 
        Socio socio = socioRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Socio no encontrado con id: " + id));

        //no podemos elimirnar socios con prestamos asociados 
        if (!socio.getPrestamos().isEmpty()) {
            throw new ReglaNegocioException( "No se puede eliminar el socio con id: " + id + " porque tiene préstamos asociados");
        }

        //eliminamos el socio 
        socioRepository.delete(socio);
    }
}
