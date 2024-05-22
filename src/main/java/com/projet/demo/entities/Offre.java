package com.projet.demo.entities;


import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Offre {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private double prix;
    private int dureeEnMois = 1;

    
    @ManyToOne
    @JoinColumn(name = "salle_id")
    @JsonBackReference(value = "salle-offre")

    private Salle salle;

    @ElementCollection(targetClass = Categorie.class)
    @CollectionTable(name = "offre_categorie", joinColumns = @JoinColumn(name = "offre_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "categorie")
    private Set<Categorie> categories = new HashSet<>();

    @OneToMany(mappedBy = "offre")
    @JsonIgnoreProperties("offre")
    private Set<Abonnement> abonnements = new HashSet<>();
    
    
    
    
		public Offre() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Offre(Long id, String nom, String description, double prix, int dureeEnMois, Salle salle,
				Set<Categorie> categories) {
			super();
			this.id = id;
			this.nom = nom;
			this.description = description;
			this.prix = prix;
			this.dureeEnMois = dureeEnMois;
			this.salle = salle;
			this.categories = categories;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
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

		public double getPrix() {
			return prix;
		}

		public void setPrix(double prix) {
			this.prix = prix;
		}

		public int getDureeEnMois() {
			return dureeEnMois;
		}

		public void setDureeEnMois(int dureeEnMois) {
			this.dureeEnMois = dureeEnMois;
		}

		public Salle getSalle() {
			return salle;
		}

		public void setSalle(Salle salle) {
			this.salle = salle;
		}

		public Set<Categorie> getCategories() {
			return categories;
		}

		public void setCategories(Set<Categorie> categories) {
			this.categories = categories;
		}

		@Override
		public String toString() {
			return "Offre [id=" + id + ", nom=" + nom + ", description=" + description + ", prix=" + prix
					+ ", dureeEnMois=" + dureeEnMois + ", salle=" + salle + ", categories=" + categories + "]";
		}
	    
	    
	    
	  
	    

}
