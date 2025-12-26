package com.baji.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "BAJI_USER_LOGIN")
@JsonInclude(Include.NON_NULL)
public class BajiUserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_RFNUM")
    private Long customerRfNum;

    @Column(name = "CUSTOMERNAME")
    private String customerName;

    @Column(name = "CUSTOMEREMAIL")
    private String customerEmail;
    
    @Column(name = "USERTYPE")
    private String userType;

    @Column(name = "CUSTOMERNUMBER")
    private String customerNumber;

    @Column(name = "CUSTOMERGENDER")
    private String customerGender;

    @Column(name = "CUSTOMERNPERMANENTADDRESS")
    private String customerPermanentAddress;

    @Column(name = "CUSTOMERCURRENTADDRESS")
    private String customerCurrentAddress;

    @Column(name = "CUSTOMERAGE")
    private Integer customerAge;

    @Column(name = "CUSTOMERPARENTPHONENUMBER")
    private String customerParentNumber;

    @Column(name = "CUSTOMERWORKLOCATION")
    private String customerWorkLocation;

    @Column(name = "CUSTOMERWORKORGNISATION")
    private String customerWorkOrg;

    // map boolean to tinyint(1) in DB
    @Column(name = "CUSTOMERISACTIVE")
    private String customerIsActive;

    @Column(name = "CUSTOMERINTERESTEDIN")
    private String customerInterestedIn;
    
    @Column(name = "PASSWORD")
    private String password;
    
    @Column(name = "BLOODGROUP")
    private String bloodGroup;
    
    @Column(name = "COUNTRY")
    private String country;
    
    @Column(name = "STATE")
    private String state;
    
    @Column(name = "CITY")
    private String city;
    
    @Column(name = "DOB")
    private String dateOfBirthh;
    
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDateOfBirthh() {
		return dateOfBirthh;
	}

	public void setDateOfBirthh(String dateOfBirthh) {
		this.dateOfBirthh = dateOfBirthh;
	}

	public Long getCustomerRfNum() {
		return customerRfNum;
	}

	public void setCustomerRfNum(Long customerRfNum) {
		this.customerRfNum = customerRfNum;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerGender() {
		return customerGender;
	}

	public void setCustomerGender(String customerGender) {
		this.customerGender = customerGender;
	}

	public String getCustomerPermanentAddress() {
		return customerPermanentAddress;
	}

	public void setCustomerPermanentAddress(String customerPermanentAddress) {
		this.customerPermanentAddress = customerPermanentAddress;
	}

	public String getCustomerCurrentAddress() {
		return customerCurrentAddress;
	}

	public void setCustomerCurrentAddress(String customerCurrentAddress) {
		this.customerCurrentAddress = customerCurrentAddress;
	}

	public Integer getCustomerAge() {
		return customerAge;
	}

	public void setCustomerAge(Integer customerAge) {
		this.customerAge = customerAge;
	}

	public String getCustomerParentNumber() {
		return customerParentNumber;
	}

	public void setCustomerParentNumber(String customerParentNumber) {
		this.customerParentNumber = customerParentNumber;
	}

	public String getCustomerWorkLocation() {
		return customerWorkLocation;
	}

	public void setCustomerWorkLocation(String customerWorkLocation) {
		this.customerWorkLocation = customerWorkLocation;
	}

	public String getCustomerWorkOrg() {
		return customerWorkOrg;
	}

	public void setCustomerWorkOrg(String customerWorkOrg) {
		this.customerWorkOrg = customerWorkOrg;
	}

	public String getCustomerIsActive() {
		return customerIsActive;
	}

	public void setCustomerIsActive(String customerIsActive) {
		this.customerIsActive = customerIsActive;
	}

	public String getCustomerInterestedIn() {
		return customerInterestedIn;
	}

	public void setCustomerInterestedIn(String customerInterestedIn) {
		this.customerInterestedIn = customerInterestedIn;
	}
    
    
}
