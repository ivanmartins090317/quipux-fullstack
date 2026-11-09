package com.quipux.prova.application.usecase;

import com.quipux.prova.application.port.CredentialChecker;
import com.quipux.prova.application.port.TokenIssuer;
import com.quipux.prova.domain.exception.InvalidCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateUseCase {

	private final CredentialChecker credentials;
	private final TokenIssuer tokens;

	public AuthenticateUseCase(CredentialChecker credentials, TokenIssuer tokens) {
		this.credentials = credentials;
		this.tokens = tokens;
	}

	public IssuedToken execute(String username, String password) {
		if (!credentials.matches(username, password)) {
			throw new InvalidCredentialsException();
		}
		return new IssuedToken(tokens.issue(username), tokens.expiresInSeconds());
	}

	public record IssuedToken(String token, long expiresInSeconds) {
	}
}
