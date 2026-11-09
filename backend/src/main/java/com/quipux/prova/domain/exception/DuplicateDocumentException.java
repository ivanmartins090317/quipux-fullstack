package com.quipux.prova.domain.exception;

public class DuplicateDocumentException extends RuntimeException {

	public DuplicateDocumentException() {
		super("Documento já cadastrado");
	}
}
