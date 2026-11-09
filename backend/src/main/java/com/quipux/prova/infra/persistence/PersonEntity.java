package com.quipux.prova.infra.persistence;

import com.quipux.prova.domain.model.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "pessoa")
public class PersonEntity {

	@Id
	private UUID id;

	@Column(nullable = false, unique = true, length = 11)
	private String documento;

	@Column(nullable = false, length = 80)
	private String nome;

	@Column(nullable = false, length = 80)
	private String sobrenome;

	@Column(nullable = false, length = 120)
	private String email;

	protected PersonEntity() {
	}

	public static PersonEntity from(Person person) {
		var entity = new PersonEntity();
		entity.id = person.id();
		entity.documento = person.documento();
		entity.nome = person.nome();
		entity.sobrenome = person.sobrenome();
		entity.email = person.email();
		return entity;
	}

	public Person toDomain() {
		return new Person(id, documento, nome, sobrenome, email);
	}
}
