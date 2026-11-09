package com.quipux.prova.infra.http;

import com.quipux.prova.application.usecase.FindNationalityUseCase;
import com.quipux.prova.infra.http.dto.NationalityResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Tag(name = "Nacionalidade")
public class NationalityController {

	private final FindNationalityUseCase findNationality;

	public NationalityController(FindNationalityUseCase findNationality) {
		this.findNationality = findNationality;
	}

	@GetMapping("/findNacionalityByPerson/{id}")
	@Operation(
			summary = "Estima a nacionalidade pelo primeiro nome, via nationalize.io",
			security = @SecurityRequirement(name = "bearerAuth")
	)
	public NationalityResponse find(@PathVariable UUID id) {
		return NationalityResponse.from(findNationality.execute(id));
	}
}
