package com.plenumsoft.vuzee.security.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role {
	 	@Id
	    private String id;
	 	
	 	@Column(unique=true, nullable = false)
	    private String name;
	 	
	 	@Column(nullable = false)
	    private boolean isActive; 
	    
	    @ManyToMany(mappedBy = "roles")
	    private Collection<User> users;
	    
	    @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(
	        name = "roles_privileges", 
	        joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), 
	        inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
	    private Collection<Privilege> privileges;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean isActive() {
			return isActive;
		}

		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}

		public Collection<User> getUsers() {
			return users;
		}

		public void setUsers(Collection<User> users) {
			this.users = users;
		}

		public Collection<Privilege> getPrivileges() {
			return privileges;
		}

		public void setPrivileges(Collection<Privilege> privileges) {
			this.privileges = privileges;
		}

		
}
