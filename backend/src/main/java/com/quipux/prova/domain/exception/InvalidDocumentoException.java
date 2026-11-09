package com.quipux.prova.domain.exception;

public class InvalidDocumentoException extends RuntimeException {

	public InvalidDocumentoException() {
		super("Documento deve ter 11 dígitos");
	}
}
