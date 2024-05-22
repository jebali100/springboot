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
import org.springframework.web.bind.annotation.RestController;

import com.projet.demo.entities.Cours;
import com.projet.demo.entities.User;
import com.projet.demo.repositories.UserRepository;
import com.projet.demo.services.CoursServiceImpl;


@RestController
public class CoursController {
	
	 @Autowired
	    private CoursServiceImpl coursService;
	 @Autowired
	 private UserRepository userRepository;


	    @PostMapping("/addcours")
	    public ResponseEntity<?> addCours(@RequestBody Cours cours) {
	        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        String username = userDetails.getUsername();

	        Optional<User> userOptional = userRepository.findByUsername(username);
	        if (userOptional.isEmpty()) {
	            return new ResponseEntity<String>("User not found", HttpStatus.UNAUTHORIZED);
	        }

	        User user = userOptional.get();
	        cours.setUser(user);
	        cours.setSalle(user.getSalle()); // Assurez-vous que l'utilisateur a une salle associée
	        Cours newCours = coursService.addCours(user.getId(), cours);
	        return new ResponseEntity<Cours>(newCours, HttpStatus.CREATED);
	    }

	    @DeleteMapping("/delete/{idcours}")
	    public ResponseEntity<?> deleteCours(@PathVariable Long idcours) {
	        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        String username = userDetails.getUsername();

	        Optional<User> userOptional = userRepository.findByUsername(username);
	        if (userOptional.isEmpty()) {
	            return new ResponseEntity<String>("User not found", HttpStatus.UNAUTHORIZED);
	        }

	        User user = userOptional.get();
	        coursService.deleteCours(idcours, user.getId());
	        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	    }

	    @PutMapping("/update/{idcours}")
	    public ResponseEntity<?> updateCours(@PathVariable Long idcours, @RequestBody Cours coursDetails) {
	        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        String username = userDetails.getUsername();

	        Optional<User> userOptional = userRepository.findByUsername(username);
	        if (userOptional.isEmpty()) {
	            return new ResponseEntity<String>("User not found", HttpStatus.UNAUTHORIZED);
	        }

	        User user = userOptional.get();
	        Cours updatedCours = coursService.updateCours(idcours, user.getId(), coursDetails);
	        return new ResponseEntity<Cours>(updatedCours, HttpStatus.OK);
	    }

	    @GetMapping("/user")
	    public ResponseEntity<?> getCoursByUser() {
	        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        String username = userDetails.getUsername();

	        Optional<User> userOptional = userRepository.findByUsername(username);
	        if (userOptional.isEmpty()) {
	            return new ResponseEntity<String>("User not found", HttpStatus.UNAUTHORIZED);
	        }

	        User user = userOptional.get();
	        List<Cours> coursList = coursService.getCoursByUserId(user.getId());
	        return new ResponseEntity<List<Cours>>(coursList, HttpStatus.OK);
	    }

	    @GetMapping("/salle/{salleId}")
	    public ResponseEntity<?> getCoursBySalleId(@PathVariable Long salleId) {
	        List<Cours> coursList = coursService.getCoursBySalleId(salleId);
	        return new ResponseEntity<List<Cours>>(coursList, HttpStatus.OK);
	    }

}
