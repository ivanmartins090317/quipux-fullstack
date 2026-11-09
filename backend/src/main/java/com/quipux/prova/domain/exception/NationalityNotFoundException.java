package com.quipux.prova.domain.exception;

public class NationalityNotFoundException extends RuntimeException {

	public NationalityNotFoundException() {
		super("Não foi possível estimar a nacionalidade para este nome");
	}
}
