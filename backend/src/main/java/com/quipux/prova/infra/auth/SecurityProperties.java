package com.quipux.prova.infra.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.charset.StandardCharsets;

@ConfigurationProperties(prefix = "app.security")
public record SecurityProperties(
		String username,
		String password,
		String jwtSecret,
		long jwtExpirationMinutes
) {
	public SecurityProperties {
		if (username == null || username.isBlank()) {
			throw new IllegalStateException("app.security.username é obrigatório");
		}
		if (password == null || password.isBlank()) {
			throw new IllegalStateException("app.security.password é obrigatório");
		}
		if (jwtSecret == null || jwtSecret.getBytes(StandardCharsets.UTF_8).length < 32) {
			throw new IllegalStateException("app.security.jwt-secret precisa ter pelo menos 32 bytes");
		}
		if (jwtExpirationMinutes <= 0) {
			throw new IllegalStateException("app.security.jwt-expiration-minutes inválido");
		}
	}
}
