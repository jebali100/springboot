package com.projet.demo.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.projet.demo.services.CartServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
@RestController

public class CartController {
	
	
	
	@Autowired
	CartServiceImpl CartServ;
	
	
	 @PostMapping (value = "/addtocart/{memberId}/{productId}/{quantity}")
	
	public void addtocart(@PathVariable Long memberId,@PathVariable Long productId,@PathVariable int quantity) {
	  CartServ.addToCart(memberId, productId, quantity);
		
		
	}
	 
	 
	 
	 
	 
	 
	 

}
