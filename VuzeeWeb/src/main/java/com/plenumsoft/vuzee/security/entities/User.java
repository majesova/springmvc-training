package com.plenumsoft.vuzee.security.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	
    private String lastName;

    @Column(unique=true, nullable = false)
    private String userName;
    
    @Column(unique=true, nullable = false)
    private String email;
    
    private boolean isEmailConfirmed;
    
    private boolean isLocked;
    
    private String password;
	
    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( 
	        name = "user_roles", 
	        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
	        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")) 
	private Collection<Role> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEmailConfirmed() {
		return isEmailConfirmed;
	}

	public void setEmailConfirmed(boolean isEmailConfirmed) {
		this.isEmailConfirmed = isEmailConfirmed;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	
	
}
