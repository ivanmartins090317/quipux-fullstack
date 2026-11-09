package com.quipux.prova.infra.auth;

import com.quipux.prova.application.port.CredentialChecker;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
public class ConfigCredentialChecker implements CredentialChecker {

	private final SecurityProperties properties;

	public ConfigCredentialChecker(SecurityProperties properties) {
		this.properties = properties;
	}

	@Override
	public boolean matches(String username, String password) {
		var userOk = secureEquals(properties.username(), username == null ? "" : username);
		var passOk = secureEquals(properties.password(), password == null ? "" : password);
		return userOk && passOk;
	}

	private boolean secureEquals(String left, String right) {
		return MessageDigest.isEqual(digest(left), digest(right));
	}

	private byte[] digest(String value) {
		try {
			return MessageDigest.getInstance("SHA-256").digest(value.getBytes(StandardCharsets.UTF_8));
		} catch (Exception ex) {
			throw new IllegalStateException("SHA-256 indisponível", ex);
		}
	}
}
