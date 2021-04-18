package com.ifma.freteapi.service.exceptions;

public class ClienteRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 2L;

	public ClienteRuntimeException(String msg) {
		super(msg);
	} 
}
