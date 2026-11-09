package com.quipux.prova.application.usecase;

import com.quipux.prova.application.port.CredentialChecker;
import com.quipux.prova.application.port.NationalityClient;
import com.quipux.prova.application.port.PersonRepository;
import com.quipux.prova.application.port.TokenIssuer;
import com.quipux.prova.domain.exception.DuplicateDocumentException;
import com.quipux.prova.domain.exception.PersonNotFoundException;
import com.quipux.prova.domain.model.CountryGuess;
import com.quipux.prova.domain.model.Documento;
import com.quipux.prova.domain.model.Person;
import com.quipux.prova.infra.nationalize.CountryNameResolver;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationUseCasesTest {

	@Test
	void cadastraNormalizandoMascaraERecusaDocumentoRepetido() {
		var repository = new InMemoryPersonRepository();
		var useCase = new RegisterPersonUseCase(repository);

		var salva = useCase.execute("529.982.247-25", " Ana ", "Silva", "ANA@Email.com");

		assertEquals("52998224725", salva.documento());
		assertEquals("Ana", salva.nome());
		assertEquals("ana@email.com", salva.email());
		assertThrows(DuplicateDocumentException.class,
				() -> useCase.execute("52998224725", "Ana", "Silva", "ana@email.com"));
	}

	@Test
	void excluirInexistenteRetornaNaoEncontrada() {
		var useCase = new DeletePersonUseCase(new InMemoryPersonRepository());
		assertThrows(PersonNotFoundException.class, () -> useCase.execute(UUID.randomUUID()));
	}

	@Test
	void nacionalidadeUsaPrimeiroNome() {
		var repository = new InMemoryPersonRepository();
		var salva = new RegisterPersonUseCase(repository).execute("12345678901", "Ana Maria", "Souza", "ana@email.com");
		NationalityClient client = name -> {
			assertEquals("Ana", name);
			return new CountryGuess("BR", "Brasil", 0.42);
		};
		var result = new FindNationalityUseCase(new GetPersonUseCase(repository), client).execute(salva.id());
		assertEquals("BR", result.codigo());
		assertEquals("Brasil", result.nomePais());
	}

	@Test
	void loginRecusaSenhaErrada() {
		CredentialChecker checker = (username, password) -> "avaliador".equals(username) && "quipux-demo".equals(password);
		TokenIssuer tokens = new TokenIssuer() {
			@Override
			public String issue(String subject) {
				return "token-" + subject;
			}

			@Override
			public long expiresInSeconds() {
				return 60;
			}
		};
		var useCase = new AuthenticateUseCase(checker, tokens);
		assertThrows(com.quipux.prova.domain.exception.InvalidCredentialsException.class,
				() -> useCase.execute("avaliador", "errada"));
		assertEquals("token-avaliador", useCase.execute("avaliador", "quipux-demo").token());
	}

	@Test
	void primeiroNomeEPaisEmPortugues() {
		assertEquals("Maria", Documento.primeiroNome("  Maria Clara "));
		assertEquals("Estados Unidos", CountryNameResolver.nomeEmPortugues("US"));
	}

	private static final class InMemoryPersonRepository implements PersonRepository {
		private final List<Person> people = new ArrayList<>();

		@Override
		public Person save(Person person) {
			people.add(person);
			return person;
		}

		@Override
		public List<Person> findAll() {
			return List.copyOf(people);
		}

		@Override
		public Optional<Person> findById(UUID id) {
			return people.stream().filter(person -> person.id().equals(id)).findFirst();
		}

		@Override
		public boolean existsByDocumento(String documento) {
			return people.stream().anyMatch(person -> person.documento().equals(documento));
		}

		@Override
		public boolean existsById(UUID id) {
			return findById(id).isPresent();
		}

		@Override
		public void deleteById(UUID id) {
			people.removeIf(person -> person.id().equals(id));
		}
	}
}
