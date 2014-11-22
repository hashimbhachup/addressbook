package com.hashim.interview.problem.addressbook.core;

import java.util.ConcurrentModificationException;
import java.util.List;


public class ContactsIteratorImpl implements ContactsIterator{
	
	 int position;      
     int lastRet = -1; 

 	public List<Contact> contacts;
 	
 	public ContactsIteratorImpl(List<Contact> contacts){
 		this.contacts = contacts;
 		position = 0;
 	}
     
     public boolean hasNext() {
         return position < contacts.size();
     }

     public Contact next() {
         Contact contact = contacts.get(position);
         lastRet = position;
         position++;
         return contact;
    }

     public void remove() throws IllegalStateException, ConcurrentModificationException{
         if (lastRet < 0)
             throw new IllegalStateException();

         try {
        	 contacts.remove(lastRet);
             position = lastRet;
             lastRet = -1;
         } catch (IndexOutOfBoundsException ex) {
             throw new ConcurrentModificationException();
         }
     }

     public void update(Contact contact) throws IllegalStateException, ConcurrentModificationException{
    	 if (lastRet < 0)
             throw new IllegalStateException();

         try {
        	 contacts.set(lastRet,contact);
         } catch (IndexOutOfBoundsException ex) {
             throw new ConcurrentModificationException();
         }
     }

}
