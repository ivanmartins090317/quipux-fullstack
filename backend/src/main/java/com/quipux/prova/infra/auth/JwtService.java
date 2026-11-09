package com.quipux.prova.infra.auth;

import com.quipux.prova.application.port.TokenIssuer;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtService implements TokenIssuer {

	private final SecurityProperties properties;
	private final SecretKey key;

	public JwtService(SecurityProperties properties) {
		this.properties = properties;
		this.key = Keys.hmacShaKeyFor(properties.jwtSecret().getBytes(StandardCharsets.UTF_8));
	}

	@Override
	public String issue(String subject) {
		var now = Instant.now();
		var expiration = now.plusSeconds(expiresInSeconds());
		return Jwts.builder()
				.subject(subject)
				.issuedAt(Date.from(now))
				.expiration(Date.from(expiration))
				.signWith(key)
				.compact();
	}

	@Override
	public long expiresInSeconds() {
		return properties.jwtExpirationMinutes() * 60;
	}

	public Optional<String> readSubject(String token) {
		try {
			var subject = Jwts.parser()
					.verifyWith(key)
					.build()
					.parseSignedClaims(token)
					.getPayload()
					.getSubject();
			return Optional.ofNullable(subject);
		} catch (JwtException | IllegalArgumentException ex) {
			return Optional.empty();
		}
	}
}
