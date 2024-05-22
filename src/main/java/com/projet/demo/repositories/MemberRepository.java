package com.projet.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projet.demo.entities.Member;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	boolean existsByEmail(String email);
	Boolean existsByUsername(String username);
	Optional<Member> findByUsername(String username);
	 List<Member> findByEtat(int etat);
	 @Query("SELECT DISTINCT m FROM Member m WHERE m.etat = 1")
	 List<Member> findDistinctByEtat();
	    List<Member> findBySalleIdsalle(Long salleId);
	 

	

}
