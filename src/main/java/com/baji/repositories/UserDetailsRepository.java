package com.baji.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baji.entities.BajiUserLogin;

@Repository
public interface UserDetailsRepository extends JpaRepository<BajiUserLogin, Long>{

	boolean existsByCustomerNumber(String customerNumber);
    boolean existsByCustomerEmail(String customerEmail);
    BajiUserLogin findByCustomerNumber(String customerNumber);
    BajiUserLogin findByCustomerEmail(String customerEmail);

}
