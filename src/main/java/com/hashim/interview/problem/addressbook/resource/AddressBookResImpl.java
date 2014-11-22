package com.hashim.interview.problem.addressbook.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hashim.interview.problem.addressbook.core.AddressBookResponse;
import com.hashim.interview.problem.addressbook.core.Contact;
import com.hashim.interview.problem.addressbook.data.Processor;


/**
 * RestFull service to access Address Book. This service provides endpoints to 
 * insert, update, get and delete contacts from Address Book
 *  @author hassingh
 */

//TODO Better Handling for http 405

@Service(value="addressBookService")
@Path("/addressbook")
public class AddressBookResImpl implements AddressBookResources{
	
	@Autowired
	private Processor processor;
	
	
	/**
	 * rest endpoint to insert new contact to address book
	 * returns 201 Created response if successfully created
	 * and returns 409 conflict if Name and Last Name already existing in contact list
	 */
	@POST
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/contact/{name}")
	public Response add(@PathParam("name") String name,Contact contact){
		AddressBookResponse addressBookResponse = new AddressBookResponse();
		
		
		contact.setName(name);
		if(processor.add(contact)){
			addressBookResponse.setCode(Status.CREATED);
			addressBookResponse.setMessage("Success");
		}else{
			addressBookResponse.setCode(Status.CONFLICT);
			addressBookResponse.setMessage("contact for " + contact.getName() 
					+ " "+ contact.getlName() + " already exists");
		}
		return Response.ok().entity(addressBookResponse).build();
	}
	
	
	/**
	 * rest endpoint to update exisitng contact to address book
	 * returns 200 response if successfully updated
	 * and returns 404 notfound if Name and Last Name does not existing in contact list
	 */
	@PUT
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/contact/{name}")
	public Response update(@PathParam("name") String name,Contact contact){
		contact.setName(name);
		AddressBookResponse addressBookResponse = new AddressBookResponse();
		if(processor.update(contact)){
			addressBookResponse.setCode(Status.OK);
			addressBookResponse.setMessage("Success");
			addressBookResponse.setMessage("Entery got successfully created");
		}else{
			addressBookResponse.setCode(Status.NOT_FOUND);
			addressBookResponse.setMessage("contact for " + contact.getName() 
					+ " "+ contact.getlName() + " does not exist");
		}
		
		return  Response.ok().entity(addressBookResponse).build();
		
	}
	
	/**
	 * End point to get all existing contacts
	 * returns all existing contacts and wrapped into {@link AddressBookResponse}
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/contacts")
	public Response getAll() {
		AddressBookResponse addressBookResponse = new AddressBookResponse();
		addressBookResponse.setContacts(processor.pullAllRecords());
		addressBookResponse.setCode(Status.OK);
		addressBookResponse.setMessage("Success");
		return Response.ok().entity(addressBookResponse).build();
	}
	
	
	/**
	 * Endpoint to get contact by first name and last specified in query parameter
	 *  {@link #get(FirstName, Last Name)}
	 *  returns 200 response with contact information 
	 *  and 404 'Not_Found if contact does not exisit.
	 *  response is wrapped in {@link #AddressBookResponse}
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/contact")
	public Response get(@QueryParam("name") String name,@QueryParam("lname") String lname){
		AddressBookResponse addressBookResponse = new AddressBookResponse();
		
		addressBookResponse = NPECheck(name, lname);
		if(addressBookResponse == null){
			addressBookResponse = new AddressBookResponse();
			Contact contact = processor.pullContact(name, lname);
			if(contact != null){
				List<Contact> contacts = new ArrayList<Contact>();
				contacts.add(contact);
				addressBookResponse.setContacts(contacts);
				addressBookResponse.setCode(Status.OK);
				addressBookResponse.setMessage("Success");
			}else{
				addressBookResponse.setCode(Status.NOT_FOUND);
				addressBookResponse.setMessage("contact for " + name
						+ " "+ lname + " does not exist");
			}
		}
		
		return  Response.ok().entity(addressBookResponse).build();
	}
	
	/**
	 * Endpoint "/contact/{firstName}" to delete specified contact
	 * takes firstname path variable and last name as query param
	 * returns 200 response if contact found and deleted successfully
	 * and 404 'Not_Found is contact does not exisit.
	 * response is wrapped in {@link #AddressBookResponse}
	 */
	@DELETE
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/contact/{name}")
	public Response delete(@PathParam("name") String name,@QueryParam("lname") String lname){
		AddressBookResponse addressBookResponse = null;
		addressBookResponse = NPECheck(name, lname);
		if(addressBookResponse == null){
			addressBookResponse = new AddressBookResponse();
			if(processor.delete(name, lname)){
				addressBookResponse.setCode(Status.OK);
				addressBookResponse.setMessage("Success");
			}else{
				addressBookResponse.setCode(Status.NOT_FOUND);
				addressBookResponse.setMessage("contact for " + name
						+ " "+ lname + " does not exist");
			}
		}
		return  Response.ok().entity(addressBookResponse).build();
	}
	
	
	//TODO do it through spoing validaion or AOP
	private AddressBookResponse NPECheck(String name, String lname){
		AddressBookResponse addressBookResponse = null;
		if(name == null || name .isEmpty()){
			addressBookResponse = new AddressBookResponse();
			addressBookResponse.setCode(Status.BAD_REQUEST);
			addressBookResponse.setMessage("name cannot be '"+ name +"'");
		}else if (lname == null || lname .isEmpty()){
			addressBookResponse = new AddressBookResponse();
			addressBookResponse.setCode(Status.BAD_REQUEST);
			addressBookResponse.setMessage("name cannot be '"+ name +"'");
		}
		return addressBookResponse;
	}
}
