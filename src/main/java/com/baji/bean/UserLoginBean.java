package com.baji.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;


@Data
@JsonInclude(Include.NON_NULL)
public class UserLoginBean {
	
	private Long customerId;
    private String deleted;
    private String customerName;
    private String customerEmail;
    private String customerNumber;
    private String customerGender;
    private String customerPermanentAddress;
    private String customerCurrentAddress;
    private Integer customerAge;
    private String customerParentNumber;
    private String customerWorkLocation;
    private String customerWorkOrg;
    private String customerIsActive;
    private String customerInterestedIn;
    private String password;
    private String bloodGroup;
    private String country;
    private String state;
    private String city;
    private String dateOfBirth;
    private String loginId;
    
//    private String passPortSizePhoto;

	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Long getCustomerId() {
		return customerId;
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
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
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
