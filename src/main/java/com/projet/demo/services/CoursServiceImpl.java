package com.projet.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.demo.entities.Cours;
import com.projet.demo.entities.Salle;
import com.projet.demo.entities.User;
import com.projet.demo.repositories.CoursRepository;
import com.projet.demo.repositories.SalleRepository;
import com.projet.demo.repositories.UserRepository;

@Service
public class CoursServiceImpl implements CoursServiceInter {
	
	  @Autowired
	    private CoursRepository coursRepository;

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private SalleRepository salleRepository;
	    
	    
	  

	    @Override
	    public Cours addCours(Long userId, Cours cours) {
	        Optional<User> userOptional = userRepository.findById(userId);
	        if (userOptional.isEmpty()) {
	            throw new RuntimeException("User not found");
	        }

	        User user = userOptional.get();
	        Salle salle = user.getSalle();
	        if (salle == null) {
	            throw new RuntimeException("Salle not associated with user");
	        }

	        cours.setUser(user);
	        cours.setSalle(salle);
	        return coursRepository.save(cours);
	    }

	    @Override
	    public void deleteCours(Long idcours, Long userId) {
	        Optional<User> userOptional = userRepository.findById(userId);
	        if (userOptional.isEmpty()) {
	            throw new RuntimeException("User not found");
	        }

	        User user = userOptional.get();
	        Salle salle = user.getSalle();
	        if (salle == null) {
	            throw new RuntimeException("Salle not associated with user");
	        }

	        Optional<Cours> coursOptional = coursRepository.findByIdcoursAndSalleIdsalle(idcours, salle.getIdsalle());
	        if (coursOptional.isEmpty()) {
	            throw new RuntimeException("Cours not found or does not belong to the salle");
	        }

	        coursRepository.deleteById(idcours);
	    }

	    @Override
	    public List<Cours> getCoursBySalleId(Long salleId) {
	        return coursRepository.findBySalleIdsalle(salleId);
	    }

	    @Override
	    public Cours updateCours(Long idcours, Long userId, Cours coursDetails) {
	        Optional<Cours> coursOptional = coursRepository.findById(idcours);
	        if (coursOptional.isEmpty()) {
	            throw new RuntimeException("Cours not found");
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

	        Cours cours = coursOptional.get();
	        cours.setTitre(coursDetails.getTitre());
	        cours.setDate(coursDetails.getDate());
	        cours.setTime(coursDetails.getTime());
	        cours.setLevel(coursDetails.getLevel());
	        cours.setDetail(coursDetails.getDetail());
	        cours.setCategorie(coursDetails.getCategorie());
	        cours.setUser(user);
	        cours.setSalle(salle);

	        return coursRepository.save(cours);
	    }

	    @Override
	    public List<Cours> getCoursByUserId(Long userId) {
	        Optional<User> userOptional = userRepository.findById(userId);
	        if (userOptional.isEmpty()) {
	            throw new RuntimeException("User not found");
	        }

	        User user = userOptional.get();
	        Salle salle = user.getSalle();
	        if (salle == null) {
	            throw new RuntimeException("Salle not associated with user");
	        }

	        return coursRepository.findBySalleIdsalle(salle.getIdsalle());
	    }
	    

	    
	    
	    
	    
	   
	    

	  
	    
	    

	    

	

	

}
