package com.quipux.prova.infra.http;

import com.quipux.prova.application.usecase.AuthenticateUseCase;
import com.quipux.prova.infra.http.dto.LoginRequest;
import com.quipux.prova.infra.http.dto.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Auth")
public class AuthController {

	private final AuthenticateUseCase authenticate;

	public AuthController(AuthenticateUseCase authenticate) {
		this.authenticate = authenticate;
	}

	@PostMapping("/auth/login")
	@Operation(summary = "Emite um JWT para as rotas protegidas")
	public TokenResponse login(@Valid @RequestBody LoginRequest request) {
		var issued = authenticate.execute(request.username(), request.password());
		return new TokenResponse(issued.token(), "Bearer", issued.expiresInSeconds());
	}
}
