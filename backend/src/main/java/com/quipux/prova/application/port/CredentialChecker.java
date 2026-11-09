package com.quipux.prova.application.port;

public interface CredentialChecker {

	boolean matches(String username, String password);
}
