package com.projet.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.demo.entities.Role;
import com.projet.demo.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleserviceInter {
	
	@Autowired
	RoleRepository RoleRep;

	@Override
	public Role AddRole(Role role) {
		// TODO Auto-generated method stub
		return RoleRep.save(role);
	}
	
	

}
