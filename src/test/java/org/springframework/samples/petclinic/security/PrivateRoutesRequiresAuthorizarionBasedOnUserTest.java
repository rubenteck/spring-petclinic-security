package org.springframework.samples.petclinic.security;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

public class PrivateRoutesRequiresAuthorizarionBasedOnUserTest extends IntegrationTest {

	public static final String URI_EDIT = "/owners/1/edit";
	public static final String URI_PET = "/owners/1/pets/new";

	@ParameterizedTest
	@ValueSource(strings = {URI_EDIT, URI_PET})
	void givenNotLoggedIn_whenPrivateRouteIsFetched_thenRouteIsNotAccessible(String URI) {
		ResponseSpec result = webClient.get().uri(URI).exchange();

		result.expectStatus().isUnauthorized();
	}

	@ParameterizedTest
	@ValueSource(strings = {URI_EDIT, URI_PET})
	void givenLoggedInAsDifferentUser_whenPrivateRouteIsFetched_thenRouteIsNotAccessible(String URI) {
		loginAsUser2();

		ResponseSpec result = webClient.get().uri(URI).exchange();

		result.expectStatus().isForbidden();
	}

	@ParameterizedTest
	@ValueSource(strings = {URI_EDIT, URI_PET})
	void givenLoggedInAsVeterinarian_whenPrivateRouteIsFetched_thenRouteIsNotAccessible(String URI) {
		loginAsVeterenarian();

		ResponseSpec result = webClient.get().uri(URI).exchange();

		result.expectStatus().isForbidden();
	}

	@ParameterizedTest
	@ValueSource(strings = {URI_EDIT, URI_PET})
	void givenLoggedInAsCorrectUser_whenPrivateRouteIsFetched_thenRouteIsAccessible(String URI) {
		loginAsUser1();

		ResponseSpec result = webClient.get().uri(URI).exchange();

		result.expectStatus().isOk();
	}
}
