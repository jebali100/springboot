package com.projet.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.demo.entities.Equipement;
import com.projet.demo.entities.Salle;
import com.projet.demo.entities.User;
import com.projet.demo.repositories.EquipementRepository;
import com.projet.demo.repositories.SalleRepository;
import com.projet.demo.repositories.UserRepository;

@Service
public class EquipementServiceImpl implements EquipementServiceInter {
	
	  @Autowired
	    private EquipementRepository equipementRepository;

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private SalleRepository salleRepository;

	    @Override
	    public Equipement addEquipement(Long userId, Equipement equipement) {
	        Optional<User> userOptional = userRepository.findById(userId);
	        if (userOptional.isEmpty()) {
	            throw new RuntimeException("User not found");
	        }

	        User user = userOptional.get();
	        Salle salle = user.getSalle();
	        if (salle == null) {
	            throw new RuntimeException("Salle not associated with user");
	        }

	        equipement.setSalle(salle);
	        return equipementRepository.save(equipement);
	    }

	    @Override
	    public void deleteEquipement(Long idequipement, Long userId) {
	        Optional<User> userOptional = userRepository.findById(userId);
	        if (userOptional.isEmpty()) {
	            throw new RuntimeException("User not found");
	        }

	        User user = userOptional.get();
	        Salle salle = user.getSalle();
	        if (salle == null) {
	            throw new RuntimeException("Salle not associated with user");
	        }

	        Optional<Equipement> equipementOptional = equipementRepository.findById(idequipement);
	        if (equipementOptional.isEmpty() || !equipementOptional.get().getSalle().equals(salle)) {
	            throw new RuntimeException("Equipement not found or does not belong to the salle");
	        }

	        equipementRepository.deleteById(idequipement);
	    }

	    @Override
	    public Equipement updateEquipement(Long idequipement, Long userId, Equipement equipementDetails) {
	        Optional<Equipement> equipementOptional = equipementRepository.findById(idequipement);
	        if (equipementOptional.isEmpty()) {
	            throw new RuntimeException("Equipement not found");
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

	        Equipement equipement = equipementOptional.get();
	        equipement.setName(equipementDetails.getName());
	        equipement.setDetails(equipementDetails.getDetails());
	        equipement.setSalle(salle);

	        return equipementRepository.save(equipement);
	    }
	    
	    

	    @Override
	    public List<Equipement> getEquipementsBySalleId(Long salleId) {
	        return equipementRepository.findBySalleIdsalle(salleId);
	    }
	    @Override
	    public List<Equipement> getEquipementsByUserId(Long userId) {
	        Optional<User> userOptional = userRepository.findById(userId);
	        if (userOptional.isEmpty()) {
	            throw new RuntimeException("User not found");
	        }

	        User user = userOptional.get();
	        Salle salle = user.getSalle();
	        if (salle == null) {
	            throw new RuntimeException("Salle not associated with user");
	        }

	        return equipementRepository.findBySalleIdsalle(salle.getIdsalle());
	    }
	    

}
