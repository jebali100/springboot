package com.projet.demo.services;

import java.util.List;

import com.projet.demo.entities.Member;

public interface ReservationServiceInter {
	  void reserveCourse(long memberId, long courseId);
	    List<Member> getMembersByCourseId(long courseId);

}
