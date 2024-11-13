package org.springframework.samples.petclinic.security;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

public class PrivateRoutesRequiresAuthenticationTest extends IntegrationTest {

	public static final String URI = "/owners";

	@Test
	void givenNotLoggedIn_whenPrivateRouteIsFetched_thenRouteIsNotAccessible() {
		WebTestClient.ResponseSpec result = webClient.get().uri(URI).exchange();

		result.expectStatus().isUnauthorized();
	}

	@Test
	void givenLoggedIn_whenPrivateRouteIsFetched_thenRouteIsAccessible() {
		loginAsUser1();

		WebTestClient.ResponseSpec result = webClient.get().uri("/owners").exchange();

		result.expectStatus().isOk();
	}
}
