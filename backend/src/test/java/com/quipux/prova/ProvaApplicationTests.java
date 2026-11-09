package com.quipux.prova;

import com.quipux.prova.application.port.NationalityClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("test")
class ProvaApplicationTests {

	@MockitoBean
	private NationalityClient nationalityClient;

	@Test
	void contextLoads() {
	}
}
