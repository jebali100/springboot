package com.projet.demo.entities;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idcart;
	private int quantite;
	private double totalPrice;
	
	
	 
	  @OneToOne(mappedBy = "cart")
	    private Member member;
	
	
	 @ManyToMany(mappedBy = "carts")
	    private Set<Produit> products = new HashSet<>();

	
	
	
	

	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}


	

	
	
	
	public Set<Produit> getProducts() {
		return products;
	}







	public void setProducts(Set<Produit> products) {
		this.products = products;
	}







	public Cart(long idcart, int quantite, double totalPrice, Member member, Set<Produit> products) {
		super();
		this.idcart = idcart;
		this.quantite = quantite;
		this.totalPrice = totalPrice;
		this.member = member;
		this.products = products;
	}


	public double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	

	
	

	

	public long getIdcart() {
		return idcart;
	}

	public void setIdcart(long idcart) {
		this.idcart = idcart;
	}

	

	

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	

	@Override
	public String toString() {
		return "Cart [idcart=" + idcart + ", quantite=" + quantite + ", totalPrice=" + totalPrice + ", member=" + member
				+ ", products=" + products + "]";
	}

	




	
	
	

}
