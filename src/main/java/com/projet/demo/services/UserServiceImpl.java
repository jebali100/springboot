package com.projet.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.demo.entities.User;
import com.projet.demo.repositories.RoleRepository;
import com.projet.demo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserServiceInter {
	
	
	@Autowired
	UserRepository UserRep ;
	
	@Autowired
	RoleRepository RoleRep;
	
	
	
	
	


	
	
	
	

	@Override
	public User updateUser(Long iduser, User user) {
		// TODO Auto-generated method stub
		User usr = UserRep.findById(iduser).get();
		usr.setFirstname(user.getFirstname());
		usr.setLastname(user.getLastname());
		usr.setEmail(user.getEmail());
		usr.setPassword(user.getPassword());
		usr.setUsername(user.getUsername());
		return UserRep.save(usr);
	}
	
	
	
	
	
	

	@Override
	public void deleteUser(Long iduser) {
		// TODO Auto-generated method stub
		UserRep.deleteById(iduser);
	}
	
	
	
	
	

	@Override
	public List<User> getListUsers() {
		// TODO Auto-generated method stub
		return UserRep.findAll() ;
	}
	
	
	
	

	
	
	
	
	
	

	

	
	

}
