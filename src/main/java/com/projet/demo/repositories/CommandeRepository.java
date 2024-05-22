package com.projet.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.demo.entities.Commande;


@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

}
