package com.ILoveLAMP.ejb.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ILoveLAMP.entities.Failure;

public class FailureTest {

	@Test
	public void testSetFailureId() {
		
		Failure testobj = new Failure();
		
		testobj.setFailureId(2);
		
		assertTrue(testobj.getFailureId()==2);
	}
	@Test
	public void testGetFailureId() {
		
		Failure testobj = new Failure();
		
		testobj.setFailureId(5);
		
		assertTrue(testobj.getFailureId()==5);
	}
	
	@Test
	public void testSetDescription() {
		
		Failure testobj = new Failure();
		
		testobj.setDescription("test");
	
		assertTrue(testobj.getDescription()=="test");
	}
	
	@Test
	public void testGetDescription() {
		
		Failure testobj = new Failure();
		
		testobj.setDescription("test2");
	
		assertTrue(testobj.getDescription()=="test2");
	}

}
