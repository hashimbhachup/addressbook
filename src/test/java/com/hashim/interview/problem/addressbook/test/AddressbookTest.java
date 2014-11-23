package com.hashim.interview.problem.addressbook.test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;
import org.jboss.resteasy.plugins.spring.SpringBeanProcessor;
import org.jboss.resteasy.plugins.spring.SpringResourceFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hashim.interview.problem.addressbook.core.Address;
import com.hashim.interview.problem.addressbook.core.AddressBookResponse;
import com.hashim.interview.problem.addressbook.core.Contact;
import com.hashim.interview.problem.addressbook.core.ContactInfo;
import com.hashim.interview.problem.addressbook.exception.MethodNotAllowedExceptionHandler;
import com.hashim.interview.problem.addressbook.exception.ResourceNotFoundException;
import com.hashim.interview.problem.addressbook.resource.AddressBookResImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:service-app-context.xml"})
public class AddressbookTest {

	private static  TJWSEmbeddedJaxrsServer server;
	private static final String END_POINT  = "addressBookService";
	private static final String APPLICATION_CONTEXT = "classpath:service-app-context.xml";
	public static final String SRV_URI = "http://localhost:3039/addressbook";
	private Client client = ClientBuilder.newClient();
	private Response response;
	
	@Autowired
	private Contact contact;
	
	 @BeforeClass
	    public static void beforeClass() throws Exception {
	        server = new TJWSEmbeddedJaxrsServer();
	        server.setPort(3039);
	        server.start();
	 
	        Dispatcher dispatcher = server.getDeployment().getDispatcher();
	 
	        SpringBeanProcessor processor = new SpringBeanProcessor(dispatcher, null, null);
	        ConfigurableApplicationContext factory = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT);
	        factory.addBeanFactoryPostProcessor(processor);
	 
