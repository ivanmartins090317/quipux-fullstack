package com.quipux.prova.infra.http;

import com.quipux.prova.application.port.NationalityClient;
import com.quipux.prova.domain.exception.ExternalServiceException;
import com.quipux.prova.domain.model.CountryGuess;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PersonApiIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private NationalityClient nationalityClient;

	@Test
	void rejeitaEmailInvalido() throws Exception {
		mockMvc.perform(post("/registrarName")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{"documento":"12345678901","nome":"Ana","sobrenome":"Silva","email":"nao-e-email"}
								"""))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.fields.email").value("E-mail inválido"));
	}

	@Test
	void rejeitaUuidInvalido() throws Exception {
		mockMvc.perform(get("/list/nao-e-uuid"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value("invalid_parameter"));
	}

	@Test
	void fluxoCadastroConsultaNacionalidadeEExclusao() throws Exception {
		when(nationalityClient.lookup("Ana")).thenReturn(new CountryGuess("US", "Estados Unidos", 0.35));

		var created = mockMvc.perform(post("/registrarName")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{"documento":"123.456.789-01","nome":"Ana","sobrenome":"Silva","email":"ana@email.com"}
								"""))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.documento").value("12345678901"))
				.andReturn();

		var id = com.jayway.jsonpath.JsonPath.read(created.getResponse().getContentAsString(), "$.id").toString();

		mockMvc.perform(post("/registrarName")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{"documento":"12345678901","nome":"Ana","sobrenome":"Silva","email":"ana@email.com"}
								"""))
				.andExpect(status().isConflict());

		mockMvc.perform(get("/list"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[*].documento").value(hasItem("12345678901")));

		mockMvc.perform(get("/list/" + id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.sobrenome").value("Silva"));

		mockMvc.perform(delete("/list/" + id)).andExpect(status().isUnauthorized());
		mockMvc.perform(get("/findNacionalityByPerson/" + id)).andExpect(status().isUnauthorized());

		mockMvc.perform(post("/auth/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{"username":"avaliador","password":"errada"}
								"""))
				.andExpect(status().isUnauthorized());

		var login = mockMvc.perform(post("/auth/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{"username":"avaliador","password":"quipux-demo"}
								"""))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.tokenType").value("Bearer"))
				.andReturn();
		var token = com.jayway.jsonpath.JsonPath.read(login.getResponse().getContentAsString(), "$.token").toString();

		mockMvc.perform(get("/findNacionalityByPerson/" + id).header("Authorization", "Bearer " + token))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.codigoNacionalidade").value("US"))
				.andExpect(jsonPath("$.nacionalidade").value("Estados Unidos"));

		mockMvc.perform(delete("/list/" + id).header("Authorization", "Bearer " + token))
				.andExpect(status().isNoContent());

		mockMvc.perform(get("/list/" + id)).andExpect(status().isNotFound());
	}

	@Test
	void nationalizeForaViraBadGateway() throws Exception {
		when(nationalityClient.lookup(anyString())).thenThrow(new ExternalServiceException("nationalize.io indisponível"));
		var created = mockMvc.perform(post("/registrarName")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{"documento":"10987654321","nome":"Bruno","sobrenome":"Costa","email":"bruno@email.com"}
								"""))
				.andExpect(status().isCreated())
				.andReturn();
		var id = com.jayway.jsonpath.JsonPath.read(created.getResponse().getContentAsString(), "$.id").toString();
		var login = mockMvc.perform(post("/auth/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{"username":"avaliador","password":"quipux-demo"}
								"""))
				.andReturn();
		var token = com.jayway.jsonpath.JsonPath.read(login.getResponse().getContentAsString(), "$.token").toString();

		mockMvc.perform(get("/findNacionalityByPerson/" + id).header("Authorization", "Bearer " + token))
				.andExpect(status().isBadGateway());
	}
}
