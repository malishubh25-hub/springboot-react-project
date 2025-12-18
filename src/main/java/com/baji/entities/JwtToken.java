package com.baji.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class JwtToken {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TOKENRFNUM")
    private Long id;

	@Column(name = "TOKEN")
    private String token;
	
	@Column(name = "USERNAME")
    private String username;
	
	@Column(name = "EXPITRED")
    private boolean expired;
	
	@Column(name = "REVOKED")
    private boolean revoked;
	
	@Column(name = "EXPIRYDATE")
    private java.util.Date expiryDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isExpired() {
		return expired;
	}
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	public boolean isRevoked() {
		return revoked;
	}
	public void setRevoked(boolean revoked) {
		this.revoked = revoked;
	}
	public java.util.Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(java.util.Date expiry) {
		this.expiryDate = expiry;
	}

}
