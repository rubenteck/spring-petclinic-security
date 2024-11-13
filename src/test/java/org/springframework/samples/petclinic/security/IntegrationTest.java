package org.springframework.samples.petclinic.security;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
	// Use WebClient instead of RestTemplateBuilder because RestTemplateBuilder will become deprecated (source https://www.baeldung.com/rest-template)
	WebTestClient webClient;
	@LocalServerPort
	int port;

	@BeforeEach
	void setup() {
		webClient = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build();
	}

	private void login(String name, String password) {
		webClient = webClient.mutate()
			.filter(basicAuthentication(name, password))
			.build();
	}

	void loginAsUser1() {
		login("1", "password");
	}

	void loginAsUser2() {
		login("2", "password");
	}

	void loginAsVeterenarian() {
		login("veterinarian", "password");
	}
}
