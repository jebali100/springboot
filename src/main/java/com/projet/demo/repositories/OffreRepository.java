package com.projet.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.demo.entities.Offre;

@Repository
public interface OffreRepository extends JpaRepository<Offre, Long> {
    List<Offre> findBySalleIdsalle(Long idsalle);

}
