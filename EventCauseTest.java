package com.ILoveLamp.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.ILoveLAMP.entities.Base_Data;
import com.ILoveLAMP.entities.Event_Cause;

public class EventCauseTest {

	@Test
	public void testSetEcId() {
		
		Event_Cause testobj = new Event_Cause();
		
		testobj.setEcId(2);
		
		assertTrue(testobj.getEcId()==2);
	}
	@Test
	public void testGetEcId() {
		
		Event_Cause testobj = new Event_Cause();
		
		testobj.setEcId(3);
		
		assertTrue(testobj.getEcId()==3);
	}

	@Test
	public void testSetCauseCode() {
		
		Event_Cause testobj = new Event_Cause();
		
		testobj.setCauseCode(1);
		
		assertTrue(testobj.getCauseCode()==1);
	}
	
	@Test
	public void testGetCauseCode() {
		
		Event_Cause testobj = new Event_Cause();
		
		testobj.setCauseCode(3);
		
		assertTrue(testobj.getCauseCode()==3);
	}
	
	@Test
	public void testSetEventId() {
		
		Event_Cause testobj = new Event_Cause();
		
		testobj.setEventId(5);
		
		assertTrue(testobj.getEventId()==5);
	}
	@Test
	public void testGetEventId() {
		
		Event_Cause testobj = new Event_Cause();
		
		testobj.setEventId(6);
		
		assertTrue(testobj.getEventId()==6);
	}
	
	@Test
	public void testSetDescription() {
		
		Event_Cause testobj = new Event_Cause();
		
		testobj.setDescription("test");
		
		assertTrue(testobj.getDescription()=="test");
	}
	@Test
	public void testGetDescription() {
		
		Event_Cause testobj = new Event_Cause();
		
		testobj.setDescription("testget");
		
		assertTrue(testobj.getDescription()=="testget");
	}
	/*@Test
	public void testGetList() {
		
		List<Base_Data>  actual =new List<Base_Data>();
		
		testobj.setDescription("testget");
		
		assertTrue(testobj.getDescription()=="testget");
	}*/
	
	
	
}
