package com.hashim.interview.problem.addressbook.core;

import java.util.List;



public interface AddressBook{
	
	public boolean addContact(Contact contact);
	
	public boolean removeContact(Contact contact);
	
	public boolean updateContact(Contact contact);

	public Contact getContact(Contact contact);
	
	public List<Contact> getAllContacts();
	
}