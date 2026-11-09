package com.quipux.prova.infra.nationalize;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record NationalizePayload(
		long count,
		String name,
		List<Country> country
) {
	public record Country(
			@JsonProperty("country_id") String countryId,
			double probability
	) {
	}
}
