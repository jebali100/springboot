package com.projet.demo.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.demo.entities.Cart;
import com.projet.demo.entities.Member;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
	Cart findByMember(Member member);

}
