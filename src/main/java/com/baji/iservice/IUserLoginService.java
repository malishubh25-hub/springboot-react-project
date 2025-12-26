package com.baji.iservice;

import com.baji.bean.UserLoginBean;
import com.baji.global.bean.CommonResponseBean;

import jakarta.servlet.http.HttpServletRequest;

public interface IUserLoginService {

	CommonResponseBean saveUserLogin(UserLoginBean userLoginBean);;

	CommonResponseBean adminLoginSubmitAction(UserLoginBean userLoginBean);

	CommonResponseBean adminLogoutSubmitAction(HttpServletRequest request);

}
