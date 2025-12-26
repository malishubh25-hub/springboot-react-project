package com.baji.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.baji.entities.BajiUserLogin;
import com.baji.repositories.UserDetailsRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired private UserDetailsRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        BajiUserLogin user = userRepository .findByCustomerNumber(username);

        return User.builder().username(user.getCustomerNumber()) .password(user.getPassword()).authorities("USER") .build();
    }

}
