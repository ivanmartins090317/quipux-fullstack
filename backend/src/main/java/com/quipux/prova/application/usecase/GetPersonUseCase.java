package com.quipux.prova.application.usecase;

import com.quipux.prova.application.port.PersonRepository;
import com.quipux.prova.domain.exception.PersonNotFoundException;
import com.quipux.prova.domain.model.Person;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetPersonUseCase {

	private final PersonRepository people;

	public GetPersonUseCase(PersonRepository people) {
		this.people = people;
	}

	public Person execute(UUID id) {
		return people.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
	}
}
