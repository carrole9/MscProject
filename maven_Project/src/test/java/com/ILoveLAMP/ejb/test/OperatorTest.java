package com.ILoveLAMP.ejb.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.ILoveLAMP.entities.Base_Data;
import com.ILoveLAMP.entities.Operator;

public class OperatorTest {

	@Test
	public void testSetOpId() {
		
		Operator testobj = new Operator();		
		testobj.setOpId(1);		
		assertEquals(1,testobj.getOpId(),0.01);
	}
	
	@Test
	public void testGetOpId() {
		
		Operator testobj = new Operator();
		
		testobj.setOpId(3);
		
		assertEquals(3,testobj.getOpId(), 0.01);
	}
	

	@Test
	public void testSetMcc() {
		
		Operator testobj = new Operator();
		
		testobj.setMcc(340);
		
		assertEquals(340,testobj.getMcc(),0.01);
	}
	@Test
	public void testGetMcc() {
		
		Operator testobj = new Operator();
		
		testobj.setMcc(440);
		
		assertEquals(440,testobj.getMcc(),0.01);
	}
	
	@Test
	public void testSetMnc() {
		
		Operator testobj = new Operator();
		
		testobj.setMnc(230);
		
		assertEquals(230,testobj.getMnc(),0.01);
	}
	@Test
	public void testGetMnc() {
		
		Operator testobj = new Operator();
		
		testobj.setMnc(250);
		
		assertEquals(250,testobj.getMnc(),0.01);
	}
	@Test
	public void testSetCountry() {
		
		Operator testobj = new Operator();
		
		testobj.setCountry("Ireland");
		
		assertEquals("Ireland",testobj.getCountry());
	}
	@Test
	public void testGetCountry() {
		
		Operator testobj = new Operator();
		
		testobj.setCountry("Poland");
		
		assertEquals("Poland", testobj.getCountry());
	}
	
	@Test
	public void testSetOperatorName() {
		
		Operator testobj = new Operator();
		
		testobj.setOperatorName("Samsung");
		
		assertEquals("Samsung",testobj.getOperatorName());
	}
	@Test
	public void testGetOperatorName() {
		
		Operator testobj = new Operator();
		
		testobj.setOperatorName("Vodafone");
		
		assertEquals("Vodafone",testobj.getOperatorName());
	}
	
	

}
