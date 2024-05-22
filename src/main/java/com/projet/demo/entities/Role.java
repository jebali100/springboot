package com.projet.demo.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long idrole;
	@Enumerated(EnumType.STRING)
	private  Rolename rolename;
	
	
	
	
	@OneToMany
	private Set<User> users= new HashSet<>();
	
	
	
	
	
	
	
	
	
	
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Role(long idrole, Rolename rolename) {
		super();
		this.idrole = idrole;
		this.rolename = rolename;
	}
	public long getIdrole() {
		return idrole;
	}
	public void setIdrole(long idrole) {
		this.idrole = idrole;
	}
	public Rolename getRolename() {
		return rolename;
	}
	public void setRolename(Rolename rolename) {
		this.rolename = rolename;
	}
	@Override
	public String toString() {
		return "Role [idrole=" + idrole + ", rolename=" + rolename + "]";
	}
	
}
