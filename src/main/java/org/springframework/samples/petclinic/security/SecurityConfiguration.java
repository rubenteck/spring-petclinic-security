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

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	// Spring security has been updated since the book 'Spring Boot in practice'.
	// I followed the Spring doc's for the latest way to configure security.
	// source https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/index.html

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/vets*").permitAll()
				.anyRequest().authenticated()
			)
			.httpBasic(Customizer.withDefaults())	//info: https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/basic.html
			.formLogin(Customizer.withDefaults());	//info: https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/form.html

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails userDetails = User.withDefaultPasswordEncoder()
			.username("user")
			.password("password")
			.roles("USER")
			.build();

		return new InMemoryUserDetailsManager(userDetails);
	}
}
