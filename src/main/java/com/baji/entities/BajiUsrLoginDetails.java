package com.baji.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "BAJI_USER_LOGIN_DETAILS")
@JsonInclude(Include.NON_NULL)
public class BajiUsrLoginDetails {
	
	 private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "USERLOGINIDDTSL")
	    private Long userLoginDtlsId;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "USERLOGINID", nullable = false)
	    private BajiUserLogin bajiUsrUserDetail;

	    @Column(name = "USERLASTLOGIN")
	    private LocalDateTime userLastLogin;

	    @Column(name = "USERLOGINEMAIL", length = 150)
	    private String userLoginNumber;

	    @Column(name = "USERLOGINCOUNTER")
	    private Long userLoginCounter;

	    @Column(name = "USERLOGINFETCHID", length = 50)
	    private Long userLoginFetchId;

	    @Column(name = "USERLOGINEXPIRYDT", length = 100)
	    private String userLoginExpiredDateTime;

	    @Column(name = "USERLOGINTOKEN", length = 255)
	    private String userLoginToken;

	    @Column(name = "USERTYPE", length = 50)
	    private String userType;

		public Long getUserLoginDtlsId() {
			return userLoginDtlsId;
		}

		public void setUserLoginDtlsId(Long userLoginDtlsId) {
			this.userLoginDtlsId = userLoginDtlsId;
		}

		public BajiUserLogin getBajiUsrUserDetail() {
			return bajiUsrUserDetail;
		}

		public void setBajiUsrUserDetail(BajiUserLogin bajiUsrUserDetail) {
			this.bajiUsrUserDetail = bajiUsrUserDetail;
		}

		public LocalDateTime getUserLastLogin() {
			return userLastLogin;
		}

		public void setUserLastLogin(LocalDateTime userLastLogin) {
			this.userLastLogin = userLastLogin;
		}

		public String getUserLoginNumber() {
			return userLoginNumber;
		}

		public void setUserLoginNumber(String userLoginNumber) {
			this.userLoginNumber = userLoginNumber;
		}

		public Long getUserLoginCounter() {
			return userLoginCounter;
		}

		public void setUserLoginCounter(Long userLoginCounter) {
			this.userLoginCounter = userLoginCounter;
		}

		public Long getUserLoginFetchId() {
			return userLoginFetchId;
		}

		public void setUserLoginFetchId(Long userLoginFetchId) {
			this.userLoginFetchId = userLoginFetchId;
		}

		public String getUserLoginExpiredDateTime() {
			return userLoginExpiredDateTime;
		}

		public void setUserLoginExpiredDateTime(String userLoginExpiredDateTime) {
			this.userLoginExpiredDateTime = userLoginExpiredDateTime;
		}

		public String getUserLoginToken() {
			return userLoginToken;
		}

		public void setUserLoginToken(String userLoginToken) {
			this.userLoginToken = userLoginToken;
		}

		public String getUserType() {
			return userType;
		}

		public void setUserType(String userType) {
			this.userType = userType;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
	    
	    

}
