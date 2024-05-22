package com.projet.demo.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;


@Entity
public class Produit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nom;
	private String description;
	private float prix;
	private String image;
	
	
	@ManyToOne
    @JoinColumn(name = "idsalle")
    @JsonBackReference(value = "salle-produit")

    private Salle salle;
	
	 @ManyToMany
	    @JoinTable(name = "cart_product",
	               joinColumns = @JoinColumn(name = "product_id"),
	               inverseJoinColumns = @JoinColumn(name = "cart_id"))
	    private Set<Cart> carts = new HashSet<>();
	 
	 
	 
	 @ManyToMany
	 @JoinTable(name = "commande_product",
     joinColumns = @JoinColumn(name = "product_id"),
     inverseJoinColumns = @JoinColumn(name = "commande_id"))
	 private Set<Commande> commandes = new HashSet<>();
	 
	 
	  
	
	
	
	
	
	
	
	
	
	public String getImage() {
		return image;
	}






	public Salle getSalle() {
		return salle;
	}






	public void setSalle(Salle salle) {
		this.salle = salle;
	}






	public void setImage(String image) {
		this.image = image;
	}






	public Produit(long id, String nom, String description, float prix, String image, Set<Cart> carts,
			Set<Commande> commandes) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.image = image;
		this.carts = carts;
		this.commandes = commandes;
	}






	public Produit() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	public Set<Cart> getCarts() {
		return carts;
	}




	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}




	public Set<Commande> getCommandes() {
		return commandes;
	}




	public void setCommandes(Set<Commande> commandes) {
		this.commandes = commandes;
	}




	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", description=" + description + ", prix=" + prix + ", image="
				+ image + ", carts=" + carts + ", commandes=" + commandes + "]";
	}
	
	

}
