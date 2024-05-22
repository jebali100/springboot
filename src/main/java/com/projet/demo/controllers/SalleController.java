package com.projet.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.demo.entities.Salle;
import com.projet.demo.services.SalleServiceImpl;


@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")


@RestController

public class SalleController {

	@Autowired
    private SalleServiceImpl salleService;


    @GetMapping("/listsalle")
    public List<Salle> getAllSalles() {
        return salleService.getAllSalles();
    }

   

	    // Créer une nouvelle salle
	    @PostMapping("/addsalle")
	    public Salle createSalle(@RequestBody Salle salle) {
	        return salleService.createSalle(salle);
	    }

	   
	    @PutMapping("/{idsalle}")
	    public ResponseEntity<Salle> updateSalle(@PathVariable(value = "idsalle") long idsalle, @RequestBody Salle salleDetails) {
	        Optional<Salle> updatedSalle = salleService.updateSalle(idsalle, salleDetails);
	        return updatedSalle.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    // Supprimer une salle
	    @DeleteMapping("/{idsalle}")
	    public ResponseEntity<Void> deleteSalle(@PathVariable(value = "idsalle") long idsalle) {
	        boolean isDeleted = salleService.deleteSalle(idsalle);
	        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	    }
	
}
