package com.ILoveLAMP.ejb.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ILoveLAMP.entities.User_Equipment;

public class User_EquipmentTest {


	@Test
	public void testSetUserEquipmentId() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setUserEquipmentId(1);
	
		assertEquals(1, testobj.getUserEquipmentId(), 0.01);
	}
	@Test
	public void testGetUserEquipment() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setUserEquipmentId(2);
	
		assertEquals(2,testobj.getUserEquipmentId(),0.01);
	}
	
	@Test
	public void testSetMarketingName() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setMarketingName("Iphone");
	
		assertEquals("Iphone",testobj.getMarketingName());
	}
	@Test
	public void testGetMarketingName() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setMarketingName("S6");
	
		assertTrue(testobj.getMarketingName().equals("S6"));
	}
	
	@Test
	public void testSetManufacturer() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setManufacturer("Apple");
	
		assertTrue(testobj.getManufacturer().equals("Apple"));
	}
	@Test
	public void testGetManufacturer() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setManufacturer("Samsung");
	
		assertTrue(testobj.getManufacturer().equals("Samsung"));
	}
	
	@Test
	public void testSetAccessCapability() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setAccessCapability("GSM800");
	
		assertTrue(testobj.getAccessCapability().equals("GSM800"));
	}
	@Test
	public void testGetAccessCapability() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setAccessCapability("GSM900");
		
		assertTrue(testobj.getAccessCapability().equals("GSM900"));
	}
	@Test
	public void testSetModel() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setModel("Galaxy");
	
		assertTrue(testobj.getModel().equals("Galaxy"));
	}
	@Test
	public void testGetModel() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setModel("MX8");
	
		assertTrue(testobj.getModel().equals("MX8"));
	}
	@Test
	public void testSetVendorName() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setVendorName("Vodafone");
	
		assertTrue(testobj.getVendorName().equals("Vodafone"));
	}
	@Test
	public void testGetVendorName() {
		
		User_Equipment testobj = new User_Equipment();
		

		testobj.setVendorName("Huawei");
	
		assertTrue(testobj.getVendorName().equals("Huawei"));
	}
	@Test
	public void testSetUeType() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setUeType("Handheld");
	
		assertTrue(testobj.getUeType().equals("Handheld"));
	}
	@Test
	public void testGetUeType() {
		
		User_Equipment testobj = new User_Equipment();
		

		testobj.setUeType("Handheld");
		
		assertTrue(testobj.getUeType().equals("Handheld"));
	}
	@Test
	public void testSetOs() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setOs("ios");
	
		assertTrue(testobj.getOs().equals("ios"));
	}
	@Test
	public void testGetOs() {
		
		User_Equipment testobj = new User_Equipment();
		

		testobj.setOs("windows");
		
		assertTrue(testobj.getOs().equals("windows"));
	}
	
	@Test
	public void testSetInputMode() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setInputMode("QWERTY");
	
		assertTrue(testobj.getInputMode().equals("QWERTY"));
	}
	@Test
	public void testGetInputMode() {
		
		User_Equipment testobj = new User_Equipment();
		

		testobj.setInputMode("QWERTY");
		
		assertTrue(testobj.getInputMode().equals("QWERTY"));
	}
	
	

}
