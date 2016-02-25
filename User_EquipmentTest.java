package com.ILoveLamp.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ILoveLAMP.entities.User_Equipment;

public class User_EquipmentTest {


	@Test
	public void testSetUserEquipmentId() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setUserEquipmentId(1);
	
		assertTrue(testobj.getUserEquipmentId()==1);
	}
	@Test
	public void testGetUserEquipmentId() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setUserEquipmentId(2);
	
		assertTrue(testobj.getUserEquipmentId()==2);
	}
	
	@Test
	public void testSetMarketingName() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setMarketingName("Iphone");
	
		assertTrue(testobj.getMarketingName()=="Iphone");
	}
	@Test
	public void testGetMarketingName() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setMarketingName("S6");
	
		assertTrue(testobj.getMarketingName()=="S6");
	}
	
	@Test
	public void testSetManufacturer() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setManufacturer("Apple");
	
		assertTrue(testobj.getManufacturer()=="Apple");
	}
	@Test
	public void testGetManufacturer() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setManufacturer("Samsung");
	
		assertTrue(testobj.getManufacturer()=="Samsung");
	}
	
	@Test
	public void testSetAccessCapability() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setAccessCapability("GSM800");
	
		assertTrue(testobj.getAccessCapability()=="GSM800");
	}
	@Test
	public void testGetAccessCapability() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setAccessCapability("GSM900");
		
		assertTrue(testobj.getAccessCapability()=="GSM900");
	}
	@Test
	public void testSetModel() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setModel("Galaxy");
	
		assertTrue(testobj.getModel()=="Galaxy");
	}
	@Test
	public void testGetModel() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setModel("MX8");
	
		assertTrue(testobj.getModel()=="MX8");
	}
	@Test
	public void testSetVendorName() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setVendorName("Vodafone");
	
		assertTrue(testobj.getModel()=="Vodafone");
	}
	@Test
	public void testGetVendorName() {
		
		User_Equipment testobj = new User_Equipment();
		

		testobj.setVendorName("Huawei");
	
		assertTrue(testobj.getModel()=="Huawei");
	}
	@Test
	public void testSetUeType() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setUeType("Handheld");
	
		assertTrue(testobj.getUeType()=="Handheld");
	}
	@Test
	public void testGetUeType() {
		
		User_Equipment testobj = new User_Equipment();
		

		testobj.setUeType("Handheld");
		
		assertTrue(testobj.getUeType()=="Handheld");
	}
	@Test
	public void testSetOs() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setOs("ios");
	
		assertTrue(testobj.getOs()=="ios");
	}
	@Test
	public void testGetOs() {
		
		User_Equipment testobj = new User_Equipment();
		

		testobj.setOs("windows");
		
		assertTrue(testobj.getOs()=="windows");
	}
	
	@Test
	public void testSetInputMode() {
		
		User_Equipment testobj = new User_Equipment();
		
		testobj.setInputMode("QWERTY");
	
		assertTrue(testobj.getInputMode()=="QWERTY");
	}
	@Test
	public void testGetInputMode() {
		
		User_Equipment testobj = new User_Equipment();
		

		testobj.setInputMode("QWERTY");
		
		assertTrue(testobj.getInputMode()=="QWERTY");
	}
	
	

}
