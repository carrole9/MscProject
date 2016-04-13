package com.ILoveLAMP.Arquillian.Test;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ILoveLAMP.dao.Base_DataDAO;
import com.ILoveLAMP.dao.jpa.JPABase_DataDAO;
import com.ILoveLAMP.entities.User_Equipment;
import com.ILoveLAMP.services.Base_DataService;
import com.ILoveLAMP.services.Base_DataServiceEJB;

@RunWith(Arquillian.class)
public class User_EquipmentTest {

	@Inject
	Base_DataService service;
	
	private User_Equipment ue;
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addPackage(Base_DataServiceEJB.class.getPackage())
				.addPackage(User_Equipment.class.getPackage())
				.addPackage(JPABase_DataDAO.class.getPackage())
				.addPackage(Base_DataDAO.class.getPackage())
				.addAsManifestResource(EmptyAsset.INSTANCE,
						ArchivePaths.create("beans.xml"))
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml");
	}
	
	@Before
	public void persistdb() {
		ue = new User_Equipment(99, "test", "test2", "test3", "test4", "test5", "test6", "windows", "test7");
		service.addUser_Equipment(ue);
	}
	
	@After
	public void removedb() {
		service.removeUser_Equipment(ue);
	}
	
	@Test
	public void testObjectInsert() {
		User_Equipment equipment = service.getUser_EquipmentById(99);
		
		assertNotNull(equipment.getMarketingName());
		assertEquals("test", equipment.getMarketingName());
		assertNotSame((String)"testing", equipment.getMarketingName());
		
		assertNotNull(equipment.getManufacturer());
		assertEquals("test2", equipment.getManufacturer());
		assertNotSame((String)"testing", equipment.getManufacturer());
		
		assertNotNull(equipment.getAccessCapability());
		assertEquals("test3", equipment.getAccessCapability());
		assertNotSame((String)"testing", equipment.getAccessCapability());
		
		assertNotNull(equipment.getModel());
		assertEquals("test4", equipment.getModel());
		assertNotSame((String)"testing", equipment.getModel());
		
		assertNotNull(equipment.getVendorName());
		assertEquals("test5", equipment.getVendorName());
		assertNotSame((String)"testing", equipment.getVendorName());
		
		assertNotNull(equipment.getUeType());
		assertEquals("test6", equipment.getUeType());
		assertNotSame((String)"testing", equipment.getUeType());
		
		assertNotNull(equipment.getOs());
		assertEquals("windows", equipment.getOs());
		assertNotSame((String)"testing", equipment.getOs());
		
		assertNotNull(equipment.getInputMode());
		assertEquals("test7", equipment.getInputMode());
		assertNotSame((String)"testing", equipment.getInputMode());
	}

}
