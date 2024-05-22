package com.projet.demo.services;

import java.util.List;

import com.projet.demo.entities.Cours;

public interface CoursServiceInter {
	 Cours addCours(Long userId, Cours cours);
	    void deleteCours(Long idcours, Long userId);
	    List<Cours> getCoursBySalleId(Long salleId);
	    Cours updateCours(Long idcours, Long userId, Cours coursDetails);
	    List<Cours> getCoursByUserId(Long userId);
	

	
}
