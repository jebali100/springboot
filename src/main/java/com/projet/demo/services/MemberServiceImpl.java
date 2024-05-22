package com.projet.demo.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.demo.entities.Member;
import com.projet.demo.repositories.MemberRepository;

@Service
public class MemberServiceImpl implements MemberServiceInter {
	
	@Autowired
	MemberRepository MembRep;

	@Override
	public Member addMember(Member member) {
		// TODO Auto-generated method stub
		return MembRep.save(member);
	}
	
	
	

		
	

	
	
	
	

	
	
	
	@Override
	 public Set<Member> getMembresAbonnesUniques() {
        List<Member> membres = MembRep.findDistinctByEtat();
        return new HashSet<>(membres); 
	}
	  public Optional<Member> getLoggedInMember(String username) {
	        return MembRep.findByUsername(username);
	    }
	
	

}
