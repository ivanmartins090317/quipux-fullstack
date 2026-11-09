package com.quipux.prova.domain.model;

import java.util.UUID;

public record Person(
		UUID id,
		String documento,
		String nome,
		String sobrenome,
		String email
) {
}
