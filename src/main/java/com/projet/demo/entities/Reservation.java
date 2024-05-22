package com.projet.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;

	    @ManyToOne
	    @JoinColumn(name = "member_id")
	    @JsonBackReference(value = "member-reservation")
	    private Member member;

	    @ManyToOne
	    @JoinColumn(name = "cours_id")
	    @JsonBackReference(value = "cours-reservation")
	    private Cours cours;

		public Reservation() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Reservation(long id, Member member, Cours cours) {
			super();
			this.id = id;
			this.member = member;
			this.cours = cours;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public Member getMember() {
			return member;
		}

		public void setMember(Member member) {
			this.member = member;
		}

		public Cours getCours() {
			return cours;
		}

		public void setCours(Cours cours) {
			this.cours = cours;
		}

		@Override
		public String toString() {
			return "Reservation [id=" + id + ", member=" + member + ", cours=" + cours + "]";
		}
	    

}
