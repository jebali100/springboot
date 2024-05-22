package com.projet.demo.services;

import java.util.List;

import com.projet.demo.entities.Equipement;

public interface EquipementServiceInter {
	 Equipement addEquipement(Long userId, Equipement equipement);
	    void deleteEquipement(Long idequipement, Long userId);
	    Equipement updateEquipement(Long idequipement, Long userId, Equipement equipementDetails);
	    List<Equipement> getEquipementsBySalleId(Long salleId);
	    List<Equipement> getEquipementsByUserId(Long userId);


}
