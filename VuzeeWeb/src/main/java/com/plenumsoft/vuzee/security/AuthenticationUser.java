package com.plenumsoft.vuzee.security;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * Usuario para iniciar sesión
 * @author msoberanis
 *
 */
public class AuthenticationUser {
	@NotEmpty
	@Email
	@NotNull
	private String email;
	
	@NotEmpty
	@NotNull
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
