package org.springframework.samples.petclinic.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

import static org.springframework.samples.petclinic.security.Roles.USER;
import static org.springframework.samples.petclinic.security.Roles.VETERENARIAN;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	// Spring security has been updated since the book 'Spring Boot in practice'.
	// I followed the Spring doc's for the latest way to configure security.
	// source https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/index.html
	// & https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html

	private final WebExpressionAuthorizationManager userIsOwnerAuthorizationManager = new WebExpressionAuthorizationManager("#ownerId == authentication.name");

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/vets*").permitAll()
				.requestMatchers("/owners/*/pets/*/visits/new").hasRole(VETERENARIAN.getRole())
				.requestMatchers("/owners/{ownerId}/edit").access(userIsOwnerAuthorizationManager)
				.requestMatchers("/owners/{ownerId}/pets/new").access(userIsOwnerAuthorizationManager)
				.anyRequest().authenticated()
			)
			.httpBasic(Customizer.withDefaults())	//info: https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/basic.html
			.formLogin(Customizer.withDefaults());	//info: https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/form.html

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user1Details = User.withDefaultPasswordEncoder()
			.username("1")
			.password("password")
			.roles(USER.getRole())
			.build();

		UserDetails user2Details = User.withDefaultPasswordEncoder()
			.username("2")
			.password("password")
			.roles(USER.getRole())
			.build();

		UserDetails veterenarianDetails = User.withDefaultPasswordEncoder()
			.username("veterinarian")
			.password("password")
			.roles(USER.getRole(), VETERENARIAN.getRole())
			.build();

		return new InMemoryUserDetailsManager(user1Details, user2Details, veterenarianDetails);
	}
}
