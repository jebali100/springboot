package com.projet.demo.entities;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;


@Entity
public class Commande {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime dateCommande;
    private LocalDateTime dateArrivee;
    private double totalPrice;

    
    @ManyToOne
    private Member member;
    
    @ManyToMany(mappedBy = "commandes")
    private Set<Produit> products = new HashSet<>();
    
    
    
    

	public Commande() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	



	public Commande(long id, LocalDateTime dateCommande, LocalDateTime dateArrivee, double totalPrice, Member member,
			Set<Produit> products) {
		super();
		this.id = id;
		this.dateCommande = dateCommande;
		this.dateArrivee = dateArrivee;
		this.totalPrice = totalPrice;
		this.member = member;
		this.products = products;
	}








	public long getId() {
		return id;
	}








	public void setId(long id) {
		this.id = id;
	}








	public Set<Produit> getProducts() {
		return products;
	}








	public void setProducts(Set<Produit> products) {
		this.products = products;
	}









	
	public LocalDateTime getDateCommande() {
		return dateCommande;
	}






	public void setDateCommande(LocalDateTime dateCommande) {
		this.dateCommande = dateCommande;
	}






	public LocalDateTime getDateArrivee() {
		return dateArrivee;
	}






	public void setDateArrivee(LocalDateTime dateArrivee) {
		this.dateArrivee = dateArrivee;
	}











	

	

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "Commande [id=" + id + ", dateCommande=" + dateCommande + ", dateArrivee=" + dateArrivee
				+ ", totalPrice=" + totalPrice + ", member=" + member + "]";
	}
    
    
    

}
