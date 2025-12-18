package com.baji.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baji.entities.JwtToken;

public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {

	JwtToken findByToken(String jwt);

	
	
}