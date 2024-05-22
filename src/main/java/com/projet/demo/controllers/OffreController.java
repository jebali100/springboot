package com.projet.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.projet.demo.entities.Offre;
import com.projet.demo.entities.User;
import com.projet.demo.repositories.UserRepository;
import com.projet.demo.services.OffreServiceImpl;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OffreController {
	
	
	 @Autowired
	    private OffreServiceImpl offreService;

	    @Autowired
	    private UserRepository userRepository;

	    @PostMapping("/addoffre")
	    public ResponseEntity<?> addOffre(@RequestBody Offre offre) {
	        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        String username = userDetails.getUsername();

	        Optional<User> userOptional = userRepository.findByUsername(username);
	        if (userOptional.isEmpty()) {
	            return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
	        }

	        User user = userOptional.get();
	        Offre newOffre = offreService.addOffre(user.getId(), offre);
	        return new ResponseEntity<>(newOffre, HttpStatus.CREATED);
	    }

	    @DeleteMapping("/deleteoffre/{idoffre}")
	    public ResponseEntity<?> deleteOffre(@PathVariable Long idoffre) {
	        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        String username = userDetails.getUsername();

	        Optional<User> userOptional = userRepository.findByUsername(username);
	        if (userOptional.isEmpty()) {
	            return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
	        }

	        User user = userOptional.get();
	        offreService.deleteOffre(idoffre, user.getId());
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    @PutMapping("/updateoffre/{idoffre}")
	    public ResponseEntity<?> updateOffre(@PathVariable Long idoffre, @RequestBody Offre offreDetails) {
	        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        String username = userDetails.getUsername();

	        Optional<User> userOptional = userRepository.findByUsername(username);
	        if (userOptional.isEmpty()) {
	            return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
	        }

	        User user = userOptional.get();
	        Offre updatedOffre = offreService.updateOffre(idoffre, user.getId(), offreDetails);
	        return new ResponseEntity<>(updatedOffre, HttpStatus.OK);
	    }

	    @GetMapping("/salleoffres/{salleId}")
	    public ResponseEntity<?> getOffresBySalleId(@PathVariable Long salleId) {
	        List<Offre> offres = offreService.getOffresBySalleId(salleId);
	        return new ResponseEntity<>(offres, HttpStatus.OK);
	    }

	    @GetMapping("/useroffres")
	    public ResponseEntity<?> getOffresByUserId() {
	        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        String username = userDetails.getUsername();

	        Optional<User> userOptional = userRepository.findByUsername(username);
	        if (userOptional.isEmpty()) {
	            return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
	        }

	        User user = userOptional.get();
	        List<Offre> offres = offreService.getOffresByUserId(user.getId());
	        return new ResponseEntity<>(offres, HttpStatus.OK);
	    }
    

    
    

  
}

