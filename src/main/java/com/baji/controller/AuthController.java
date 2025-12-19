package com.baji.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baji.bean.UserLoginBean;
import com.baji.entities.JwtToken;
import com.baji.entities.UserLogin;
import com.baji.global.bean.CommonResponseBean;
import com.baji.repositories.JwtTokenRepository;
import com.baji.repositories.UserLoginRepository;
import com.baji.utils.JwtTokenUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private JwtTokenRepository tokenRepo;

    @Autowired
    private UserLoginRepository loginRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody com.baji.bean.JwtToken loginRequest) {
        UserLogin user = loginRepository.findByCustomerEmail(loginRequest.getUsername());

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }  
        java.util.Date expiry = jwtUtil.getExpirationDateFromToken(loginRequest.getUsername());

        // Save token to DB      
        JwtToken jwtToken = new JwtToken();
        //jwtToken.setToken(token);
        jwtToken.setUsername(user.getCustomerEmail());
        jwtToken.setExpired(false);
        jwtToken.setRevoked(false);
        jwtToken.setExpiryDate(expiry);
        tokenRepo.save(jwtToken);

        Map<String, Object> response = new HashMap<>();
       // response.put("token", token);
        response.put("user", user);
        response.put("expiry", expiry);
        return null;
    }
}
