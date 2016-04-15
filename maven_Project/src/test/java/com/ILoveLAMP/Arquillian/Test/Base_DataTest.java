package com.ILoveLAMP.Arquillian.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ILoveLAMP.dao.Base_DataDAO;
import com.ILoveLAMP.dao.jpa.JPABase_DataDAO;
import com.ILoveLAMP.entities.Base_Data;
import com.ILoveLAMP.entities.Event_Cause;
import com.ILoveLAMP.entities.Failure;
import com.ILoveLAMP.entities.Operator;
import com.ILoveLAMP.entities.User_Equipment;
import com.ILoveLAMP.services.Base_DataService;
import com.ILoveLAMP.services.Base_DataServiceEJB;

@RunWith(Arquillian.class)
public class Base_DataTest {

	@Inject
	Base_DataService service;
	
	@PersistenceContext
	EntityManager em;
	
	private Base_Data data, data1;
	private Event_Cause event,event1;
	private Failure fail,fail1;
	private Operator operator,operator1;
	private User_Equipment equipment,equipment1;

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addPackage(Base_DataServiceEJB.class.getPackage())
				.addPackage(Base_Data.class.getPackage())
				.addPackage(JPABase_DataDAO.class.getPackage())
				.addPackage(Base_DataDAO.class.getPackage())
				.addAsManifestResource(EmptyAsset.INSTANCE,
						ArchivePaths.create("beans.xml"))
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml");
	}
	
	public void backingTables() {
		event = new Event_Cause(99, 9, 9, "test");
		fail = new Failure(99, "test");
		operator = new Operator(99, 9, 9, "IRELAND", "VODAFONE");
		equipment = new User_Equipment(99, "test", "test2", "test3", "test4", "test5", "test6", "windows", "test7");
	}
	
	public void addBackUpTables() {
		service.addEvent_Cause(event);
		service.addToFailures(fail);
		service.addOperator(operator);
		service.addUser_Equipment(equipment);
	}
	
	@SuppressWarnings("deprecation")
	public void addOneBaseDataRow() {
		backingTables();
		
		data = new Base_Data(9999, new Date(2013, 3, 10, 9, 10), 9, 1001, "23X", "123456789", "123456789", "123456789", "123456789",
				fail, event, operator, equipment);
	
		addBackUpTables();
		service.addToBaseDatas(data);
	}
	
	public void emptyBackUpTables() {
		service.removeEvent_Cause(event);
		service.removeFailure(fail);
		service.removeOperator(operator);
		service.removeUser_Equipment(equipment);
	}
	
	public void emptydb() {
		service.removeBase_Data(data1);
		emptyBackUpTables();
	}
	
	@Test
	public void testBaseDataValues() {
		addOneBaseDataRow();
		String max = em.createQuery("SELECT max(bd.dataId) FROM Base_Data bd").getSingleResult().toString();
		int maxId = Integer.parseInt(max);
		
		data1 = service.getDatabyID(maxId);
		fail1 = data1.getFailure();
		event1 = data1.getEventCause();
		operator1 = data1.getOperator();
		equipment1 = data1.getUserEquipment();
		
		testChildFailure();
		testChildEventCause();
		testChildOperator();
		testChildUserEquipment();
		testBaseData();
		emptydb();
	}
	
	public void testChildFailure(){
		assertNotNull(fail1.getDescription());
		assertEquals("test", fail1.getDescription());
		assertNotSame((String)"testing", fail1.getDescription());
	}
	
	public void testChildEventCause(){
		
		assertNotNull(event1.getCauseCode());
		assertEquals((Integer)9, event1.getCauseCode());
		assertNotSame((Integer)99, event1.getCauseCode());
		
		assertNotNull(event1.getEventId());
		assertEquals((Integer)9, event1.getEventId());
		assertNotSame((Integer)99, event1.getEventId());
		
		assertNotNull(event1.getDescription());
		assertEquals("test", event1.getDescription());
		assertNotSame((String)"testing", event1.getDescription());
	}
	
	public void testChildOperator(){
		assertNotNull(operator1.getMcc());
		assertEquals((Integer)9, operator1.getMcc());
		assertNotSame((Integer)99, operator1.getMcc());
		
		assertNotNull(operator1.getMnc());
		assertEquals((Integer)9, operator1.getMnc());
		assertNotSame((Integer)99, operator1.getMnc());
		
		assertNotNull(operator1.getCountry());
		assertEquals("IRELAND", operator1.getCountry());
		assertNotSame((String)"SPAIN", operator1.getCountry());
		
		assertNotNull(operator1.getOperatorName());
		assertEquals("VODAFONE", operator1.getOperatorName());
		assertNotSame((String)"METEOR", operator1.getOperatorName());
	}
	
	public void testChildUserEquipment(){
		assertNotNull(equipment1.getMarketingName());
		assertEquals("test", equipment1.getMarketingName());
		assertNotSame((String)"testing", equipment1.getMarketingName());
		
		assertNotNull(equipment1.getManufacturer());
		assertEquals("test2", equipment1.getManufacturer());
		assertNotSame((String)"testing", equipment1.getManufacturer());
		
		assertNotNull(equipment1.getAccessCapability());
		assertEquals("test3", equipment1.getAccessCapability());
		assertNotSame((String)"testing", equipment1.getAccessCapability());
		
		assertNotNull(equipment1.getModel());
		assertEquals("test4", equipment1.getModel());
		assertNotSame((String)"testing", equipment1.getModel());
		
		assertNotNull(equipment1.getVendorName());
		assertEquals("test5", equipment1.getVendorName());
		assertNotSame((String)"testing", equipment1.getVendorName());
		
		assertNotNull(equipment1.getUeType());
		assertEquals("test6", equipment1.getUeType());
		assertNotSame((String)"testing", equipment1.getUeType());
		
		assertNotNull(equipment1.getOs());
		assertEquals("windows", equipment1.getOs());
		assertNotSame((String)"testing", equipment1.getOs());
		
		assertNotNull(equipment1.getInputMode());
		assertEquals("test7", equipment1.getInputMode());
		assertNotSame((String)"testing", equipment1.getInputMode());
	}

	public void testBaseData(){
		assertNotNull(data1.getDateTime());
		assertEquals("3913-04-10 09:10:00.0", data1.getDateTime().toString());
		assertNotSame((String)"3913-05-10 09:10:00.0", data1.getDateTime());
		
		assertNotNull(data1.getCellId());
		assertEquals((Integer)9, data1.getCellId());
		assertNotSame((Integer)99, data1.getCellId());
		
		assertNotNull(data1.getDuration());
		assertEquals((Integer)1001, data1.getDuration());
		assertNotSame((Integer)99, data1.getDuration());
		
		assertNotNull(data1.getNeVersion());
		assertEquals("23X", data1.getNeVersion().toString());
		assertNotSame((String)"23S", data1.getNeVersion());
		
		assertNotNull(data1.getImsi());
		assertEquals("123456789", data1.getImsi().toString());
		assertNotSame((String)"123456788", data1.getImsi());
		
		assertNotNull(data1.getHier3Id());
		assertEquals("123456789", data1.getHier3Id().toString());
		assertNotSame((String)"123456788", data1.getHier3Id());
		
		assertNotNull(data1.getHier32Id());
		assertEquals("123456789", data1.getHier32Id().toString());
		assertNotSame((String)"123456788", data1.getHier32Id());
		
		assertNotNull(data1.getHier321Id());
		assertEquals("123456789", data1.getHier321Id().toString());
		assertNotSame((String)"123456788", data1.getHier321Id());
	}
}
