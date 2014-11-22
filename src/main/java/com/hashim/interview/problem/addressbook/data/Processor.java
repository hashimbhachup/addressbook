package com.hashim.interview.problem.addressbook.data;

import java.util.ConcurrentModificationException;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.hashim.interview.problem.addressbook.core.AddressBookImpl;
import com.hashim.interview.problem.addressbook.core.Contact;

/**
 * @author hassingh
 */
@Named("processor")
public class Processor {

	@Autowired
	private AddressBookImpl addressBookImpl;
	
	
	public boolean add(Contact contact){	
		return addressBookImpl.addContact(contact);
	}
	
	
	public List<Contact> pullAllRecords(){
		return addressBookImpl.getAllContacts();
	}
	
	public boolean update(Contact contact){
		try{
			return addressBookImpl.updateContact(contact); 
		}catch(ConcurrentModificationException ex){
			//TODO handle exceptions and generate better message response
			return false;
		}
	}
	
	public boolean delete(String name, String lname){
		Contact contact =new Contact();
		contact.setName(name);
		contact.setlName(lname);
		try{
			return addressBookImpl.removeContact(contact);
		}catch(ConcurrentModificationException ex){
			//TODO handle exceptions and generate better message response
			return false;
		}
	}
	
	public Contact pullContact(String name, String lname){
		Contact contact =new Contact();
		contact.setName(name);
		contact.setlName(lname);
		return addressBookImpl.getContact(contact);
	}
	
}