	        SpringResourceFactory springResourceFactory = new SpringResourceFactory(END_POINT, factory,
	                                                                     AddressBookResImpl.class);
	        dispatcher.getRegistry().addResourceFactory(springResourceFactory);
	        dispatcher.getProviderFactory().registerProvider(MethodNotAllowedExceptionHandler.class);
	        dispatcher.getProviderFactory().registerProvider(ResourceNotFoundException.class);
	    }

	    @AfterClass
	    public static void afterClass() throws Exception {
	        server.stop();
	 
	    }
	
	@Before
	public void setup(){
		ContactInfo contactInfo = new ContactInfo();
		Address address = new Address();
		contactInfo.setEmail("test@gmail.com");
		contactInfo.setPhoneNumber("123-454-34543");	
		address.setCity("torrace");
		address.setCountry("US");
		contact.setName("Hashim1");
		contact.setlName("Bhachu");
		contact.setAddress(address);
		contact.setContactInfo(contactInfo);
		
	}

	/**
	 * Running all the test cases in a sequence
	 */
	@Test
	public void controlSequence(){
		AddressBookResponse addressBookResponse;
		
		// insert new contact in address book
		
		insert();
		addressBookResponse = response.readEntity(AddressBookResponse.class);
		assertEquals(Status.CREATED, addressBookResponse.getCode());
		response.close();
		
		//Duplicate entry test
		
		duplicateUserTest();
//		addressBookResponse = response.readEntity(AddressBookResponse.class);
//		assertEquals(Status.CONFLICT, addressBookResponse.getCode());
		response.close();
		
		
		//Getting all contacs
		
		getContacts();
//		addressBookResponse = response.readEntity(AddressBookResponse.class);
//		assertEquals(Status.OK, addressBookResponse.getCode());
//		assertEquals(1,addressBookResponse.getContacts().size());
		String jsonResponse = response.readEntity(String.class);
		System.out.println(jsonResponse);
		response.close();
		
		//Getting single contact
		
		getContact("hashim1","bhachu");
		addressBookResponse = response.readEntity(AddressBookResponse.class);
		assertEquals(Status.OK , addressBookResponse.getCode());
		assertEquals(1,addressBookResponse.getContacts().size());
		response.close();
		
		//Getting contact which does not exist
		
		getContact("testingNullContact", "testingNullContact");
		addressBookResponse = response.readEntity(AddressBookResponse.class);
		assertEquals(Status.NOT_FOUND , addressBookResponse.getCode());
		assertEquals(null,addressBookResponse.getContacts());
		response.close();
		
		//updating contact
		
		Contact updatedCon = update();
		addressBookResponse = response.readEntity(AddressBookResponse.class);
		assertEquals(Status.OK, addressBookResponse.getCode());	
		response.close();
		
		//Making sure address has been updated successfully
		
		getContact(updatedCon.getName(),updatedCon.getlName());
		addressBookResponse = response.readEntity(AddressBookResponse.class);
		Contact pulledContact = addressBookResponse.getContacts().get(0);
		assertEquals(updatedCon.getAddress().getCity(), pulledContact.getAddress().getCity());
		assertEquals(updatedCon.getAddress().getStreetNumber(), pulledContact.getAddress().getStreetNumber());	
		response.close();
		
		//deleteing a contact
		
		delete(updatedCon.getName(),updatedCon.getlName());
		addressBookResponse = response.readEntity(AddressBookResponse.class);
		assertEquals(Status.OK , addressBookResponse.getCode());
		response.close();
		
		//verifing contact has been deleted
		
		getContact(updatedCon.getName(), updatedCon.getlName());
		addressBookResponse = response.readEntity(AddressBookResponse.class);
		assertEquals(Status.NOT_FOUND , addressBookResponse.getCode());
		assertEquals(null,addressBookResponse.getContacts());
		response.close();
		
	}
	
	@Test
	public void resourceNotFoundTest(){
		response =  client.target(SRV_URI + "/contactse/")
				.request(MediaType.APPLICATION_JSON).post(Entity.json(contact));
		AddressBookResponse addressBookResponse = response.readEntity(AddressBookResponse.class);
		assertEquals(Status.NOT_FOUND, addressBookResponse.getCode());
		response.close();
	}
	
	
	// Method not found exception test 405
	@Test
	public void methodNotFoundTest(){
		response =  client.target(SRV_URI + "/contacts/")
				.request(MediaType.APPLICATION_JSON).post(Entity.json(contact));
		AddressBookResponse addressBookResponse = response.readEntity(AddressBookResponse.class);
		assertEquals(Status.METHOD_NOT_ALLOWED, addressBookResponse.getCode());
		response.close();
	}
	
	@Test
	public void XmlRequestTest(){
		String xmlRequest = "<contact>"
									+ "<lname>bhachu</lname>"
									+ "<address>"
										+ "<city>torrace</city>"
										+ "<country>US</country>"
										+ "<streetNumber>0</streetNumber>"
									+ "</address>"
									+ "<contactInfo>"
									+ "<email>test@gmail.com</email>"
									+ "<phoneNumber>123-454-34543</phoneNumber>"
									+ "</contactInfo>"
							+ "</contact>";
		response =  client.target(SRV_URI + "/contact/hashim2")
				.request(MediaType.APPLICATION_XML).post(Entity.xml(xmlRequest));
		AddressBookResponse addressBookResponse = response.readEntity(AddressBookResponse.class);
		assertEquals(Status.CREATED, addressBookResponse.getCode());
	}
	
	private void insert() {
		response =  client.target(SRV_URI + "/contact/" + contact.getName())
				.request(MediaType.APPLICATION_JSON).post(Entity.json(contact));	
	}
	
	private void duplicateUserTest() {
		response = client.target(SRV_URI +"/contact/" + contact.getName() )
				.request(MediaType.APPLICATION_JSON).post(Entity.json(contact));
	}
	
	
	//returns XML response
	private void getContacts(){
		response = client.target(SRV_URI +"/contacts")
				.request().get();
	}
		
	private void getContact(String name, String lname){
		response = client.target(SRV_URI +"/contact").queryParam("name", name).
				queryParam("lname", lname).request().get();
	}
	
	private Contact update() {
		Contact updateCon = contact;
		Address address = new Address();
		address.setCity("denver");
		address.setCountry("US");
		address.setStreetNumber(100);
		updateCon.setAddress(address);
		
		response = client.target(SRV_URI +"/contact/" + contact.getName() )
				.request(MediaType.APPLICATION_JSON).put(Entity.json(updateCon));
		return updateCon;
	}
	
	private void delete(String name, String lname){
		response = client.target(SRV_URI +"/contact/"  + name ).queryParam("lname", lname)
				.request(MediaType.APPLICATION_JSON).delete();	
	}
}
