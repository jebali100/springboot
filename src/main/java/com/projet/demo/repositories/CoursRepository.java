package com.projet.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.demo.entities.Cours;

@Repository
public interface CoursRepository extends JpaRepository<Cours, Long> {
	List<Cours> findBySalleIdsalle(Long salleId);
    Optional<Cours> findByIdcoursAndSalleIdsalle(Long idcours, Long salleId);
}
