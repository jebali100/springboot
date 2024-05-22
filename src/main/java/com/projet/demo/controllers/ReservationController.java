package com.projet.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.demo.entities.Member;
import com.projet.demo.services.ReservationServiceImpl;

@RestController
public class ReservationController {

	@Autowired
    private ReservationServiceImpl reservationService;
	
	
	
	
	 @PostMapping(value = "/reserve/{memberId}/{courseId}")
	    public ResponseEntity<String> reserveCourse(@PathVariable long memberId, @PathVariable long courseId) {
	        reservationService.reserveCourse(memberId, courseId);
	        return ResponseEntity.ok("Course reserved successfully");
	    }
	
	 @GetMapping(value = "/membersReserved/{courseId}")
	    public ResponseEntity<List<Member>> getMembersByCourseId(@PathVariable("courseId") long courseId) {
	        List<Member> members = reservationService.getMembersByCourseId(courseId);
	        return ResponseEntity.ok(members);
	 }
	 
	 
	
}
