package com.quipux.prova.domain.exception;

public class InvalidCredentialsException extends RuntimeException {

	public InvalidCredentialsException() {
		super("Usuário ou senha inválidos");
	}
}
