package com.interview.problem.addressbook.core;

import java.util.Iterator;

public interface ContactsIterator extends Iterator<Contact> {
	public void update(Contact contact);
}
