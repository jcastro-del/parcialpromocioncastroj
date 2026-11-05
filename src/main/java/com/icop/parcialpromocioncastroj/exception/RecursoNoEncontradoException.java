package com.icop.parcialpromocioncastroj.exception;

//excepcion para cuando el socio soliitado no exista 
public class RecursoNoEncontradoException extends RuntimeException {
    public RecursoNoEncontradoException(String mensaje) { super(mensaje); }
}