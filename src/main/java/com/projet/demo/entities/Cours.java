package com.projet.demo.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
public class Cours {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idcours;
	private String titre;
	private String date;
	private String time;
	private String level;
	private String detail;
	
	
    @Enumerated(EnumType.STRING)

	private Categorie categorie;

	
	
    @ManyToOne
    @JoinColumn(name = "salle_id")
    @JsonBackReference(value = "salle-cours")
    private Salle salle;
    
    

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-cours")
    private User user;
    
    

    @OneToMany(mappedBy = "cours")
    @JsonManagedReference(value = "cours-reservation")
    private Set<Reservation> reservations = new HashSet<>();

	


	public Cours() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Cours(long idcours, String titre, Categorie categorie, String date, String time, String level, String detail,
			Salle salle, User user, Set<Reservation> reservations) {
		super();
		this.idcours = idcours;
		this.titre = titre;
		this.categorie = categorie;
		this.date = date;
		this.time = time;
		this.level = level;
		this.detail = detail;
		this.salle = salle;
		this.user = user;
		this.reservations = reservations;
	}




	public long getIdcours() {
		return idcours;
	}




	public void setIdcours(long idcours) {
		this.idcours = idcours;
	}




	public String getTitre() {
		return titre;
	}




	public void setTitre(String titre) {
		this.titre = titre;
	}




	public Categorie getCategorie() {
		return categorie;
	}




	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}




	public String getDate() {
		return date;
	}




	public void setDate(String date) {
		this.date = date;
	}




	public String getTime() {
		return time;
	}




	public void setTime(String time) {
		this.time = time;
	}




	public String getLevel() {
		return level;
	}




	public void setLevel(String level) {
		this.level = level;
	}




	public String getDetail() {
		return detail;
	}




	public void setDetail(String detail) {
		this.detail = detail;
	}




	public Salle getSalle() {
		return salle;
	}




	public void setSalle(Salle salle) {
		this.salle = salle;
	}




	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public Set<Reservation> getReservations() {
		return reservations;
	}




	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}




	@Override
	public String toString() {
		return "Cours [idcours=" + idcours + ", titre=" + titre + ", categorie=" + categorie + ", date=" + date
				+ ", time=" + time + ", level=" + level + ", detail=" + detail + ", salle=" + salle + ", user=" + user
				+ ", reservations=" + reservations + "]";
	}





}




	





	

