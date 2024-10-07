package org.springframework.samples.petclinic.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PublicRoutesTest {
	// Use WebClient instead of RestTemplateBuilder because RestTemplateBuilder will become deprecated (source https://www.baeldung.com/rest-template)
	@Autowired
	WebTestClient webClient;

	@Test
	void whenPublicRouteIsQueried_thenRouteIsAccessible() {
		WebTestClient.ResponseSpec result = webClient.get().uri("/vets").exchange();

		result.expectStatus().isOk();
	}
}
