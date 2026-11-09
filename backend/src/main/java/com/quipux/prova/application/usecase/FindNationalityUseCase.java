package com.quipux.prova.application.usecase;

import com.quipux.prova.application.port.NationalityClient;
import com.quipux.prova.domain.model.Documento;
import com.quipux.prova.domain.model.NationalityResult;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindNationalityUseCase {

	private final GetPersonUseCase getPerson;
	private final NationalityClient nationalityClient;

	public FindNationalityUseCase(GetPersonUseCase getPerson, NationalityClient nationalityClient) {
		this.getPerson = getPerson;
		this.nationalityClient = nationalityClient;
	}

	public NationalityResult execute(UUID id) {
		var person = getPerson.execute(id);
		var guess = nationalityClient.lookup(Documento.primeiroNome(person.nome()));
		return new NationalityResult(person.id(), person.nome(), guess.code(), guess.name(), guess.probability());
	}
}
