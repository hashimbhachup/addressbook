package com.hashim.interview.problem.addressbook.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.List;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.Table;


@Named("addressBookImpl")
@Entity
@Table(name="addressbook")
public class AddressBookImpl implements AddressBook{
	
	private List<Contact> contacts;
	
	public AddressBookImpl(){
//		contacts = new ArrayList<Contact>();
		contacts = Collections.synchronizedList(new ArrayList<Contact>());
	}
	
	/**
	 * Add contact to address book
	 * @param {@link Contact}
	 * @return {@link Boolean} returns true if added successfully 
	 * otherwise return false if contact exists .
	 */
	public boolean addContact(Contact contact) {
			if(!exits(contact)){
				contacts.add(contact);
				return true;
			}
		
		return false;
	}
	
	/**
	 * udpate existing contact to address book
	 * @param {@link Contact}
	 * @return {@link Boolean} true if updated successfully otherwise return false.
	 * @throws IllegalStateException, ConcurrentModificationException
	 */
	public boolean updateContact(Contact contact) throws IllegalStateException, ConcurrentModificationException{
		synchronized (contacts) {
			
			ContactsIterator iterator = getIterator();
			while(iterator.hasNext()){
				if(equals(iterator.next(),contact)){
					iterator.update(contact);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Remove existing contact from address book
	 * @param {@link Contact}
	 * @return {@link Boolean} true if removed successfully otherwise return false.
	 * @throws IllegalStateException, ConcurrentModificationException
	 */
	public boolean removeContact(Contact contact) throws IllegalStateException, ConcurrentModificationException{
		//TODO handle Exceptions/unsuccessful removal in better way
		synchronized (contacts) {
			ContactsIterator iterator = getIterator();
			while(iterator.hasNext()){
				if(equals(iterator.next(),contact)){
					iterator.remove();
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * look for contact in Address book and return if found
	 * @param {@link Contact} use only Name and Last Name to Find Contact from Address Book
	 * @return {@link Contact} true if updated successfully otherwise return false.
	 */
	public Contact getContact(Contact contact) {	
		//TODO improve to accept only firstName and LastName
		synchronized (contacts) {
			ContactsIterator iterator = getIterator();
			while(iterator.hasNext()){
				Contact currentCon = iterator.next();
				if(equals(currentCon,contact)){
					return currentCon;
				}
			}
		}
		return null;
	}
	
	
	/**
	 * Remove existing contact from address book
	 * @param {@link Contact}
	 * @return {@link List<Contact>} .
	 */
	public List<Contact> getAllContacts() {
		return contacts;
	}
	
	
	private boolean exits(Contact contact){
		if(contacts.contains(contact)){
			return true;
		}
		return getContact(contact)!=null?true:false;
	}
	
	
	private boolean equals(Contact existingContact, Contact currentContact){
		if(existingContact.getName().equals(currentContact.getName()) &&
				existingContact.getlName().equals(currentContact.getlName())){
			return true;
		}
		return false;
	}
	
	private ContactsIterator getIterator(){
		return new ContactsIteratorImpl(contacts);
	}
}
