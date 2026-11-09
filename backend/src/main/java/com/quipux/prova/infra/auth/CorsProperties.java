package com.quipux.prova.infra.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

@ConfigurationProperties(prefix = "app.cors")
public record CorsProperties(String allowedOrigins) {

	public CorsProperties {
		if (allowedOrigins == null || allowedOrigins.isBlank()) {
			throw new IllegalStateException("app.cors.allowed-origins é obrigatório");
		}
	}

	public List<String> origins() {
		return Arrays.stream(allowedOrigins.split(","))
				.map(String::trim)
				.filter(origin -> !origin.isEmpty())
				.toList();
	}
}
