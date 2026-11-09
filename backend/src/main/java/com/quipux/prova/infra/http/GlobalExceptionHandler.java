package com.quipux.prova.infra.http;

import com.quipux.prova.domain.exception.DuplicateDocumentException;
import com.quipux.prova.domain.exception.ExternalServiceException;
import com.quipux.prova.domain.exception.InvalidCredentialsException;
import com.quipux.prova.domain.exception.InvalidDocumentoException;
import com.quipux.prova.domain.exception.NationalityNotFoundException;
import com.quipux.prova.domain.exception.PersonNotFoundException;
import com.quipux.prova.infra.http.dto.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.LinkedHashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {
		var fields = new LinkedHashMap<String, String>();
		for (var error : ex.getBindingResult().getFieldErrors()) {
			fields.putIfAbsent(error.getField(), error.getDefaultMessage());
		}
		return ResponseEntity.badRequest().body(new ApiError(400, "validation_error", "Dados inválidos", fields));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	ResponseEntity<ApiError> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		return ResponseEntity.badRequest().body(new ApiError(
				400,
				"invalid_parameter",
				"O parâmetro informado é inválido. Use um UUID.",
				null
		));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	ResponseEntity<ApiError> handleUnreadable(HttpMessageNotReadableException ex) {
		return ResponseEntity.badRequest().body(new ApiError(400, "invalid_body", "JSON inválido", null));
	}

	@ExceptionHandler(InvalidDocumentoException.class)
	ResponseEntity<ApiError> handleDocumento(InvalidDocumentoException ex) {
		return ResponseEntity.badRequest().body(new ApiError(400, "invalid_document", ex.getMessage(), null));
	}

	@ExceptionHandler(PersonNotFoundException.class)
	ResponseEntity<ApiError> handleNotFound(PersonNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ApiError(404, "not_found", ex.getMessage(), null));
	}

	@ExceptionHandler(DuplicateDocumentException.class)
	ResponseEntity<ApiError> handleDuplicate(DuplicateDocumentException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ApiError(409, "duplicate_document", ex.getMessage(), null));
	}

	@ExceptionHandler(InvalidCredentialsException.class)
	ResponseEntity<ApiError> handleCredentials(InvalidCredentialsException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(new ApiError(401, "invalid_credentials", ex.getMessage(), null));
	}

	@ExceptionHandler(NationalityNotFoundException.class)
	ResponseEntity<ApiError> handleNationality(NationalityNotFoundException ex) {
		return ResponseEntity.unprocessableEntity()
				.body(new ApiError(422, "nationality_not_found", ex.getMessage(), null));
	}

	@ExceptionHandler(ExternalServiceException.class)
	ResponseEntity<ApiError> handleExternal(ExternalServiceException ex) {
		log.warn("Falha na API externa: {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
				.body(new ApiError(502, "external_service", ex.getMessage(), null));
	}

	@ExceptionHandler(Exception.class)
	ResponseEntity<ApiError> handleUnexpected(Exception ex) {
		log.error("Erro inesperado", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiError(500, "internal_error", "Erro interno", null));
	}
}
