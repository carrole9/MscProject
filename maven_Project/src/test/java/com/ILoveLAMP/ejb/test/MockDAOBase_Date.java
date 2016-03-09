package com.ILoveLAMP.ejb.test;


	import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

	import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import com.ILoveLAMP.dao.jpa.JPABase_DataDAO;
import com.ILoveLAMP.entities.Base_Data;
import com.ILoveLAMP.entities.Event_Cause;
import com.ILoveLAMP.entities.Failure;
import com.ILoveLAMP.entities.Operator;
import com.ILoveLAMP.entities.User_Equipment;
import com.ILoveLAMP.services.Base_DataServiceEJB;

	

	public class MockDAOBase_Date {
		
		Date date = new Date(2013, 11, 11, 11, 11, 11);
		
		private Base_DataServiceEJB eventEJB;
		
		@Before
		public void setup(){
			Failure fail=new Failure(0,"Emergengy");
			Event_Cause eventCause= new Event_Cause(40970,0,4097,"RC con conect");
			Operator operator = new Operator(2381,238,1,"Denmark","TCD-DK");
			User_Equipment userEquipment = new User_Equipment();
			Base_Data data = new Base_Data(1, date, 1,
					100, "version", "100000",
					"100000", "100000", "100000",
					fail, eventCause,  operator,
					userEquipment);
			Collection<Base_Data>datas=new ArrayList <Base_Data>();
			JPABase_DataDAO mockedDAO = mock(JPABase_DataDAO.class);
			when(mockedDAO.getBaseDataById(anyInt())).thenReturn(data);
			when(mockedDAO.getAllBaseDatas()).thenReturn(datas);
		//	when(((OngoingStubbing<Base_Data>) mockedDAO.getBaseDataById(0)).thenReturn(mockedEvents);
			
			eventEJB = new Base_DataServiceEJB();
			eventEJB.setDao(mockedDAO);
			
		}

		@Test
		public void test() {
			Base_Data results = eventEJB.getDatabyID(2);
					
  
			
			
			assertEquals(results.getDataId().intValue(), 1);
			assertEquals(results.getDateTime(), "today");
			assertEquals(results.getCellId().intValue(), 1);
			assertEquals(results.getDuration().intValue(), 100);
			assertEquals(results.getNeVersion(), "version");
			assertEquals(results.getImsi(), "100000");
			assertEquals(results.getHier3Id(), "100000");
			assertEquals(results.getHier32Id(), "100000");
			assertEquals(results.getHier321Id(), "100000");
			assertEquals(results.getFailure().getFailureId().intValue(), 0);
			assertEquals(results.getEventCause().getEcId().intValue(), 40970);
			assertEquals(results.getOperator().getOpId().intValue(), 2381);
			
		}
		@Test
		public void testbase_datas() {
			Collection<Base_Data>base_datas=eventEJB.getAllBaseDatas();
			assertNotNull(base_datas);
			assertTrue(base_datas.size()<=0);
		}

	}
/*	package daoTests;

	import static org.junit.Assert.*;
	import static org.mockito.Mockito.*;

	import java.util.ArrayList;

	import org.junit.Before;
	import org.junit.Test;

	import entities.Event;
	import jpa.dao.JPAEventDAO;
	import services.EventServiceEJB;

	public class EventDAOTests {
		
		private EventServiceEJB eventEJB;
		
		@Before
		public void setup(){
			ArrayList<Event> mockedEvents = new ArrayList<Event>();
			mockedEvents.add(new Event("Todays date", 1, 1, 1, 1, 1, 1, 1, 1, "neVersion", "imsi", "hier3id", "hier32id",
					"hier321id"));
			JPAEventDAO mockedDAO = mock(JPAEventDAO.class);
			when(mockedDAO.getEventByIMSI(anyString())).thenReturn(mockedEvents);
			
			eventEJB = new EventServiceEJB();
			eventEJB.setDao(mockedDAO);
			
		}

		@Test
		public void test() {
			ArrayList<Event> results = (ArrayList<Event>) eventEJB.getEventByIMSI("Apples");
			Event resultEvent = results.get(0);
			assertTrue(results.size() == 1);
			//assertEquals(resultEvent.getId(), 1);
			assertEquals(resultEvent.getDate(), "Todays date");
			assertEquals(resultEvent.getEventId(), 1);
			assertEquals(resultEvent.getFailureClass(), 1);
			assertEquals(resultEvent.getTac(), 1);
			assertEquals(resultEvent.getMnc(), 1);
			assertEquals(resultEvent.getMcc(), 1);
			assertEquals(resultEvent.getCellId(), 1);
			assertEquals(resultEvent.getDuration(), 1);
			assertEquals(resultEvent.getCauseCode(), 1);
			assertEquals(resultEvent.getNeVersion(), "neVersion");
			assertEquals(resultEvent.getImsi(), "imsi");
			assertEquals(resultEvent.getHier3id(), "hier3id");
			assertEquals(resultEvent.getHier32id(), "hier32id");
			assertEquals(resultEvent.getHier321id(), "hier321id");
		}

	}*/
