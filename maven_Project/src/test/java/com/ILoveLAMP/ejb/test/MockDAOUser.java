package com.ILoveLAMP.ejb.test;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import com.ILoveLAMP.dao.jpa.JPAUserDAO;
import com.ILoveLAMP.entities.User;
import com.ILoveLAMP.services.users.UserService;



public class MockDAOUser {
	
	private UserService eventEJB;
	
	@Before
	public void setup(){
	
		User user = new User("Emer","password","admin", 1);
		JPAUserDAO mockedDAO = mock(JPAUserDAO.class);
		when(mockedDAO.getUserById(anyInt())).thenReturn(user);
	//	when(((OngoingStubbing<Base_Data>) mockedDAO.getBaseDataById(0)).thenReturn(mockedEvents);
		
		eventEJB = new com.ILoveLAMP.services.users.UserServiceEJB();
//		eventEJB.setDao(mockedDAO);
		
	}

	@Test
	public void test() {
//		User results = eventEJB.getUserById(5);
//				
//		assertTrue(results.getPassword().equals("password"));
//		assertFalse(results.getPassword().equals("Mypassword"));
//		
//		assertTrue(results.getUsername().equals("Emer"));
//		assertFalse(results.getUsername().equals("Yang"));
//		
//		assertTrue(results.getUsertype().equals("admin"));
//		assertFalse(results.getUsertype().equals("Customer"));
//		
//		assertTrue(results.getId()==1);
//		assertFalse(results.getId()==2);
//		
//		assertNotNull(results.getPassword());
//		assertNotNull(results.getUsername());
//		assertNotNull(results.getUsertype());
//		assertNotNull(results.getId());
		
		
	
		
	}

}
