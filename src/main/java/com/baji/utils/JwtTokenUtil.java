package com.baji.utils;

import java.security.Key;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.baji.bean.UserLoginBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {
	private static final long serialVersionUID = 1L;
	private static final String ALGORITHM = "AES";
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.token.validity}")
	private Duration jwtValidity;

	Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public String getUserTypeFromToken(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
		return (String) claims.get("userType");
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String getUserTypeFromTokens(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
		return (String) claims.get("loginId");
	}

	public String generateToken(UserLoginBean userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder().setClaims(claims).setSubject(userDetails.getLoginId())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).signWith(key)
				.compact();
	}

	public String generateTokens(String loginId, String userType) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("userType", userType);
		claims.put("loginId", loginId);

		return Jwts.builder().setClaims(claims).setSubject(loginId).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).signWith(key)
				.compact();
	}

	public String createToken(String subject, String claimKey, Object claimValue, long tokenValidityInMs,
			Boolean rememberMe) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(claimKey, claimValue);
		long now = (new Date()).getTime();
		Date validity = new Date(now + tokenValidityInMs);

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(validity).signWith(key).compact();
	}

	public Claims parseToken(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}

	public Boolean validateTokenForUser(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public Boolean validateToken(String token, UserLoginBean userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getLoginId()) && !isTokenExpired(token));
	}

	public Claims getOtpJwtClaims(String token) {
		Claims claims = null;
		try {
			claims = getAllClaimsFromToken(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return claims;
	}

	public String encrypt(String data) throws Exception {
		String secret = generateSecretKey();
		SecretKeySpec key = new SecretKeySpec(secret.getBytes(), ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedData = cipher.doFinal(data.getBytes());
		return Base64.getEncoder().encodeToString(encryptedData);
	}

	public String decrypt(String encryptedData) throws Exception {
		String secret = generateSecretKey();
		SecretKeySpec key = new SecretKeySpec(secret.getBytes(), ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decodedData = Base64.getDecoder().decode(encryptedData);
		byte[] decryptedData = cipher.doFinal(decodedData);
		return new String(decryptedData);
	}

	public String generateSecretKey() throws Exception {
		KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
		keyGen.init(128); // for AES-128
		SecretKey secretKey = keyGen.generateKey();
		return Base64.getEncoder().encodeToString(secretKey.getEncoded());
	}
}
