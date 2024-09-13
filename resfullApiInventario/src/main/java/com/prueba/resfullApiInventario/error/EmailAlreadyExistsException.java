package com.prueba.resfullApiInventario.error;

public class EmailAlreadyExistsException extends Exception{
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
