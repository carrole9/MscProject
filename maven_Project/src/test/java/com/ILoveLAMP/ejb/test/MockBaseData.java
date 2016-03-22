package com.ILoveLAMP.ejb.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.ILoveLAMP.dao.jpa.JPABase_DataDAO;
import com.ILoveLAMP.entities.Base_Data;
import com.ILoveLAMP.entities.Event_Cause;
import com.ILoveLAMP.entities.Failure;
import com.ILoveLAMP.entities.Operator;
import com.ILoveLAMP.entities.User_Equipment;
import com.ILoveLAMP.services.Base_DataServiceEJB;

public class MockBaseData {
	
private Base_DataServiceEJB eventEJB;

	@SuppressWarnings("deprecation")
	Date date = new Date(2013, 11, 11, 11, 11, 11);
	
	@Before
	public void setup(){
		ArrayList<Base_Data> mockedEvents = new ArrayList<Base_Data>();
		Failure failure1 = new Failure(1, "test failure");
		Event_Cause ec1 = new Event_Cause(1, 1, 1, "test event cause");
		Operator op1 = new Operator(1, 1, 1, "ireland", "operatorName");
		User_Equipment ue1 = new User_Equipment(1, "marketingName", "manufacturer", "accessCapability", "model",
				"vendorName", "ueType", "os", "inputMode");

		@SuppressWarnings("unused")
		Failure failure2 = new Failure(2, "test failure_2");
		@SuppressWarnings("unused")
		Event_Cause ec2 = new Event_Cause(2, 2, 2, "test event cause_2");
		@SuppressWarnings("unused")
		Operator op2 = new Operator(2, 2, 2, "england", "operatorName_2");
		@SuppressWarnings("unused")
		User_Equipment ue2 = new User_Equipment(2, "marketingName_2", "manufacturer_2", "accessCapability_2", "model_2",
				"vendorName_2", "ueType_2", "os_2", "inputMode_2");

		mockedEvents.add(new Base_Data(1, date, 1, 1, "neVersion", "imsi", "h3id", "h32id", "h321id", failure1, ec1,
				op1, ue1));
		mockedEvents.add(new Base_Data());
		JPABase_DataDAO mockedDAO = mock(JPABase_DataDAO.class);
		when(mockedDAO.getAllBaseDatas()).thenReturn(mockedEvents);
		
		eventEJB = new Base_DataServiceEJB();
		eventEJB.setDao(mockedDAO);
		
	}

	@Test
	public void test() {
		ArrayList<Base_Data> results = (ArrayList<Base_Data>) eventEJB.getAllBaseDatas();
		Base_Data resultEvent = results.get(0);
		assertEquals(2,results.size(),0.01);
		assertEquals(1, resultEvent.getDataId(), 0.01);
		assertEquals(1, resultEvent.getCellId(), 0.01);
		assertEquals(1, resultEvent.getDuration(), 0.01);
		assertTrue(resultEvent.getNeVersion().equals("neVersion"));
		assertTrue(resultEvent.getImsi().equals("imsi"));
		assertTrue(resultEvent.getHier3Id().equals("h3id"));
		assertTrue(resultEvent.getHier32Id().equals("h32id"));
		assertTrue(resultEvent.getHier321Id().equals("h321id"));
//		assertTrue(resultEvent.getDateTime().equals("bd_date"));
		assertFalse(resultEvent.getNeVersion().equals("Version"));
		assertFalse(resultEvent.getImsi().equals("im"));
		assertFalse(resultEvent.getHier3Id().equals("3id"));
		assertFalse(resultEvent.getHier32Id().equals("h32"));
		assertFalse(resultEvent.getHier321Id().equals("21id"));
		assertFalse(resultEvent.getDateTime().equals("date"));
		assertNotNull(results.get(0));
		
		Base_Data result = results.get(1);
		
		assertNull(result.getDataId());
		assertNull(result.getCellId());
		assertNull(result.getDuration());
		assertNull(result.getNeVersion());
		assertNull(result.getImsi());
		assertNull(result.getHier3Id());
		assertNull(result.getHier32Id());
		assertNull(result.getHier321Id());
//		assertNull(result.getDateTime());
		assertNotNull(result);
		

	}

}


