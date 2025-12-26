package com.baji.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baji.bean.UserLoginBean;
import com.baji.global.bean.CommonResponseBean;
import com.baji.iservice.IUserLoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping(path = "/api/user/login")
public class UserLoginController {
	
	@Autowired IUserLoginService iUserLoginService;
	
	@PostMapping("/saveUserLogin")
	public ResponseEntity<CommonResponseBean> saveUserLogin(@RequestBody UserLoginBean userLoginBean){
		CommonResponseBean response = iUserLoginService.saveUserLogin(userLoginBean);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/openUser")
	public ResponseEntity<CommonResponseBean> userLogin(@RequestBody UserLoginBean userLoginBean){
		return new ResponseEntity<>(iUserLoginService.adminLoginSubmitAction(userLoginBean), HttpStatus.CREATED);
	}
	
	@PostMapping("/logout")
    public ResponseEntity<CommonResponseBean> logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<>(iUserLoginService.adminLogoutSubmitAction(request), HttpStatus.CREATED);
    }
}
