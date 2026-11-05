package com.icop.parcialpromocioncastroj.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class RequestSocioDto {
    private String nombre;
    private String apellido;
    private String dni;
}