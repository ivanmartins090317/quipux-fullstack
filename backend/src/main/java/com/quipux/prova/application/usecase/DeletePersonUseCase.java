package com.quipux.prova.application.usecase;

import com.quipux.prova.application.port.PersonRepository;
import com.quipux.prova.domain.exception.PersonNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeletePersonUseCase {

	private final PersonRepository people;

	public DeletePersonUseCase(PersonRepository people) {
		this.people = people;
	}

	public void execute(UUID id) {
		if (!people.existsById(id)) {
			throw new PersonNotFoundException(id);
		}
		people.deleteById(id);
	}
}
