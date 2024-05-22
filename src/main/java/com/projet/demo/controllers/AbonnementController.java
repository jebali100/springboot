package com.projet.demo.controllers;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.demo.entities.Abonnement;
import com.projet.demo.entities.Member;
import com.projet.demo.entities.Salle;
import com.projet.demo.repositories.MemberRepository;
import com.projet.demo.services.AbonnementServiceImpl;

@RestController

public class AbonnementController {
	
	 @Autowired
	    private AbonnementServiceImpl abonnementService;

	    @Autowired
	    private MemberRepository memberRepository;

	    @PostMapping("/create")
	    public ResponseEntity<Abonnement> createAbonnement(@RequestParam Long offreId) {
	        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        String username = userDetails.getUsername();

	        Optional<Member> memberOptional = memberRepository.findByUsername(username);
	        if (memberOptional.isEmpty()) {
	            throw new RuntimeException("Member not found");
	        }

	        Long memberId = memberOptional.get().getId();
	        Abonnement abonnement = abonnementService.addAbonnement(offreId, memberId);
	        return ResponseEntity.ok(abonnement);
	    }
	
	 
	
	    
}   


