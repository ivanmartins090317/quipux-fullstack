package com.quipux.prova.domain.exception;

import java.util.UUID;

public class PersonNotFoundException extends RuntimeException {

	private final UUID id;

	public PersonNotFoundException(UUID id) {
		super("Pessoa não encontrada");
		this.id = id;
	}

	public UUID id() {
		return id;
	}
}
