package com.quipux.prova.domain.model;

import java.util.UUID;

public record NationalityResult(
		UUID personId,
		String nome,
		String codigo,
		String nomePais,
		double probabilidade
) {
}
