package com.projet.demo.services;

import java.util.List;


import com.projet.demo.entities.User;

public interface UserServiceInter {
	

	public User updateUser(Long iduser, User user);
	public void deleteUser(Long iduser);
	public List<User> getListUsers();

	
	

}
