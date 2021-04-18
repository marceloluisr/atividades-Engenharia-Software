package com.ifma.freteapi.service.exceptions;

public class CidadeException extends Exception {
    public CidadeException(Exception e) throws CidadeException {
        super(e);
    }
    
}
