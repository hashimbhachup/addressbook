package com.hashim.interview.problem.addressbook.exception;

public abstract class AddressBookException extends Exception{
	
	public AddressBookException(){
		super();
	}
	
	public AddressBookException(String message){
		super(message);
	}
	
	public AddressBookException(String message, Throwable throwable) {
		super(message, throwable);   
	}
	
}
