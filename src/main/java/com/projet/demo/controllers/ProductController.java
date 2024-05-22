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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.demo.entities.Produit;
import com.projet.demo.entities.User;
import com.projet.demo.repositories.UserRepository;
import com.projet.demo.services.ProductServiceImpl;


@CrossOrigin(origins = "http://localhost:4200")


@RestController
public class ProductController {
	
	@Autowired
    private ProductServiceImpl productService;
	
	 
	 @Autowired
	    private UserRepository userRepository;
	 
	 
	 
	 @PostMapping("/addproduct")
	    public ResponseEntity<?> addProduct(@RequestBody Produit produit) {
	        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        String username = userDetails.getUsername();
	        
	        // Récupérer l'utilisateur par le nom d'utilisateur
	        Optional<User> userOptional = userRepository.findByUsername(username);
	        if (userOptional.isEmpty()) {
	            return new ResponseEntity<String>("User not found", HttpStatus.UNAUTHORIZED);
	        }

	        User user = userOptional.get();
	        Produit newProduct = productService.addProduct(user.getId(), produit);
	        return new ResponseEntity<Produit>(newProduct, HttpStatus.CREATED);
	    }

	    @DeleteMapping("/deleteproduct/{idproduct}")
	    public ResponseEntity<?> deleteProduct(@PathVariable("idproduct") Long idproduct) {
	        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        String username = userDetails.getUsername();
	        
	        // Récupérer l'utilisateur par le nom d'utilisateur
	        Optional<User> userOptional = userRepository.findByUsername(username);
	        if (userOptional.isEmpty()) {
	            return new ResponseEntity<String>("User not found", HttpStatus.UNAUTHORIZED);
	        }

	        User user = userOptional.get();
	        productService.deleteProduct(idproduct, user.getId());
	        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	    }

	    @PutMapping("/updateproduct/{idproduct}")
	    public ResponseEntity<?> updateProduct(@PathVariable("idproduct") Long idproduct, @RequestBody Produit produit) {
	        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        String username = userDetails.getUsername();
	        
	        // Récupérer l'utilisateur par le nom d'utilisateur
	        Optional<User> userOptional = userRepository.findByUsername(username);
	        if (userOptional.isEmpty()) {
	            return new ResponseEntity<String>("User not found", HttpStatus.UNAUTHORIZED);
	        }

	        User user = userOptional.get();
	        Produit updatedProduct = productService.updateProduct(idproduct, user.getId(), produit);
	        return new ResponseEntity<Produit>(updatedProduct, HttpStatus.OK);
	    }

	    @GetMapping("/products")
	    public ResponseEntity<?> getlistProduct() {
	        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        String username = userDetails.getUsername();
	        
	        // Récupérer l'utilisateur par le nom d'utilisateur
	        Optional<User> userOptional = userRepository.findByUsername(username);
	        if (userOptional.isEmpty()) {
	            return new ResponseEntity<String>("User not found", HttpStatus.UNAUTHORIZED);
	        }

	        User user = userOptional.get();
	        List<Produit> products = productService.getlistProduct(user.getId());
	        return new ResponseEntity<List<Produit>>(products, HttpStatus.OK);
	    }

	    @GetMapping("api/auth/products/salle/{salleId}")
	    public ResponseEntity<?> getlistProductBySalleId(@PathVariable("salleId") Long salleId) {
	        List<Produit> products = productService.getlistProductBySalleId(salleId);
	        return new ResponseEntity<List<Produit>>(products, HttpStatus.OK);
	    }
}
