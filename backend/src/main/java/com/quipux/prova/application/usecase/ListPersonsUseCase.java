package com.quipux.prova.application.usecase;

import com.quipux.prova.application.port.PersonRepository;
import com.quipux.prova.domain.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListPersonsUseCase {

	private final PersonRepository people;

	public ListPersonsUseCase(PersonRepository people) {
		this.people = people;
	}

	public List<Person> execute() {
		return people.findAll();
	}
}
