package com.icop.parcialpromocioncastroj.controller;

import com.icop.parcialpromocioncastroj.dto.*;
import com.icop.parcialpromocioncastroj.service.PrestamoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    // registrar un prestamo
    @PostMapping
    public ResponseEntity<ResponsePrestamoDto> registrar( @RequestBody RequestPrestamoDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(prestamoService.registrar(dto));
    }

    //listar socio pro id 
    @GetMapping("/socio/{id}")
    public List<ResponsePrestamoDto> listarPorSocio(@PathVariable Long id) {return prestamoService.listarPorSocio(id);}
}