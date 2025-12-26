package com.baji.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.baji.bean.AuthResponse;
import com.baji.bean.UserLoginBean;
import com.baji.common.utils.Constants;
import com.baji.common.utils.OmsUtils;
import com.baji.entities.BajiUserLogin;
import com.baji.entities.BajiUsrLoginDetails;
import com.baji.global.bean.CommonResponseBean;
import com.baji.iservice.IUserLoginService;
import com.baji.repositories.UserDetailsRepository;
import com.baji.repositories.UserLoginDtlsRepository;
import com.baji.utils.JwtTokenUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserLoginService implements IUserLoginService {

	@Autowired UserDetailsRepository userDetailRepository;
	@Autowired UserLoginDtlsRepository dtlsRepository;
	@Autowired JwtTokenUtil jwtTokenUtil;
	@Autowired private PasswordEncoder passwordEncoder;

	@Override
	public CommonResponseBean saveUserLogin(UserLoginBean userLoginBean) {
		CommonResponseBean responseBean = new CommonResponseBean();
		if (userLoginBean == null) {
			responseBean.setSuccess(false);
			responseBean.setMessage(Constants.MSG_INVALID_REQ);
			return responseBean;
		}
		String incomingNumber = OmsUtils.trimToNull(userLoginBean.getCustomerNumber());
		String incomingEmail = OmsUtils.normalizeEmail(userLoginBean.getCustomerEmail());
		try {
			boolean numberExists = false;
			boolean emailExists = false;

			if (incomingNumber != null) {
				numberExists = userDetailRepository.existsByCustomerNumber(incomingNumber);
			}
			if (incomingEmail != null) {
				emailExists = userDetailRepository.existsByCustomerEmail(incomingEmail);
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
			BajiUserLogin userEntity = mapBeanToEntity(userLoginBean);
			BajiUserLogin saved = userDetailRepository.save(userEntity);

			if (saved != null)
				saved.setPassword(null);

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

	private BajiUserLogin mapBeanToEntity(UserLoginBean userLoginBean) {
		BajiUserLogin userLogin = new BajiUserLogin();
		try {
			if (userLoginBean != null) {
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
				OmsUtils.setIfNotNull(userLogin::setPassword, hashed);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userLogin;
	}

	@Override
	public CommonResponseBean adminLoginSubmitAction(UserLoginBean userLoginBean) {

		CommonResponseBean responseBean = new CommonResponseBean();
		AuthResponse response = new AuthResponse();
		BajiUsrLoginDetails bajiUsrLoginDetails = new BajiUsrLoginDetails();
		if (Objects.nonNull(userLoginBean)) {
			responseBean.setSuccess(false);
			responseBean.setMessage(Constants.MSG_EMAIL_PASSS_REQUIRED);
			return responseBean;
		}
		try {
			BajiUserLogin userLogin = userDetailRepository.findByCustomerEmail(userLoginBean.getCustomerEmail());
			if (OmsUtils.isNotEmpty(userLogin)) {
				String uniqueID = jwtTokenUtil.generateTokens(userLogin.getCustomerNumber(), userLogin.getUserType());
				Date expiration = (Date) jwtTokenUtil.getExpirationDateFromToken(uniqueID);
				response.setAuthCode(uniqueID);
				response.setLoginId(userLogin.getCustomerRfNum().toString());
				response.setUserType(userLogin.getUserType());

				responseBean.setResponse(response);
				responseBean.setSuccess(true);
				responseBean.setMessage("login successfully");

				List<BajiUsrLoginDetails> loginDetails = dtlsRepository.findByBajiUsrUserDetail(userLogin);

				if (!loginDetails.isEmpty()) {
					BajiUsrLoginDetails existingLoginDetails = loginDetails.get(0);
					existingLoginDetails.setUserLastLogin(LocalDateTime.now());
					existingLoginDetails.setUserLoginNumber(userLogin.getCustomerNumber());
					existingLoginDetails.setUserLoginCounter(existingLoginDetails.getUserLoginCounter() + 1);
					existingLoginDetails.setUserLoginFetchId(userLogin.getCustomerRfNum());
					existingLoginDetails.setUserLoginExpiredDateTime(expiration + "");
					existingLoginDetails.setUserLoginToken(uniqueID);
					existingLoginDetails.setUserType(userLogin.getUserType());
					dtlsRepository.save(existingLoginDetails);
				} else {
					bajiUsrLoginDetails.setBajiUsrUserDetail(userLogin);
					bajiUsrLoginDetails.setUserLastLogin(LocalDateTime.now());
					bajiUsrLoginDetails.setUserLoginNumber(userLogin.getCustomerNumber());
					bajiUsrLoginDetails.setUserLoginCounter(1L);
					bajiUsrLoginDetails.setUserLoginFetchId(userLogin.getCustomerRfNum());
					bajiUsrLoginDetails.setUserLoginExpiredDateTime(expiration + "");
					bajiUsrLoginDetails.setUserLoginToken(uniqueID);
					bajiUsrLoginDetails.setUserType(userLogin.getUserType());
					dtlsRepository.save(bajiUsrLoginDetails);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setMessage("An unexpected error occurred during login.");
		}
		return responseBean;
	}
	
	public CommonResponseBean adminLogoutSubmitAction(HttpServletRequest req){
//		long num = Long.parseLong(req.getHeader("loginId"));
//		List<BajiUsrLoginDetails> loginDetailss = dtlsRepository.findByBajiUsrUserDetail(num);
//		if (!loginDetailss.isEmpty()) {
//			BajiUsrLoginDetails existingLoginDetails = loginDetailss.get(0);
//		    existingLoginDetails.setUserLoginToken(null);
//		    dtlsRepository.save(existingLoginDetails);
//		}
		return null;
		
	}

}
