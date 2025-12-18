package com.baji.iservice;

import com.baji.bean.UserLoginBean;
import com.baji.global.bean.CommonResponseBean;

public interface IUserLoginService {

	CommonResponseBean saveUserLogin(UserLoginBean userLoginBean);

	CommonResponseBean userLogin(UserLoginBean userLoginBean);

}
