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
    
	
    
    
}
