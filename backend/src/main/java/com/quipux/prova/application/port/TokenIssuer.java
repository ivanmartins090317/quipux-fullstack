package com.quipux.prova.application.port;

public interface TokenIssuer {

	String issue(String subject);

	long expiresInSeconds();
}
