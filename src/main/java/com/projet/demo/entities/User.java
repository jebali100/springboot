package com.projet.demo.entities;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;



@Entity
@Data
public class User  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstname;
	private String lastname;
	private String email;
	private String username;
	private String password;
	private String image;
	
	
	

	@ManyToOne
    @JoinColumn(name = "idsalle")
    @JsonBackReference(value = "salle-user")


	private Salle salle;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();
	
	
	
	
	
	@OneToMany (mappedBy = "user" )
    @JsonManagedReference(value = "user-cours")

	private Set<Cours> cours= new HashSet<>();





	public User() {
		super();
		// TODO Auto-generated constructor stub
	}










	public User(long id, String firstname, String lastname, String email, String username, String password,
			String image, Salle salle, List<Role> roles, Set<Cours> cours) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.image = image;
		this.salle = salle;
		this.roles = roles;
		this.cours = cours;
	}










	public long getId() {
		return id;
	}





	public void setId(long id) {
		this.id = id;
	}





	public String getImage() {
		return image;
	}










	public void setImage(String image) {
		this.image = image;
	}










	public Salle getSalle() {
		return salle;
	}










	public void setSalle(Salle salle) {
		this.salle = salle;
	}










	public String getFirstname() {
		return firstname;
	}





	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}





	public String getLastname() {
		return lastname;
	}





	public void setLastname(String lastname) {
		this.lastname = lastname;
	}





	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public String getUsername() {
		return username;
	}





	public void setUsername(String username) {
		this.username = username;
	}





	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}





	public List<Role> getRoles() {
		return roles;
	}





	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}





	public Set<Cours> getCours() {
		return cours;
	}





	public void setCours(Set<Cours> cours) {
		this.cours = cours;
	}





	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", username=" + username + ", password=" + password + ", roles=" + roles + ", cours=" + cours + "]";
	}
	
	
	
	
	
	
	
	
}
