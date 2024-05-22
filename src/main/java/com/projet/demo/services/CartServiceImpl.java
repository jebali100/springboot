package com.projet.demo.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.demo.entities.Cart;
import com.projet.demo.entities.Member;
import com.projet.demo.entities.Produit;
import com.projet.demo.repositories.CartRepository;
import com.projet.demo.repositories.MemberRepository;
import com.projet.demo.repositories.ProductRepository;

@Service
public class CartServiceImpl implements CartServiceInter{
	
	
	@Autowired
	CartRepository CartRep;
	
	@Autowired
	ProductRepository ProdRep;
	
	@Autowired
	MemberRepository MemRep;
	
	
	
	
	

	@Override
	public void addToCart(long memberId, long productId, int quantity) {
	    
	    Member member = MemRep.findById(memberId).get();
	    Produit product = ProdRep.findById(productId).get();
	    Cart cart = member.getCart();
	    if (cart == null) {
	        cart = new Cart();
	        cart.setMember(member);
	        member.setCart(cart);
	    }
	    boolean productAlreadyInCart = cart.getProducts().contains(product);

	    if (productAlreadyInCart) {
	     
	    } else {
	        
	        cart.getProducts().add(product);
	    }
	    cart.setQuantite(cart.getQuantite() + quantity);


        
        CartRep.save(cart);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	    
	}

	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	
	
	
		
		
 
		

	


