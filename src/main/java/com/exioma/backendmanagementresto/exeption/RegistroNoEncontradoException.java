package com.exioma.backendmanagementresto.exeption;

public class RegistroNoEncontradoException extends RuntimeException {
    public RegistroNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
