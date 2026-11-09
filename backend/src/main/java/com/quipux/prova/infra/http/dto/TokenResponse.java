package com.quipux.prova.infra.http.dto;

public record TokenResponse(String token, String tokenType, long expiresInSeconds) {
}
