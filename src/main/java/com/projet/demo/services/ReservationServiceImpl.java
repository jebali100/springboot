package com.projet.demo.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.demo.entities.Cours;
import com.projet.demo.entities.Member;
import com.projet.demo.entities.Reservation;
import com.projet.demo.repositories.CoursRepository;
import com.projet.demo.repositories.MemberRepository;
import com.projet.demo.repositories.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationServiceInter {
	
	
	
	 @Autowired
	    private MemberRepository memberRepository;

	    @Autowired
	    private CoursRepository coursRepository;

	    @Autowired
	    private ReservationRepository reservationRepository;
	    
	    
	    
	    
	
	
	

	@Override
	public void reserveCourse(long memberId, long courseId) {
		// TODO Auto-generated method stub
		 Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
	        Cours cours = coursRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

	        Reservation reservation = new Reservation();
	        reservation.setMember(member);
	        reservation.setCours(cours);

	        reservationRepository.save(reservation);
	    }
		
		
		
		
	
	
	
	
	
	
	
	
	
	

	@Override
	public List<Member> getMembersByCourseId(long courseId) {
		// TODO Auto-generated method stub
		Cours cours = coursRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        Set<Reservation> reservations = cours.getReservations();
        return reservations.stream().map(Reservation::getMember).collect(Collectors.toList());
	}

}
