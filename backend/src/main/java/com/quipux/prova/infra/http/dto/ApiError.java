package com.quipux.prova.infra.http.dto;

import java.util.Map;

public record ApiError(int status, String error, String message, Map<String, String> fields) {
}
