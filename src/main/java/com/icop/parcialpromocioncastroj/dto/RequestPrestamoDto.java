package com.icop.parcialpromocioncastroj.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class RequestPrestamoDto {
    private Long socioId;
    private String tituloLibro;
    private LocalDate fechaPrestamo;
}