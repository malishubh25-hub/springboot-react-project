package com.baji.utils;

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
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {

	private static final long serialVersionUID = 1L;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private static final String AES_ALGORITHM = "AES";

    @Value("${jwt.tokenValidityMs:86400000}")
    private long tokenValidityMs; // default 24h

    // HMAC key for signing JWTs
    private final SecretKey hmacKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public static String secret = "9ez/uqcQLFEeIG2KQy8pelosVDUB175poEoqpHh4YIg=";

    
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String getUserTypeFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(token).getBody();
        return (String) claims.get("userType");
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            System.out.println("Invalid JWT: " + e.getMessage());
            return false;
        }
    }
    
    public String getUserIdFromTokens(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(token).getBody();
        return (String) claims.get("userId");
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    
    public Date getExpiration(String token) {
        return Jwts.parserBuilder().setSigningKey(hmacKey).build()
                .parseClaimsJws(token).getBody().getExpiration();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(token).getBody();
    }

    @SuppressWarnings("unused")
	private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date(0, 0, 0));
    }

    public String generateTokens(String loginId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", loginId);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(loginId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(hmacKey)
                .compact();
    }
    
    public Long fetchUserIdFromToken(String encryptedToken) {
		Long id = null;
		try {
			encryptedToken = encryptedToken.substring(7);
			String decrypt = decrypt(encryptedToken);
			String  claims = getUsernameFromToken(decrypt);
			if(claims != null) {
				id = Long.parseLong(claims);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}

    public String createToken(String subject, String claimKey, Object claimValue, long tokenValidityInMs, Boolean rememberMe) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(claimKey, claimValue);
        long now = (new Date()).getTime();
        Date validity = new Date(now + tokenValidityInMs);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(validity)
                .signWith(hmacKey)
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(token).getBody();
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

    // AES encryption method
    public String encrypt(String data) throws Exception {
        SecretKeySpec key = new SecretKeySpec(Base64.getDecoder().decode(secret), AES_ALGORITHM); // Decode the Base64 key
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData); // Ensure data is Base64 encoded
    }

    public String decrypt(String encryptedData) throws Exception {
        SecretKeySpec key = new SecretKeySpec(Base64.getDecoder().decode(secret), AES_ALGORITHM); // Decode the Base64 key
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }

    public static String generateSecretKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(AES_ALGORITHM);
        keyGen.init(256); // for AES-256
        SecretKey secretKey = keyGen.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
         
    }

}
