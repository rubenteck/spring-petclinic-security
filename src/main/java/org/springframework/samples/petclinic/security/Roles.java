package org.springframework.samples.petclinic.security;

public enum Roles {
	USER("USER"),
	VETERENARIAN("VETERENARIAN");

	private final String role;

	Roles(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
}
