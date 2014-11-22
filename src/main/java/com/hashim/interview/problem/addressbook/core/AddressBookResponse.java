package com.hashim.interview.problem.addressbook.core;

import java.util.List;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="addressbook")
@XmlType(propOrder={"code","message","contacts"})
public class AddressBookResponse{

	private Status code;
	
	private String message;
	
	private List<Contact> contacts;

	public Status getCode() {
		return code;
	}

	public void setCode(Status code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	
	
	
	
}
