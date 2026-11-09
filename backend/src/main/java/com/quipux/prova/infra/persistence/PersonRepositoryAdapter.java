package com.quipux.prova.infra.persistence;

import com.quipux.prova.application.port.PersonRepository;
import com.quipux.prova.domain.exception.DuplicateDocumentException;
import com.quipux.prova.domain.model.Person;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class PersonRepositoryAdapter implements PersonRepository {

	private final PersonJpaRepository jpa;

	public PersonRepositoryAdapter(PersonJpaRepository jpa) {
		this.jpa = jpa;
	}

	@Override
	public Person save(Person person) {
		try {
			return jpa.saveAndFlush(PersonEntity.from(person)).toDomain();
		} catch (DataIntegrityViolationException ex) {
			throw new DuplicateDocumentException();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Person> findAll() {
		return jpa.findAll(Sort.by("nome", "sobrenome", "documento")).stream()
				.map(PersonEntity::toDomain)
				.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Person> findById(UUID id) {
		return jpa.findById(id).map(PersonEntity::toDomain);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsByDocumento(String documento) {
		return jpa.existsByDocumento(documento);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsById(UUID id) {
		return jpa.existsById(id);
	}

	@Override
	public void deleteById(UUID id) {
		jpa.deleteById(id);
	}
}
