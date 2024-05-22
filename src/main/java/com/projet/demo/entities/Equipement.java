package com.projet.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Equipement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idequipement;
	private String name;
	private String details;
	private String image;
	
	@ManyToOne
    @JoinColumn(name = "salle_id")
    @JsonBackReference(value = "salle-equipement")
    private Salle salle;

	public Equipement() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	

	public String getImage() {
		return image;
	}






	public void setImage(String image) {
		this.image = image;
	}






	public Equipement(long idequipement, String name, String details, String image, Salle salle) {
		super();
		this.idequipement = idequipement;
		this.name = name;
		this.details = details;
		this.image = image;
		this.salle = salle;
	}






	public long getIdequipement() {
		return idequipement;
	}

	public void setIdequipement(long idequipement) {
		this.idequipement = idequipement;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	@Override
	public String toString() {
		return "Equipement [idequipement=" + idequipement + ", name=" + name + ", details=" + details + ", salle="
				+ salle + "]";
	}
	
	

}
