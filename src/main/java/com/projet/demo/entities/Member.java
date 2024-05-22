package com.projet.demo.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;



	
@Entity	
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Member {
	
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	    private String username;
	    private String firstname;
	    private String lastname;
	    private String email;
	    private String password;
	    private int etat = 0;

	    @OneToOne
	    @JoinColumn(name = "cart_idcart")
	    private Cart cart;

	    @ManyToOne
	    @JoinColumn(name = "idsalle")
	    @JsonBackReference(value = "salle-member")
	    private Salle salle;

	    @OneToMany(mappedBy = "member")
	    private Set<Commande> commandes = new HashSet<>();

	    @OneToMany(mappedBy = "member")
	    private Set<Reservation> reservations = new HashSet<>();

	    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
	    @JsonManagedReference(value = "member-abonnement")
	    private Abonnement abonnement;
	
	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}








	














	public Member(long id, String username, String firstname, String lastname, String email, String password, int etat,
			Cart cart, Salle salle, Set<Commande> commandes, Set<Reservation> reservations, Abonnement abonnement) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.etat = etat;
		this.cart = cart;
		this.salle = salle;
		this.commandes = commandes;
		this.reservations = reservations;
		this.abonnement = abonnement;
	}























	public long getId() {
		return id;
	}








	public void setId(long id) {
		this.id = id;
	}








	public String getUsername() {
		return username;
	}








	public void setUsername(String username) {
		this.username = username;
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








	public String getPassword() {
		return password;
	}








	public void setPassword(String password) {
		this.password = password;
	}








	public Salle getSalle() {
		return salle;
	}























	public void setSalle(Salle salle) {
		this.salle = salle;
	}























	public int getEtat() {
		return etat;
	}








	public void setEtat(int etat) {
		this.etat = etat;
	}








	public Cart getCart() {
		return cart;
	}








	public void setCart(Cart cart) {
		this.cart = cart;
	}








	public Set<Commande> getCommandes() {
		return commandes;
	}








	public void setCommandes(Set<Commande> commandes) {
		this.commandes = commandes;
	}








	public Set<Reservation> getReservations() {
		return reservations;
	}








	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}








	public Abonnement getAbonnement() {
		return abonnement;
	}
















	public void setAbonnement(Abonnement abonnement) {
		this.abonnement = abonnement;
	}
















	@Override
	public String toString() {
		return "Member [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", password=" + password + ", etat=" + etat + ", cart=" + cart + ", commandes="
				+ commandes + ", reservations=" + reservations + "]";
	}
	
	
	
	
	
}
