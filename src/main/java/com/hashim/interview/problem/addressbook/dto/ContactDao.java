package com.hashim.interview.problem.addressbook.dto;

import java.util.List;

import com.hashim.interview.problem.addressbook.core.Contact;

public interface ContactDao {
	
	public void insert(Contact contact);
	
	public boolean remove(Contact contact);
	
	public boolean removeByFNameAndLName(String firstName, String lastName);
	
	public boolean update(Contact contact);

	public Contact getByFNameAndLName(String firstName,String lastName);
	
	public List<Contact> getAllContacts();
	
	public List<Contact> getlAllContacts(int limit);
	
}
