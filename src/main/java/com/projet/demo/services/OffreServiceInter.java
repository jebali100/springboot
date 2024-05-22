package com.projet.demo.services;

import java.util.List;

import com.projet.demo.entities.Offre;

public interface OffreServiceInter {
	Offre addOffre(Long userId, Offre offre);
    void deleteOffre(Long idoffre, Long userId);
    Offre updateOffre(Long idoffre, Long userId, Offre offreDetails);
    List<Offre> getOffresBySalleId(Long salleId);
    List<Offre> getOffresByUserId(Long userId);
}
