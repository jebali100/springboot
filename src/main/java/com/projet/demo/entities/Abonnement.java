package com.projet.demo.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Abonnement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	 private LocalDate dateDebut;
	    private LocalDate dateFin;

	    @OneToOne
	    @JoinColumn(name = "member_id")
	    @JsonBackReference(value = "member-abonnement")
	    private Member member;

	    @ManyToOne
	    @JoinColumn(name = "offre_id")
	    @JsonManagedReference(value = "abonnement-offre")
	    private Offre offre;

	    @ManyToOne
	    @JoinColumn(name = "salle_id")
	    @JsonBackReference(value = "salle-abonnement")
	    private Salle salle;

		public Abonnement() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Abonnement(long id, LocalDate dateDebut, LocalDate dateFin, Member member, Offre offre, Salle salle) {
			super();
			this.id = id;
			this.dateDebut = dateDebut;
			this.dateFin = dateFin;
			this.member = member;
			this.offre = offre;
			this.salle = salle;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public LocalDate getDateDebut() {
			return dateDebut;
		}

		public void setDateDebut(LocalDate dateDebut) {
			this.dateDebut = dateDebut;
		}

		public LocalDate getDateFin() {
			return dateFin;
		}

		public void setDateFin(LocalDate dateFin) {
			this.dateFin = dateFin;
		}

		public Member getMember() {
			return member;
		}

		public void setMember(Member member) {
			this.member = member;
		}

		public Offre getOffre() {
			return offre;
		}

		public void setOffre(Offre offre) {
			this.offre = offre;
		}

		public Salle getSalle() {
			return salle;
		}

		public void setSalle(Salle salle) {
			this.salle = salle;
		}

		@Override
		public String toString() {
			return "Abonnement [id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", member=" + member
					+ ", offre=" + offre + ", salle=" + salle + "]";
		}
	    
	    
	    
	
	
	
	
}
