package com.hashim.interview.problem.addressbook.exception;

public class DuplicateRecordException extends AddressBookException{

	public DuplicateRecordException(String user){
		super("record for " + user  +" already exist");
	}
}
