package com.hashim.interview.problem.addressbook.exception;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.hashim.interview.problem.addressbook.core.AddressBookResponse;

@Provider
public class MethodNotAllowedExceptionHandler implements ExceptionMapper<NotAllowedException>{

	public Response toResponse(NotAllowedException exception) {
		AddressBookResponse addressBookResponse = new AddressBookResponse();
		addressBookResponse.setCode(Status.METHOD_NOT_ALLOWED);
		addressBookResponse.setMessage("Method not allowed");
		return Response.ok().entity(addressBookResponse).type(MediaType.APPLICATION_JSON).build();
	}

}
