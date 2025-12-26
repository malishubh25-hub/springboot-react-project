package com.baji.utils;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.baji.common.utils.OmsUtils;
import com.baji.entities.BajiUsrLoginDetails;
import com.baji.repositories.UserLoginDtlsRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
	
	@Autowired private JwtTokenUtil jwtUtil;
	@Autowired UserLoginDtlsRepository userLoginRepository;
	@Autowired private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {

		String authorizationHeader = httpServletRequest.getHeader("Authorization");
		String token = null;
		String userName = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			token = authorizationHeader.substring(7);
			userName = jwtUtil.getUsernameFromToken(token);
		}
		List<BajiUsrLoginDetails> loginDetails = userLoginRepository.findByUserLoginNumber(userName);
		if (OmsUtils.isNotEmpty(loginDetails)) {
			if (OmsUtils.isNotEmpty(loginDetails.get(0).getUserLoginToken())) {
				if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

					if (jwtUtil.validateTokenForUser(token, userDetails)) {
						
						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());
						usernamePasswordAuthenticationToken
								.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

					} else {
						SecurityContextHolder.clearContext();
						httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
						return;
					}
				}
			}
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
