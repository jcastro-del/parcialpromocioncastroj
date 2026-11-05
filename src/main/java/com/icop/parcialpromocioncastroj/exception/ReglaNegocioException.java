package com.icop.parcialpromocioncastroj.exception;

//excepciones par las reglas de negocio 
public class ReglaNegocioException extends RuntimeException {
    public ReglaNegocioException(String mensaje) {
        super(mensaje);
    }
}