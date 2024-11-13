package org.springframework.samples.petclinic.security;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

public class PublicRoutesTest extends IntegrationTest {

	public static final String URI = "/vets";

	@Test
	void whenPublicRouteIsFetched_thenRouteIsAccessible() {
		WebTestClient.ResponseSpec result = webClient.get().uri(URI).exchange();

		result.expectStatus().isOk();
	}
}
