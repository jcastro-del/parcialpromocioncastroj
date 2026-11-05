package com.icop.parcialpromocioncastroj.controller;

import com.icop.parcialpromocioncastroj.dto.*;
import com.icop.parcialpromocioncastroj.service.SocioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/socios")
public class SocioController {

    private final SocioService socioService;

    public SocioController(SocioService socioService) {
        this.socioService = socioService;
    }

    //listar socios
    @GetMapping
    public List<ResponseSocioDto> listar() { return socioService.listar(); }

    //get socios por el id
    @GetMapping("/{id}")
    public ResponseSocioDto buscar(@PathVariable Long id) { return socioService.buscarPorId(id); }

    //registrar un socio 
    @PostMapping
    public ResponseEntity<ResponseSocioDto> registrar(@RequestBody RequestSocioDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(socioService.registrar(dto));
    }

    //elimminar un socio 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        socioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}