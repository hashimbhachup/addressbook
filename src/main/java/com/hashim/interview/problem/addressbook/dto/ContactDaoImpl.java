package com.hashim.interview.problem.addressbook.dto;

import java.util.List;

import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.hashim.interview.problem.addressbook.core.Contact;

@Named("contactDao")
public class ContactDaoImpl implements ContactDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void insert(Contact contact) {
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(contact);
        tx.commit();
        session.close();
	}

	public boolean remove(Contact contact) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeByFNameAndLName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update(Contact contact) {
		// TODO Auto-generated method stub
		return false;
	}

	public Contact getByFNameAndLName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Contact> getAllContacts() {
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Contact> contacts = session.createCriteria(Contact.class).list();
        tx.commit();
        session.close();
		return contacts;
	}

	public List<Contact> getlAllContacts(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

}
