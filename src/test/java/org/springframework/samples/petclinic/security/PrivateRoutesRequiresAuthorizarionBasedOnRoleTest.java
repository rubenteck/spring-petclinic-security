package org.springframework.samples.petclinic.security;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

public class PrivateRoutesRequiresAuthorizarionBasedOnRoleTest extends IntegrationTest {

	public static final String URI = "/owners/1/pets/1/visits/new";

	@Test
	void givenNotLoggedIn_whenPrivateRouteIsFetched_thenRouteIsNotAccessible() {
		ResponseSpec result = webClient.get().uri(URI).exchange();

		result.expectStatus().isUnauthorized();
	}

	@Test
	void givenLoggedInAsUser_whenPrivateRouteIsFetched_thenRouteIsNotAccessible() {
		loginAsUser1();

		ResponseSpec result = webClient.get().uri(URI).exchange();

		result.expectStatus().isForbidden();
	}

	@Test
	void givenLoggedInAsVeterinarian_whenPrivateRouteIsFetched_thenRouteIsAccessible() {
		loginAsVeterenarian();

		ResponseSpec result = webClient.get().uri(URI).exchange();

		result.expectStatus().isOk();
	}
}
