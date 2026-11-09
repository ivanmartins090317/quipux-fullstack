package com.quipux.prova.application.port;

import com.quipux.prova.domain.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepository {

	Person save(Person person);

	List<Person> findAll();

	Optional<Person> findById(UUID id);

	boolean existsByDocumento(String documento);

	boolean existsById(UUID id);

	void deleteById(UUID id);
}
