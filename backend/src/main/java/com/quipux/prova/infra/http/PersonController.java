package com.quipux.prova.infra.http;

import com.quipux.prova.application.usecase.DeletePersonUseCase;
import com.quipux.prova.application.usecase.GetPersonUseCase;
import com.quipux.prova.application.usecase.ListPersonsUseCase;
import com.quipux.prova.application.usecase.RegisterPersonUseCase;
import com.quipux.prova.infra.http.dto.PersonResponse;
import com.quipux.prova.infra.http.dto.RegisterPersonRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Pessoas")
public class PersonController {

	private final RegisterPersonUseCase registerPerson;
	private final ListPersonsUseCase listPersons;
	private final GetPersonUseCase getPerson;
	private final DeletePersonUseCase deletePerson;

	public PersonController(
			RegisterPersonUseCase registerPerson,
			ListPersonsUseCase listPersons,
			GetPersonUseCase getPerson,
			DeletePersonUseCase deletePerson
	) {
		this.registerPerson = registerPerson;
		this.listPersons = listPersons;
		this.getPerson = getPerson;
		this.deletePerson = deletePerson;
	}

	@PostMapping("/registrarName")
	@Operation(summary = "Cadastra uma pessoa")
	public ResponseEntity<PersonResponse> register(@Valid @RequestBody RegisterPersonRequest request) {
		var saved = registerPerson.execute(request.documento(), request.nome(), request.sobrenome(), request.email());
		return ResponseEntity.created(URI.create("/list/" + saved.id())).body(PersonResponse.from(saved));
	}

	@GetMapping("/list")
	@Operation(summary = "Lista as pessoas cadastradas")
	public List<PersonResponse> list() {
		return listPersons.execute().stream().map(PersonResponse::from).toList();
	}

	@GetMapping("/list/{id}")
	@Operation(summary = "Busca uma pessoa pelo UUID")
	public PersonResponse get(@PathVariable UUID id) {
		return PersonResponse.from(getPerson.execute(id));
	}

	@DeleteMapping("/list/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(summary = "Exclui uma pessoa pelo UUID", security = @SecurityRequirement(name = "bearerAuth"))
	public void delete(@PathVariable UUID id) {
		deletePerson.execute(id);
	}
}
