package com.plenumsoft.vuzee.security.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="privileges")
public class Privilege {
	
	 	@Id
	    private String id;
	 	
	 	@Column(unique=true, nullable = false)
	    private String name;
	 	
	 	@Column(nullable = false)
	    private boolean isActive; 
	 
	    @ManyToMany(mappedBy = "privileges")
	    private Collection<Role> roles;

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

		public Collection<Role> getRoles() {
			return roles;
		}

		public void setRoles(Collection<Role> roles) {
			this.roles = roles;
		}
}
