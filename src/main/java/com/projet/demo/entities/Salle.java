package com.projet.demo.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idsalle")

public class Salle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idsalle;
	private String name;
	private String adresse;
	
	   @OneToMany(mappedBy = "salle")
	    @JsonManagedReference(value = "salle-user")
	    private Set<User> users = new HashSet<>();

	@OneToMany(mappedBy = "salle")
    @JsonManagedReference(value = "salle-member")
    private Set<Member> members = new HashSet<>();

	@OneToMany(mappedBy = "salle")
    @JsonManagedReference(value = "salle-produit")
    private Set<Produit> products = new HashSet<>();
	
    @OneToMany(mappedBy = "salle")
    @JsonIgnoreProperties("salle")
    private Set<Cours> cours = new HashSet<>();

    @OneToMany(mappedBy = "salle")
    @JsonIgnoreProperties("salle")
    private Set<Equipement> equipements = new HashSet<>();
    
    
    @OneToMany(mappedBy = "salle")
    @JsonManagedReference(value = "salle-abonnement")
    private Set<Abonnement> abonnements = new HashSet<>();
    
    @OneToMany(mappedBy = "salle")
    
    @JsonManagedReference
    private Set<Offre> offres = new HashSet<>();


	
	
	
	
	
	
	public Salle() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Salle(long idsalle, String name, String adresse, Set<User> users, Set<Member> members) {
		super();
		this.idsalle = idsalle;
		this.name = name;
		this.adresse = adresse;
		this.users = users;
		this.members = members;
	}


	public long getIdsalle() {
		return idsalle;
	}


	public Set<Equipement> getEquipements() {
		return equipements;
	}


	public void setEquipements(Set<Equipement> equipements) {
		this.equipements = equipements;
	}


	public Set<Abonnement> getAbonnements() {
		return abonnements;
	}


	public void setAbonnements(Set<Abonnement> abonnements) {
		this.abonnements = abonnements;
	}


	public Set<Offre> getOffres() {
		return offres;
	}


	public void setOffres(Set<Offre> offres) {
		this.offres = offres;
	}


	public Set<Produit> getProducts() {
		return products;
	}


	public void setProducts(Set<Produit> products) {
		this.products = products;
	}


	public Set<Cours> getCours() {
		return cours;
	}


	public void setCours(Set<Cours> cours) {
		this.cours = cours;
	}


	public void setIdsalle(long idsalle) {
		this.idsalle = idsalle;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public Set<User> getUsers() {
		return users;
	}


	public void setUsers(Set<User> users) {
		this.users = users;
	}


	public Set<Member> getMembers() {
		return members;
	}


	public void setMembers(Set<Member> members) {
		this.members = members;
	}


	@Override
	public String toString() {
		return "Salle [idsalle=" + idsalle + ", name=" + name + ", adresse=" + adresse + ", users=" + users
				+ ", members=" + members + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
