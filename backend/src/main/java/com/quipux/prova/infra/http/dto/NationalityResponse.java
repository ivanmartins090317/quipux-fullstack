package com.quipux.prova.infra.http.dto;

import com.quipux.prova.domain.model.NationalityResult;

import java.util.UUID;

public record NationalityResponse(
		UUID id,
		String nome,
		String codigoNacionalidade,
		String nacionalidade,
		double probabilidade
) {
	public static NationalityResponse from(NationalityResult result) {
		return new NationalityResponse(
				result.personId(),
				result.nome(),
				result.codigo(),
				result.nomePais(),
				result.probabilidade()
		);
	}
}
