package com.projet.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projet.demo.entities.Role;
import com.projet.demo.services.RoleServiceImpl;

@RestController
public class RoleController {
	
	@Autowired
	RoleServiceImpl RoleServ;
	
	@PostMapping(value = "/addrole")
	public Role AddRole(@RequestBody Role role) {
		return RoleServ.AddRole(role);
	}

}
