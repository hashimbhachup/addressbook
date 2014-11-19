package com.interview.problem.addressbook.core;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name ="contact")
@XmlType(propOrder={"name","lName","address","contactInfo"})
@Named("contact")
public class Contact {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private long id;
	
	private String name;
	
	
	private String lName;
	
	private Address address;
	
	private ContactInfo contactInfo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.toLowerCase();;
	}
	
	public String getlName() {
		return lName;
	}

	@XmlElement(name = "lname")
	public void setlName(String lName) {
		this.lName = lName.toLowerCase();
	}

	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}
