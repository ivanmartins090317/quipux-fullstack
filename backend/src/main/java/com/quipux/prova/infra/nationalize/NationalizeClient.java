package com.quipux.prova.infra.nationalize;

import com.quipux.prova.application.port.NationalityClient;
import com.quipux.prova.domain.exception.ExternalServiceException;
import com.quipux.prova.domain.exception.NationalityNotFoundException;
import com.quipux.prova.domain.model.CountryGuess;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.time.Duration;
import java.util.Comparator;

@Component
public class NationalizeClient implements NationalityClient {

	private final RestClient restClient;

	public NationalizeClient() {
		var requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(Duration.ofSeconds(3));
		requestFactory.setReadTimeout(Duration.ofSeconds(5));
		this.restClient = RestClient.builder()
				.baseUrl("https://api.nationalize.io")
				.requestFactory(requestFactory)
				.build();
	}

	@Override
	public CountryGuess lookup(String firstName) {
		NationalizePayload payload;
		try {
			payload = restClient.get()
					.uri(uriBuilder -> uriBuilder.path("/").queryParam("name", firstName).build())
					.retrieve()
					.body(NationalizePayload.class);
		} catch (RestClientException ex) {
			throw new ExternalServiceException("nationalize.io indisponível");
		}
		if (payload == null || payload.country() == null || payload.country().isEmpty()) {
			throw new NationalityNotFoundException();
		}
		var best = payload.country().stream()
				.max(Comparator.comparingDouble(NationalizePayload.Country::probability))
				.orElseThrow(NationalityNotFoundException::new);
		return new CountryGuess(
				best.countryId(),
				CountryNameResolver.nomeEmPortugues(best.countryId()),
				best.probability()
		);
	}
}
