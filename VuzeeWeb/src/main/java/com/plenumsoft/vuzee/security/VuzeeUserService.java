package com.plenumsoft.vuzee.security;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface VuzeeUserService extends UserDetailsService{
	
	public AuthenticatedUser registerNewAccount(VuzeeUser userDto) throws EmailExistsException;
}
