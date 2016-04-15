package com.ILoveLAMP.ejb.test;



import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.ILoveLAMP.dao.jpa.JPAUserDAO;
import com.ILoveLAMP.entities.User;



public class MockUserService  {
	
	private com.ILoveLAMP.services.users.UserServiceEJB eventEJB;
	
	@Before
	public void setup(){
		ArrayList<User> mockedEvents = new ArrayList<User>();


		User testobj = new User();
		testobj.setPassword("password");
		testobj.setUsername("Emer");
		testobj.setUsertype("admin");
		
		@SuppressWarnings("unused")
		User invalidUser = new User("", "", "");
		
		mockedEvents.add(new User("Emer","password","admin", 1));
		mockedEvents.add(testobj);
		mockedEvents.add(new User());
		//mockedEvents.add(invalidUser);
		JPAUserDAO mockedDAO = mock(JPAUserDAO.class);
		when(mockedDAO.getAllUsers()).thenReturn(mockedEvents);
		when(mockedDAO.getUserByType("")).thenReturn(mockedEvents);
		
		eventEJB = new com.ILoveLAMP.services.users.UserServiceEJB();
		eventEJB.setDao(mockedDAO);
		
	}

	@Test
	public void testgetAllUsers() {
		ArrayList<User> results = (ArrayList<User>) eventEJB.getAllUsers();
		User resultEvent = results.get(0);
		assertEquals(3, results.size() ,0.01);
        assertNotNull(resultEvent);
		assertEquals(resultEvent.getId(), 1);
		assertEquals(resultEvent.getUsername(), "Emer");
		assertEquals(resultEvent.getPassword(), "password");
		assertEquals(resultEvent.getUsertype(), "admin");

		
		User testobj = results.get(1);
		assertTrue(testobj.getUsertype().equals("admin"));
		assertFalse(testobj.getUsertype().equals("Customer"));
		assertTrue(testobj.getUsername().equals("Emer"));
		assertFalse(testobj.getUsername().equals("Yang"));
		assertTrue(testobj.getPassword().equals("password"));
		assertFalse(testobj.getPassword().equals("Mypassword"));
		
		User invalidUser = new User("", "", "");
		eventEJB.addToUsers(invalidUser);
		results= (ArrayList<User>)eventEJB.getAllUsers();
		assertEquals(3, results.size());
		
		User valid = new User("Micheal", "password", "customer");
		eventEJB.addToUsers(valid);
		results= (ArrayList<User>)eventEJB.getAllUsers();
		assertEquals(3, results.size());	
		
		User test = results.get(2);
		assertNull(test.getUsertype());
		assertNull(test.getUsername());
		assertNull(test.getPassword());
	}
	
	@Test
	public void testgetUsersByType(){
		ArrayList<User> results = (ArrayList<User>) eventEJB.getUserByType("");
		User result1 = results.get(0);
		assertEquals(result1.getUsertype(), "admin");
		
		User result2 = results.get(1);
		assertEquals(result2.getUsertype(), "admin");
		
		User test = results.get(2);
		assertNull(test.getUsertype());
		assertNull(test.getUsername());
		assertNull(test.getPassword());
		
		
		
		
	}

}