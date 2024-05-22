package com.projet.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.demo.services.CommandeServiceImpl;

@RequestMapping("/api/auth")

@RestController
public class CommandeController {
	
	
	@Autowired
	CommandeServiceImpl CommandServ;
	
	@PostMapping(value = "/addCommand/{memberId}/{productId}/{quantity}")
	public void createOrder(@PathVariable Long memberId,@PathVariable Long productId,@PathVariable int quantity) {
		CommandServ.createOrder(memberId, productId, quantity);
	}
	

}
