package com.hashim.interview.problem.addressbook.core;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Named("address")
@Embeddable
public class Address{
	
	@Column(name="street_name")
	private String steetName ;
	
	@Column(name="street_number")
	private int streetNumber;
	
	@Column(name="postal_code")
	private String postalCode;
	
	@Column(name="city")
	private String city;
	
	@Column(name="country")
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
