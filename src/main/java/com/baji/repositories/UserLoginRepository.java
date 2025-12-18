package com.baji.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baji.entities.UserLogin;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long>{

	boolean existsByCustomerNumber(String customerNumber);
    boolean existsByCustomerEmail(String customerEmail);
	UserLogin findByCustomerNumber(String customerNumber);
	UserLogin findByCustomerEmail(String customerEmail);

}
