package com.baji.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.baji.bean.UserLoginBean;
import com.baji.common.utils.Constants;
import com.baji.common.utils.OmsUtils;
import com.baji.entities.UserLogin;
import com.baji.global.bean.CommonResponseBean;
import com.baji.iservice.IUserLoginService;
import com.baji.repositories.UserLoginRepository;
import com.baji.utils.JwtTokenUtil;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserLoginService implements IUserLoginService{

	@Autowired UserLoginRepository loginRepository;
	@Autowired JwtTokenUtil encDec;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Override
	public CommonResponseBean saveUserLogin(UserLoginBean userLoginBean) {
		CommonResponseBean responseBean = new CommonResponseBean();
		if (userLoginBean == null) {
            responseBean.setSuccess(false);
            responseBean.setMessage(Constants.MSG_INVALID_REQ);
            return responseBean;
        }
        String incomingNumber = OmsUtils.trimToNull(userLoginBean.getCustomerNumber());
        String incomingEmail  = OmsUtils.normalizeEmail(userLoginBean.getCustomerEmail());
		try {
			 boolean numberExists = false;
	         boolean emailExists  = false;

	            if (incomingNumber != null) {
	                numberExists = loginRepository.existsByCustomerNumber(incomingNumber);
	            }
	            if (incomingEmail != null) {
	                emailExists = loginRepository.existsByCustomerEmail(incomingEmail);
	            }

	            if (numberExists && emailExists) {
	                responseBean.setSuccess(false);
	                responseBean.setMessage(Constants.MSG_BOTH_EXISTS);
	                return responseBean;
	            } else if (numberExists) {
	                responseBean.setSuccess(false);
	                responseBean.setMessage(Constants.MSG_MOBILE_EXISTS);
	                return responseBean;
	            } else if (emailExists) {
	                responseBean.setSuccess(false);
	                responseBean.setMessage(Constants.MSG_EMAIL_EXISTS);
	                return responseBean;
	            }
	            UserLogin userEntity = mapBeanToEntity(userLoginBean);
	            UserLogin saved = loginRepository.save(userEntity);
	            
	         // hide password before returning
	            if (saved != null) saved.setPassword(null);

	            responseBean.setSuccess(true);
	            responseBean.setMessage(Constants.MSG_REG_SUCCESS);
	            responseBean.setResponse(saved);
	            return responseBean;
	            
		} catch (Exception e) {
//			Logger.error("Error while registering user: number={}, email={}", incomingNumber, incomingEmail, e);
            responseBean.setSuccess(false);
            responseBean.setMessage(Constants.MSG_REG_ERROR);
            return responseBean;
		}
	}

	private UserLogin mapBeanToEntity(UserLoginBean userLoginBean) {
		UserLogin userLogin = new UserLogin();
		try {
			if(userLoginBean != null) {
				OmsUtils.setIfNotNull(userLogin::setCustomerName, userLoginBean.getCustomerName());
				OmsUtils.setIfNotNull(userLogin::setCustomerEmail, userLoginBean.getCustomerEmail());
				OmsUtils.setIfNotNull(userLogin::setCustomerAge, userLoginBean.getCustomerAge());
				OmsUtils.setIfNotNull(userLogin::setCustomerCurrentAddress, userLoginBean.getCustomerCurrentAddress());
				OmsUtils.setIfNotNull(userLogin::setCustomerGender, userLoginBean.getCustomerGender());
				OmsUtils.setIfNotNull(userLogin::setCustomerInterestedIn, userLoginBean.getCustomerInterestedIn());
				OmsUtils.setIfNotNull(userLogin::setCustomerNumber, userLoginBean.getCustomerNumber());
				OmsUtils.setIfNotNull(userLogin::setCustomerParentNumber, userLoginBean.getCustomerParentNumber());
				OmsUtils.setIfNotNull(userLogin::setCustomerPermanentAddress, userLoginBean.getCustomerPermanentAddress());
				OmsUtils.setIfNotNull(userLogin::setCustomerWorkLocation, userLoginBean.getCustomerWorkLocation());
				OmsUtils.setIfNotNull(userLogin::setCustomerWorkOrg, userLoginBean.getCustomerWorkOrg());
				OmsUtils.setIfNotNull(userLogin::setCity, userLoginBean.getCity());
				OmsUtils.setIfNotNull(userLogin::setBloodGroup, userLoginBean.getBloodGroup());
				OmsUtils.setIfNotNull(userLogin::setState, userLoginBean.getState());
				OmsUtils.setIfNotNull(userLogin::setCountry, userLoginBean.getCountry());
				OmsUtils.setIfNotNull(userLogin::setDateOfBirthh, userLoginBean.getDateOfBirth());
                OmsUtils.setIfNotNull(userLogin::setCustomerIsActive, "Y");
                String hashed = passwordEncoder.encode(userLoginBean.getPassword());
				OmsUtils.setIfNotNull(userLogin::setPassword,hashed);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userLogin;
	}
	
	
	@Override
	public CommonResponseBean userLogin(UserLoginBean userLoginBean) {
		CommonResponseBean responseBean = new CommonResponseBean();;
		if (userLoginBean == null || userLoginBean.getCustomerEmail() == null || userLoginBean.getPassword() == null) {
	        responseBean.setSuccess(false);
	        responseBean.setMessage(Constants.MSG_EMAIL_PASSS_REQUIRED);
	        return responseBean;
	    }
		try {
			String email = userLoginBean.getCustomerEmail().trim().toLowerCase();
	        String rawPassword = userLoginBean.getPassword();
	     // Fetch user by email
	        UserLogin userLogin = loginRepository.findByCustomerEmail(email);
	        if (userLogin == null) {
	            responseBean.setSuccess(false);
	            responseBean.setMessage(Constants.MSG_INVALID_EMAIL_PASSS);
	            return responseBean;
	        }
	        
	        boolean passwordMatch = passwordEncoder.matches(rawPassword, userLogin.getPassword());
	        if (!passwordMatch) {
	            responseBean.setSuccess(false);
	            responseBean.setMessage(Constants.MSG_INVALID_EMAIL_PASSS);
	            return responseBean;
	        }
	        
	        userLogin.setPassword(null);

	        responseBean.setSuccess(true);
	        responseBean.setResponse(userLogin);
	        responseBean.setMessage(Constants.MSG_USER_LOGIN);
	        
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
	        responseBean.setMessage("An unexpected error occurred during login.");
		}
		return responseBean;
	}
	

}
