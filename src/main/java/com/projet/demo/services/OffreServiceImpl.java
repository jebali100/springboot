package com.projet.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.demo.entities.Offre;
import com.projet.demo.entities.Salle;
import com.projet.demo.entities.User;
import com.projet.demo.repositories.OffreRepository;
import com.projet.demo.repositories.SalleRepository;
import com.projet.demo.repositories.UserRepository;

@Service
public class OffreServiceImpl implements OffreServiceInter {
	
	 @Autowired
	    private OffreRepository offreRepository;

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private SalleRepository salleRepository;
	    
	    
	    
	    
	    
	    

	    @Override
	    public Offre addOffre(Long userId, Offre offre) {
	        Optional<User> userOptional = userRepository.findById(userId);
	        if (userOptional.isEmpty()) {
	            throw new RuntimeException("User not found");
	        }

	        User user = userOptional.get();
	        Salle salle = user.getSalle();
	        if (salle == null) {
	            throw new RuntimeException("Salle not associated with user");
	        }

	        offre.setSalle(salle);
	        return offreRepository.save(offre);
	    }

	    
	    
	    
	    
	    
	    
	    @Override
	    public void deleteOffre(Long idoffre, Long userId) {
	        Optional<User> userOptional = userRepository.findById(userId);
	        if (userOptional.isEmpty()) {
	            throw new RuntimeException("User not found");
	        }

	        User user = userOptional.get();
	        Salle salle = user.getSalle();
	        if (salle == null) {
	            throw new RuntimeException("Salle not associated with user");
	        }

	        Optional<Offre> offreOptional = offreRepository.findById(idoffre);
	        if (offreOptional.isEmpty() || !offreOptional.get().getSalle().equals(salle)) {
	            throw new RuntimeException("Offre not found or does not belong to the salle");
	        }

	        offreRepository.deleteById(idoffre);
	    }
	    
	    
	    
	    
	    
	    
	    

	    @Override
	    public Offre updateOffre(Long idoffre, Long userId, Offre offreDetails) {
	        Optional<Offre> offreOptional = offreRepository.findById(idoffre);
	        if (offreOptional.isEmpty()) {
	            throw new RuntimeException("Offre not found");
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

	        Offre offre = offreOptional.get();
	        offre.setNom(offreDetails.getNom());
	        offre.setDescription(offreDetails.getDescription());
	        offre.setPrix(offreDetails.getPrix());
	        offre.setDureeEnMois(offreDetails.getDureeEnMois());
	        offre.setCategories(offreDetails.getCategories());
	        offre.setSalle(salle);

	        return offreRepository.save(offre);
	    }
	    
	    
	    

	    @Override
	    public List<Offre> getOffresBySalleId(Long salleId) {
	        return offreRepository.findBySalleIdsalle(salleId);
	    }

	    
	    
	    
	    
	    
	    @Override
	    public List<Offre> getOffresByUserId(Long userId) {
	        Optional<User> userOptional = userRepository.findById(userId);
	        if (userOptional.isEmpty()) {
	            throw new RuntimeException("User not found");
	        }

	        User user = userOptional.get();
	        Salle salle = user.getSalle();
	        if (salle == null) {
	            throw new RuntimeException("Salle not associated with user");
	        }

	        return offreRepository.findBySalleIdsalle(salle.getIdsalle());
	    }
	
	
	

	

}
