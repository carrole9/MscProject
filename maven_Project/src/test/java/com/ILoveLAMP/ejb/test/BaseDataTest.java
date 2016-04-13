package com.ILoveLAMP.ejb.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.ILoveLAMP.entities.Base_Data;
import com.ILoveLAMP.entities.Event_Cause;
import com.ILoveLAMP.entities.Failure;
import com.ILoveLAMP.entities.Operator;
import com.ILoveLAMP.entities.User_Equipment;

public class BaseDataTest {
	
	@SuppressWarnings("deprecation")
	Date date = new Date(2013, 11, 11, 11, 11, 11);
	Failure failure1 = new Failure(1, "test failure");
	Event_Cause ec1 = new Event_Cause(1, 1, 1, "test event cause");
	Operator op1 = new Operator(1, 1, 1, "ireland", "operatorName");
	User_Equipment ue1 = new User_Equipment(1, "marketingName", "manufacturer", "accessCapability", "model",
			"vendorName", "ueType", "os", "inputMode");

	Failure failure2 = new Failure(2, "test failure_2");
	Event_Cause ec2 = new Event_Cause(2, 2, 2, "test event cause_2");
	Operator op2 = new Operator(2, 2, 2, "england", "operatorName_2");
	User_Equipment ue2 = new User_Equipment(2, "marketingName_2", "manufacturer_2", "accessCapability_2", "model_2",
			"vendorName_2", "ueType_2", "os_2", "inputMode_2");

	@Test
	public void test() {
		//fail("Not yet implemented");

		Base_Data bd = new Base_Data(1, date, 1, 1, "neVersion", "imsi", "h3id", "h32id", "h321id", failure1, ec1,
				op1, ue1);

		assertEquals(1, bd.getDataId(), 0.01);
		assertEquals(1, bd.getCellId(), 0.01);
		assertEquals(1, bd.getDuration(), 0.01);
		assertTrue(bd.getNeVersion().equals("neVersion"));
		assertTrue(bd.getImsi().equals("imsi"));
		assertTrue(bd.getHier3Id().equals("h3id"));
		assertTrue(bd.getHier32Id().equals("h32id"));
		assertTrue(bd.getHier321Id().equals("h321id"));
		//assertTrue(bd.getDateTime().equals("bd_date"));
		assertFalse(bd.getNeVersion().equals("Version"));
		assertFalse(bd.getImsi().equals("im"));
		assertFalse(bd.getHier3Id().equals("3id"));
		assertFalse(bd.getHier32Id().equals("h32"));
		assertFalse(bd.getHier321Id().equals("21id"));
		assertFalse(bd.getDateTime().equals("date"));
		
		bd.setCellId(2);
		bd.setDataId(2);
		bd.setDateTime(date);
		bd.setDuration(2);
		bd.setHier321Id("h321id_2");
		bd.setHier32Id("h32id_2");
		bd.setHier3Id("h3id_2");
		bd.setImsi("imsi_2");
		bd.setNeVersion("neVersion_2");
		 bd.setFailure(failure2);
	        bd.setEventCause(ec2);
	        bd.setOperator(op2);
	        bd.setUserEquipment(ue2);
	         
	        assertEquals(2, bd.getDataId(), 0.01);
	        assertEquals(2, bd.getCellId(), 0.01);
	        assertEquals(2, bd.getDuration(), 0.01);
	        assertTrue(bd.getNeVersion().equals("neVersion_2"));
	        assertTrue(bd.getImsi().equals("imsi_2"));
	        assertTrue(bd.getHier3Id().equals("h3id_2"));
	        assertTrue(bd.getHier32Id().equals("h32id_2"));
	        assertTrue(bd.getHier321Id().equals("h321id_2"));
//	        assertTrue(bd.getDateTime().equals("bd_date2"));
	         
	        assertEquals(2, bd.getFailure().getFailureId(), 0.01);
	        assertTrue(bd.getFailure().getDescription().equals("test failure_2"));
	        assertEquals(2, bd.getEventCause().getCauseCode(), 0.01);
	        assertTrue(bd.getEventCause().getDescription().equals("test event cause_2"));
	        assertEquals(2, bd.getOperator().getOpId(), 0.01);
	        assertTrue(bd.getOperator().getOperatorName().equals("operatorName_2"));
	        assertEquals(2, bd.getUserEquipment().getUserEquipmentId(), 0.01);
	        assertTrue(bd.getUserEquipment().getUeType().equals("ueType_2"));
	 

	}

}
