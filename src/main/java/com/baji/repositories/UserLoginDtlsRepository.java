package com.baji.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baji.entities.BajiUserLogin;
import com.baji.entities.BajiUsrLoginDetails;

@Repository
public interface UserLoginDtlsRepository extends JpaRepository<BajiUsrLoginDetails, Long>{

	List<BajiUsrLoginDetails> findByBajiUsrUserDetail(BajiUserLogin customerRfNum);

	List<BajiUsrLoginDetails> findByUserLoginNumber(String userName);

}
