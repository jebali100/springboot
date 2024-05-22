package com.projet.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.demo.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
