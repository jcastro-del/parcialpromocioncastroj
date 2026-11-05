package com.icop.parcialpromocioncastroj.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePrestamoDto {
    private Long id;
    private String tituloLibro;
    private LocalDate fechaPrestamo;
    private boolean devuelto;
    private String nombreSocio;
}
