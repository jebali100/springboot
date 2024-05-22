package com.projet.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projet.demo.entities.Equipement;
import com.projet.demo.entities.User;
import com.projet.demo.repositories.UserRepository;
import com.projet.demo.services.EquipementServiceImpl;
@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class EquipementController {
	
	
	@Autowired
    private EquipementServiceImpl equipementService;

    @Autowired
    private UserRepository userRepository;
    
    
    @PostMapping("/addequipement")
    public ResponseEntity<?> addEquipement(@RequestBody Equipement equipement) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
        }

        User user = userOptional.get();
        Equipement newEquipement = equipementService.addEquipement(user.getId(), equipement);
        return new ResponseEntity<>(newEquipement, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteequipement/{idequipement}")
    public ResponseEntity<?> deleteEquipement(@PathVariable Long idequipement) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
        }

        User user = userOptional.get();
        equipementService.deleteEquipement(idequipement, user.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/updateequipement/{idequipement}")
    public ResponseEntity<?> updateEquipement(@PathVariable Long idequipement, @RequestBody Equipement equipementDetails) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
        }

        User user = userOptional.get();
        Equipement updatedEquipement = equipementService.updateEquipement(idequipement, user.getId(), equipementDetails);
        return new ResponseEntity<>(updatedEquipement, HttpStatus.OK);
    }

    @GetMapping("/api/auth/salleequipement/{salleId}")
    public ResponseEntity<?> getEquipementsBySalleId(@PathVariable Long salleId) {
        List<Equipement> equipements = equipementService.getEquipementsBySalleId(salleId);
        return new ResponseEntity<>(equipements, HttpStatus.OK);
    }

    @GetMapping("/userequipement")
    public ResponseEntity<?> getEquipementsByUserId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
        }

        User user = userOptional.get();
        List<Equipement> equipements = equipementService.getEquipementsByUserId(user.getId());
        return new ResponseEntity<>(equipements, HttpStatus.OK);
    }

}
