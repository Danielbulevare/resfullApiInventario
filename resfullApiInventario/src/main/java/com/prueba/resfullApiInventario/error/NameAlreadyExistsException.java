package com.prueba.resfullApiInventario.error;

public class NameAlreadyExistsException extends Exception{
    public NameAlreadyExistsException(String message) {
        super(message);
    }
}
