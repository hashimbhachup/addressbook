package com.hashim.interview.problem.addressbook.core;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Named("contactInfo")
@Embeddable
public class ContactInfo{

	@Column(name="phone")
	private String phoneNumber;
	
	
	private String email;
	
	@Column(name="last_contact_date")
	private String lastContactDate;
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastContactDate() {
		return lastContactDate;
	}

	public void setLastContactDate(String lastContactDate) {
		this.lastContactDate = lastContactDate;
	}
}
