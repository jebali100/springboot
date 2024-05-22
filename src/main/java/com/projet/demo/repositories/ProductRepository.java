package com.projet.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.demo.entities.Produit;

@Repository
public interface ProductRepository extends JpaRepository<Produit, Long> {
	
	 List<Produit> findBySalleIdsalle(Long salleId);
	    Optional<Produit> findByIdAndSalleIdsalle(Long id, Long salleId);
}
