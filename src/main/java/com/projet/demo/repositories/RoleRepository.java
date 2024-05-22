package com.projet.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.demo.entities.Role;
import com.projet.demo.entities.Rolename;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
	Role getRoleByIdrole(Long idrole );
	
	
	Optional<Role> findByRolename(Rolename roleName);
	   default Optional<Role> findByRolenameString(String roleName) {
	        Rolename role = Rolename.valueOf(roleName.toLowerCase());
	        return findByRolename(role);
	    }

}
