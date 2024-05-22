package com.projet.demo.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.demo.entities.Cart;
import com.projet.demo.entities.Commande;
import com.projet.demo.entities.Member;
import com.projet.demo.entities.Produit;
import com.projet.demo.repositories.CartRepository;
import com.projet.demo.repositories.CommandeRepository;
import com.projet.demo.repositories.MemberRepository;
import com.projet.demo.repositories.ProductRepository;


@Service
public class CommandeServiceImpl implements CommandeServiceInter {
	
	
	
	
	@Autowired
    CommandeRepository commandeRepository;

    @Autowired
     CartRepository cartRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ProductRepository productRepository;
  
	
	

	@Override
	public void createOrder(Long memberId, Long productId, int quantity) {
		// TODO Auto-generated method stub
		
			Member member = memberRepository.findById(memberId).get();
			Produit product=productRepository.findById(productId).get();
			
			Commande commande = new Commande();
	        commande.setMember(member);
	       
	        commande.setDateCommande(LocalDateTime.now());
	        commande.setDateArrivee(LocalDateTime.now().plusDays(7));
	        
	        
	        Set<Produit> produits = new HashSet<>();
	        produits.add(product);
	        commande.setProducts(produits);
	        double totalPrix = 0.0;
	        for (Produit produit : commande.getProducts()) {
	            double prixUnitaire = produit.getPrix(); 
	            totalPrix += prixUnitaire * quantity;
	        }
	        
	        commande.setTotalPrice(totalPrix);
	        
	        commandeRepository.save(commande);
	        
	        
	        Cart cart = cartRepository.findByMember(member);
	        
	        if (cart != null) {
	            cart.getProducts().remove(product);
	            cart.setQuantite(0);
	        }
	        
	        cartRepository.save(cart);
	        

	}
	

}
