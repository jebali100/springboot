package com.projet.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.projet.demo.entities.Produit;
import com.projet.demo.entities.Salle;
import com.projet.demo.entities.User;
import com.projet.demo.repositories.ProductRepository;
import com.projet.demo.repositories.SalleRepository;
import com.projet.demo.repositories.UserRepository;

@Service
public class ProductServiceImpl implements ProductServiceInter {
	
	 
	    
	    
	    
	    
	    @Autowired
	    private ProductRepository prodRep;

	    @Autowired
	    private UserRepository userRepository;
	    
	    
	    
	    

	    
	    public Produit addProduct(Long userId, Produit produit) {
	        // Récupérer l'utilisateur
	        Optional<User> userOptional = userRepository.findById(userId);
	        if (userOptional.isEmpty()) {
	            throw new RuntimeException("User not found");
	        }

	        // Vérifier l'association de l'utilisateur avec une salle
	        User user = userOptional.get();
	        Salle salle = user.getSalle();
	        if (salle == null) {
	            throw new RuntimeException("Salle not associated with user");
	        }

	        produit.setSalle(salle);
	        return prodRep.save(produit);
	    }
	    
	    

	    public void deleteProduct(Long idproduct, Long userId) {
	        Optional<User> userOptional = userRepository.findById(userId);
	        if (userOptional.isEmpty()) {
	            throw new RuntimeException("User not found");
	        }

	        User user = userOptional.get();
	        Salle salle = user.getSalle();
	        if (salle == null) {
	            throw new RuntimeException("Salle not associated with user");
	        }

	        Optional<Produit> produitOptional = prodRep.findByIdAndSalleIdsalle(idproduct, salle.getIdsalle());
	        if (produitOptional.isEmpty()) {
	            throw new RuntimeException("Product not found or does not belong to the salle");
	        }

	        prodRep.deleteById(idproduct);
	    }

	    public Produit updateProduct(Long idproduct, Long userId, Produit produitDetails) {
	        Optional<Produit> produitOptional = prodRep.findById(idproduct);
	        if (produitOptional.isEmpty()) {
	            throw new RuntimeException("Product not found");
	        }

	        Optional<User> userOptional = userRepository.findById(userId);
	        if (userOptional.isEmpty()) {
	            throw new RuntimeException("User not found");
	        }

	        User user = userOptional.get();
	        Salle salle = user.getSalle();
	        if (salle == null) {
	            throw new RuntimeException("Salle not associated with user");
	        }

	        Produit produit = produitOptional.get();
	        produit.setDescription(produitDetails.getDescription());
	        produit.setNom(produitDetails.getNom());
	        produit.setPrix(produitDetails.getPrix());
	        produit.setSalle(salle);
	        return prodRep.save(produit);
	    }

	    public List<Produit> getlistProduct(Long userId) {
	        Optional<User> userOptional = userRepository.findById(userId);
	        if (userOptional.isEmpty()) {
	            throw new RuntimeException("User not found");
	        }

	        User user = userOptional.get();
	        Salle salle = user.getSalle();
	        if (salle == null) {
	            throw new RuntimeException("Salle not associated with user");
	        }

	        return prodRep.findBySalleIdsalle(salle.getIdsalle());
	    }

	    public List<Produit> getlistProductBySalleId(Long salleId) {
	        return prodRep.findBySalleIdsalle(salleId);
	    }
	

}
