package com.projet.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.projet.demo.entities.Salle;
import com.projet.demo.repositories.SalleRepository;

@Service
public class SalleServiceImpl implements SalleServiceInter {
	 @Autowired
	    private SalleRepository salleRepository;

	    public List<Salle> getAllSalles() {
	        return salleRepository.findAll();
	    }

	    public Optional<Salle> getSalleById(long idsalle) {
	        return salleRepository.findById(idsalle);
	    }

	    public Salle createSalle(Salle salle) {
	        return salleRepository.save(salle);
	    }

	    public Optional<Salle> updateSalle(long idsalle, Salle salleDetails) {
	        return salleRepository.findById(idsalle).map(salle -> {
	            salle.setName(salleDetails.getName());
	            salle.setAdresse(salleDetails.getAdresse());
	            return salleRepository.save(salle);
	        });
	    }

	    public boolean deleteSalle(long idsalle) {
	        return salleRepository.findById(idsalle).map(salle -> {
	            salleRepository.delete(salle);
	            return true;
	        }).orElse(false);
	    }
}


