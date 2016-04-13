package com.ILoveLAMP.ejb.test;


import static org.junit.Assert.*;

import org.junit.Test;

import com.ILoveLAMP.entities.User;

public class UserTest {

	@Test
	public void testGetPassword() {
		
		User testobj = new User();
		
		testobj.setPassword("password");
		
		assertTrue(testobj.getPassword().equals("password"));
		assertFalse(testobj.getPassword().equals("Mypassword"));
	}
	@Test
	public void testGetUsername() {
		
		User testobj = new User();
		
		testobj.setUsername("Emer");
		assertTrue(testobj.getUsername().equals("Emer"));
		assertFalse(testobj.getUsername().equals("Yang"));
	}
	
	@Test
	public void testSetUserType() {
		
		User testobj = new User();
		
		testobj.setUsertype("admin");
	
		assertTrue(testobj.getUsertype().equals("admin"));
		assertFalse(testobj.getUsertype().equals("Customer"));
	}
	
	@Test
	public void testGetID() {
		
		User testobj = new User();
		
		testobj.setId(1);
	
		assertEquals(1,testobj.getId(),0.01);
		//assertNotEquals(2, testobj.getId(), 0.01);
	}
	
	@Test
	public void testConstruct1() {		
		User testobj = new User("Emer","password");	
		
		assertTrue(testobj.getUsername().equals("Emer"));
		assertFalse(testobj.getUsername().equals("Yang"));
		
		assertTrue(testobj.getPassword().equals("password"));
		assertFalse(testobj.getPassword().equals("Mypassword"));
		
		assertNull(testobj.getUsertype());
		assertNotNull(testobj.getId());
}
	@Test
	public void testConstruct2() {		
		User testobj = new User("Emer","password", "admin");	
		
		assertTrue(testobj.getUsername().equals("Emer"));
		assertFalse(testobj.getUsername().equals("Yang"));
		
		assertTrue(testobj.getPassword().equals("password"));
		assertFalse(testobj.getPassword().equals("Mypassword"));
		
		assertNotNull(testobj.getUsertype());
		
		assertTrue(testobj.getUsertype().equals("admin"));
		assertFalse(testobj.getUsertype().equals("Customer"));
}
	@Test
	public void testConstruct3() {		
		User testobj = new User("Emer","password", "admin", 1);	
		
		assertTrue(testobj.getUsername().equals("Emer"));
		assertFalse(testobj.getUsername().equals("Yang"));
		
		assertTrue(testobj.getPassword().equals("password"));
		assertFalse(testobj.getPassword().equals("Mypassword"));
		
		assertNotNull(testobj.getUsertype());
		
		assertTrue(testobj.getUsertype().equals("admin"));
		assertFalse(testobj.getUsertype().equals("Customer"));
}

}

