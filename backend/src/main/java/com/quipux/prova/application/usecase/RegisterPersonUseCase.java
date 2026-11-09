package com.quipux.prova.application.usecase;

import com.quipux.prova.application.port.PersonRepository;
import com.quipux.prova.domain.exception.DuplicateDocumentException;
import com.quipux.prova.domain.exception.InvalidDocumentoException;
import com.quipux.prova.domain.model.Documento;
import com.quipux.prova.domain.model.Person;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterPersonUseCase {

	private final PersonRepository people;

	public RegisterPersonUseCase(PersonRepository people) {
		this.people = people;
	}

	public Person execute(String documento, String nome, String sobrenome, String email) {
		var digitos = Documento.apenasDigitos(documento);
		if (!digitos.matches("\\d{11}")) {
			throw new InvalidDocumentoException();
		}
		if (people.existsByDocumento(digitos)) {
			throw new DuplicateDocumentException();
		}
		var person = new Person(
				UUID.randomUUID(),
				digitos,
				nome.trim(),
				sobrenome.trim(),
				email.trim().toLowerCase()
		);
		return people.save(person);
	}
}
