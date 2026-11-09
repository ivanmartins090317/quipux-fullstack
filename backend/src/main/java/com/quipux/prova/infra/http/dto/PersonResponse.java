package com.quipux.prova.infra.http.dto;

import com.quipux.prova.domain.model.Person;

import java.util.UUID;

public record PersonResponse(
		UUID id,
		String documento,
		String nome,
		String sobrenome,
		String email
) {
	public static PersonResponse from(Person person) {
		return new PersonResponse(person.id(), person.documento(), person.nome(), person.sobrenome(), person.email());
	}
}
