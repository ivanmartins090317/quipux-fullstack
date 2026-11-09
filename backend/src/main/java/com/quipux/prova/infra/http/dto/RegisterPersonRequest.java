package com.quipux.prova.infra.http.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterPersonRequest(
		@NotBlank(message = "Documento é obrigatório")
		@Pattern(
				regexp = "\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}",
				message = "Documento deve ter 11 dígitos (CPF)"
		)
		String documento,
		@NotBlank(message = "Nome é obrigatório")
		@Size(max = 80, message = "Nome deve ter no máximo 80 caracteres")
		String nome,
		@NotBlank(message = "Sobrenome é obrigatório")
		@Size(max = 80, message = "Sobrenome deve ter no máximo 80 caracteres")
		String sobrenome,
		@NotBlank(message = "E-mail é obrigatório")
		@Email(message = "E-mail inválido")
		@Size(max = 120, message = "E-mail deve ter no máximo 120 caracteres")
		String email
) {
}
