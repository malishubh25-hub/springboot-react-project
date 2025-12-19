package com.baji.bean;

public class JwtToken {
	
	    private String token;
	    private String username;
	    private boolean expired;
	    private boolean revoked;
	    private java.util.Date expiryDate;
	    private String customerEmail;
	    private String password;
	    String getCustomerEmail() {
			return customerEmail;
		}
		public void setCustomerEmail(String customerEmail) {
			this.customerEmail = customerEmail;
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
		public void setExpiryDate(java.util.Date expiryDate) {
			this.expiryDate = expiryDate;
		}
		public CharSequence getPassword() {
			// TODO Auto-generated method stub
			return null;
		}

}
