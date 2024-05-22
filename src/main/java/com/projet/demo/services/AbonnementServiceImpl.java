package com.projet.demo.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.projet.demo.entities.Abonnement;
import com.projet.demo.entities.Categorie;
import com.projet.demo.entities.Member;
import com.projet.demo.entities.Offre;
import com.projet.demo.entities.Salle;
import com.projet.demo.repositories.AbonnementRepository;
import com.projet.demo.repositories.MemberRepository;
import com.projet.demo.repositories.OffreRepository;
import com.projet.demo.repositories.SalleRepository;


@Service
public class AbonnementServiceImpl implements AbonnementServiceInter{
	
	
	
	 @Autowired
	    private AbonnementRepository abonnementRepository;

	    @Autowired
	    private MemberRepository memberRepository;

	    @Autowired
	    private OffreRepository offreRepository;

	    @Autowired
	    private SalleRepository salleRepository;

	    public Abonnement addAbonnement(Long offreId, Long memberId) {
	        Optional<Offre> offreOptional = offreRepository.findById(offreId);
	        if (offreOptional.isEmpty()) {
	            throw new RuntimeException("Offre not found");
	        }

	        Optional<Member> memberOptional = memberRepository.findById(memberId);
	        if (memberOptional.isEmpty()) {
	            throw new RuntimeException("Member not found");
	        }

	        Offre offre = offreOptional.get();
	        Member member = memberOptional.get();

	        Abonnement abonnement = new Abonnement();
	        abonnement.setDateDebut(LocalDate.now());
	        abonnement.setDateFin(LocalDate.now().plusMonths(offre.getDureeEnMois()));
	        abonnement.setOffre(offre);
	        abonnement.setMember(member);
	        abonnement.setSalle(member.getSalle());

	        if (offre.getCategories().contains(Categorie.GROUP) && offre.getCategories().contains(Categorie.PERSONAL)) {
	            member.setEtat(4);
	        } else if (offre.getCategories().contains(Categorie.GROUP)) {
	            member.setEtat(2);
	        } else if (offre.getCategories().contains(Categorie.PERSONAL)) {
	            member.setEtat(3);
	        } else {
	            member.setEtat(1);
	        }

	        memberRepository.save(member);
	        return abonnementRepository.save(abonnement);
	    }
    
	    @Scheduled(cron = "0 0 0 * * *") 
	    public void verifierDateFinAbonnements() {
	        List<Abonnement> abonnementsExpires = abonnementRepository.findByDateFinBefore(LocalDate.now());
	        for (Abonnement abonnement : abonnementsExpires) {
	            Member member = abonnement.getMember();
	            member.setEtat(0); 
	            memberRepository.save(member);
	        }
    }
}


