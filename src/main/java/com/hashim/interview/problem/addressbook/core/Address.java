package com.hashim.interview.problem.addressbook.core;

import javax.inject.Named;

@Named("address")
public class Address{
	
	private String steetName ;
	
	private int streetNumber;
	
	private String postalCode;
	
	private String city;
	
	private String country;

	public String getSteetName() {
		return steetName;
	}

	public void setSteetName(String steetName) {
		this.steetName = steetName;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	

	
}
