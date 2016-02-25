package com.ILoveLamp.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.ILoveLAMP.entities.Base_Data;
import com.ILoveLAMP.entities.Operator;

public class OperatorTest {

	@Test
	public void testSetOpId() {
		
		Operator testobj = new Operator();
		
		testobj.setOpId(1);
		
		assertTrue(testobj.getOpId()==1);
	}
	
	@Test
	public void testGetOpId() {
		
		Operator testobj = new Operator();
		
		testobj.setOpId(3);
		
		assertTrue(testobj.getOpId()==3);
	}
	

	@Test
	public void testSetMcc() {
		
		Operator testobj = new Operator();
		
		testobj.setMcc(340);
		
		assertTrue(testobj.getMcc()==340);
	}
	@Test
	public void testGetMcc() {
		
		Operator testobj = new Operator();
		
		testobj.setMcc(440);
		
		assertTrue(testobj.getMcc()==440);
	}
	
	@Test
	public void testSetMnc() {
		
		Operator testobj = new Operator();
		
		testobj.setMnc(230);
		
		assertTrue(testobj.getMnc()==230);
	}
	@Test
	public void testGetMnc() {
		
		Operator testobj = new Operator();
		
		testobj.setMnc(250);
		
		assertTrue(testobj.getMnc()==250);
	}
	@Test
	public void testSetCountry() {
		
		Operator testobj = new Operator();
		
		testobj.setCountry("Ireland");
		
		assertTrue(testobj.getCountry()=="Ireland");
	}
	@Test
	public void testGetCountry() {
		
		Operator testobj = new Operator();
		
		testobj.setCountry("Poland");
		
		assertTrue(testobj.getCountry()=="Poland");
	}
	
	@Test
	public void testSetOperatorName() {
		
		Operator testobj = new Operator();
		
		testobj.setOperatorName("Samsung");
		
		assertTrue(testobj.getOperatorName()=="Samsung");
	}
	@Test
	public void testGetOperatorName() {
		
		Operator testobj = new Operator();
		
		testobj.setOperatorName("Vodafone");
		
		assertTrue(testobj.getOperatorName()=="Vodafone");
	}
	
	

}
