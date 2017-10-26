package com.plenumsoft.vuzee.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.plenumsoft.vuzee.security.entities.User;
/**
 * Usuario con sesión iniciada
 * @author msoberanis
 *
 */
public class AuthenticatedUser implements UserDetails {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private long id;
	private List<GrantedAuthority> grantedAuthorities;
	private static final long serialVersionUID = 5639683223516504866L;
	
	public AuthenticatedUser(User user, List<GrantedAuthority> grantedAuthorities){
		firstName = user.getFirstName();
		lastName = user.getLastName();
		email = user.getEmail();
		password = user.getPassword();
		id = user.getId();
		this.grantedAuthorities = grantedAuthorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

	public String getName() {
		return getFirstName() + " " + getLastName();
	}

	public String getEmail() {
		return email;
	}
	
	public  boolean hasPrivilege(String targetType, String permission) {
	    for (GrantedAuthority grantedAuth : getAuthorities()) {
	        if (grantedAuth.getAuthority().startsWith(permission)) {
	            if (grantedAuth.getAuthority().contains(targetType)) {
	                return true;
	            }
	        }
	    }
	    return false;
	}
	
}
