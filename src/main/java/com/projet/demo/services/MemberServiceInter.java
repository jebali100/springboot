package com.projet.demo.services;

import java.util.List;
import java.util.Set;

import com.projet.demo.entities.Member;


public interface MemberServiceInter {
	public Member addMember(Member member );
	
	 
	 public Set<Member> getMembresAbonnesUniques();
	

}
