package com.ILoveLAMP.ejb.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ILoveLAMP.entities.Error_Data;

public class Error_DataTest {

	Error_Data data = new Error_Data();
	Error_Data data1 = new Error_Data(1, "11/01/2013  05:15:00 PM", "4098", "1", "21060800", "340", "930", "4", "1000", "1", "12A", "344930000000011", "4809532081614990000", "8226896360947470000", "1150444940909480000");

	@Test
	public void testDateTime() {
		data.setDateTime("11/01/2013  05:15:00 PM");
		assertEquals("11/01/2013  05:15:00 PM", data.getDateTime());
		assertFalse(data.getDateTime().equals("11/01/2013  05:15:00 AM"));
		assertEquals("11/01/2013  05:15:00 PM", data1.getDateTime());
		assertFalse(data1.getDateTime().equals("11/01/2013  05:15:00 AM"));
	}

	@Test
	public void testEventId() {
		data.setEventId("4098");
		assertEquals("4098", data.getEventId());
		assertFalse(data.getEventId().equals("4099"));
		assertEquals("4098", data1.getEventId());
		assertFalse(data1.getEventId().equals("4099"));
	}

	@Test
	public void testFailureId() {
		data.setFailureId("1");
		assertEquals("1", data.getFailureId());
		assertFalse(data.getFailureId().equals("(null)"));
		assertEquals("1", data1.getFailureId());
		assertFalse(data1.getFailureId().equals("(null)"));
	}

	@Test
	public void testUeType() {
		data.setUeType("21060800");
		assertEquals("21060800", data.getUeType());
		assertFalse(data.getUeType().equals("4099"));
		assertEquals("21060800", data1.getUeType());
		assertFalse(data1.getUeType().equals("4099"));
	}

	@Test
	public void testMarket() {
		data.setMarket("344");
		assertEquals("344", data.getMarket());
		assertFalse(data.getMarket().equals("4099"));
		assertEquals("340", data1.getMarket());
		assertFalse(data1.getMarket().equals("4099"));
	}

	@Test
	public void testOperator() {
		data.setOperator("930");
		assertEquals("930", data.getOperator());
		assertFalse(data.getOperator().equals("4099"));
		assertEquals("930", data1.getOperator());
		assertFalse(data1.getOperator().equals("4099"));
	}

	@Test
	public void testCellId() {
		data.setCellId("4");
		assertEquals("4", data.getCellId());
		assertFalse(data.getCellId().equals("4099"));
		assertEquals("4", data1.getCellId());
		assertFalse(data1.getCellId().equals("4099"));
	}

	@Test
	public void testDuration() {
		data.setDuration("1000");
		assertEquals("1000", data.getDuration());
		assertFalse(data.getDuration().equals("4099"));
		assertEquals("1000", data1.getDuration());
		assertFalse(data1.getDuration().equals("4099"));
	}

	@Test
	public void testCause_Code() {
		data.setCause_Code("0");
		assertEquals("0", data.getCause_Code());
		assertFalse(data.getCause_Code().equals("4099"));
		assertEquals("1", data1.getCause_Code());
		assertFalse(data1.getCause_Code().equals("4099"));
	}

	@Test
	public void testNeVersion() {
		data.setNeVersion("11B");
		assertEquals("11B", data.getNeVersion());
		assertFalse(data.getNeVersion().equals("4099"));
		assertEquals("12A", data1.getNeVersion());
		assertFalse(data1.getNeVersion().equals("4099"));
	}

	@Test
	public void testImsi() {
		data.setImsi("344930000000011");
		assertEquals("344930000000011", data.getImsi());
		assertFalse(data.getImsi().equals("4099"));
		assertEquals("344930000000011", data1.getImsi());
		assertFalse(data1.getImsi().equals("4099"));
	}

	@Test
	public void testHier3_Id() {
		data.setHier3_Id("4809532081614990000");;
		assertEquals("4809532081614990000", data.getHier3_Id());
		assertFalse(data.getHier3_Id().equals("4099"));
		assertEquals("4809532081614990000", data1.getHier3_Id());
		assertFalse(data1.getHier3_Id().equals("4099"));
	}

	@Test
	public void testHier32_Id() {
		data.setHier32_Id("8226896360947470000");;
		assertEquals("8226896360947470000", data.getHier32_Id());
		assertFalse(data.getHier32_Id().equals("4099"));
		assertEquals("8226896360947470000", data1.getHier32_Id());
		assertFalse(data1.getHier32_Id().equals("4099"));
	}

	@Test
	public void testHier321_Id() {
		data.setHier321_Id("1150444940909480000");;
		assertEquals("1150444940909480000", data.getHier321_Id());
		assertFalse(data.getHier321_Id().equals("4099"));
		assertEquals("1150444940909480000", data1.getHier321_Id());
		assertFalse(data1.getHier321_Id().equals("4099"));
	}

	@Test
	public void testDataId(){
		data.setDataId(0);
		assertTrue(0 == data.getDataId());	
		assertFalse(1 == data.getDataId());
		
		assertTrue(1 == data1.getDataId());	
		assertFalse(0 == data1.getDataId());
	}

}
