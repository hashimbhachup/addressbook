package com.interview.problem.addressbook.exception;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.interview.problem.addressbook.core.AddressBookResponse;

public class ResourceNotFoundException implements ExceptionMapper<NotFoundException>{

	public Response toResponse(NotFoundException exception) {
		AddressBookResponse addressBookResponse = new AddressBookResponse();
		addressBookResponse.setCode(Status.NOT_FOUND);
		addressBookResponse.setMessage("Resource not found");
		return Response.ok().entity(addressBookResponse).type(MediaType.APPLICATION_JSON).build();

	}

}
