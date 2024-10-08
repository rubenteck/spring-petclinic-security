package org.springframework.samples.petclinic.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrivateRoutesRequiresAuthenticationTest {
	@Autowired
	WebTestClient webClient;

	@Test
	void givenNotLoggedIn_whenPrivateRouteIsQueried_thenRouteIsNotAccessible() {
		WebTestClient.ResponseSpec result = webClient.get().uri("/owners").exchange();

		result.expectStatus().isUnauthorized();
	}

	@Test
	void givenLoggedIn_whenPrivateRouteIsQueried_thenRouteIsAccessible() {
		WebTestClient client = webClient.mutate()
			.filter(basicAuthentication("user", "password"))
			.build();

		WebTestClient.ResponseSpec result = client.get().uri("/owners").exchange();

		result.expectStatus().isOk();
	}
}
