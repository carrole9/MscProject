package com.ILoveLAMP.Arquillian.Test;

import static org.junit.Assert.*;

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
import com.ILoveLAMP.entities.Error_Data;
import com.ILoveLAMP.services.Base_DataService;
import com.ILoveLAMP.services.Base_DataServiceEJB;

@RunWith(Arquillian.class)
public class Error_DataTest {

	@Inject
	Base_DataService service;
	
	@PersistenceContext
	EntityManager em;
	
	private Error_Data data,data1;
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addPackage(Base_DataServiceEJB.class.getPackage())
				.addPackage(Error_Data.class.getPackage())
				.addPackage(JPABase_DataDAO.class.getPackage())
				.addPackage(Base_DataDAO.class.getPackage())
				.addAsManifestResource(EmptyAsset.INSTANCE,
						ArchivePaths.create("beans.xml"))
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml");
	}
	
	public void addOneBaseDataRow() {
		data = new Error_Data(99, "Fri Jan 11 17:32:00 GMT 2013", "9999", "test", "test2", "999", "999", "9", "9999",
				"test3", "23X", "123456789", "123456789", "123456789", "123456789");
		
		service.addErrorData(data);
	}
	
	@Test
	public void test() {
		addOneBaseDataRow();
		
		String max = em.createQuery("SELECT max(ed.dataId) FROM Error_Data ed").getSingleResult().toString();
		int maxId = Integer.parseInt(max);
		
		data1 = service.getErrorDataById(maxId);
	
		assertNotNull(data1.getDateTime());
		assertEquals("Fri Jan 11 17:32:00 GMT 2013", data1.getDateTime());
		assertNotSame((String)"testing", data1.getDateTime());
		
		assertNotNull(data1.getEventId());
		assertEquals("9999", data1.getEventId());
		assertNotSame((String)"testing", data1.getEventId());
		
		assertNotNull(data1.getFailureId());
		assertEquals("test", data1.getFailureId());
		assertNotSame((String)"testing", data1.getFailureId());
		
		assertNotNull(data1.getUeType());
		assertEquals("test2", data1.getUeType());
		assertNotSame((String)"testing", data1.getUeType());
		
		assertNotNull(data1.getMarket());
		assertEquals("999", data1.getMarket());
		assertNotSame((String)"testing", data1.getMarket());
		
		assertNotNull(data1.getOperator());
		assertEquals("999", data1.getOperator());
		assertNotSame((String)"testing", data1.getOperator());
		
		assertNotNull(data1.getCellId());
		assertEquals("9", data1.getCellId());
		assertNotSame((String)"99", data1.getCellId());
		
		assertNotNull(data1.getDuration());
		assertEquals("9999", data1.getDuration());
		assertNotSame((String)"99", data1.getDuration());
		
		assertNotNull(data1.getCause_Code());
		assertEquals("test3", data1.getCause_Code());
		assertNotSame((String)"99", data1.getCause_Code());
		
		assertNotNull(data1.getNeVersion());
		assertEquals("23X", data1.getNeVersion());
		assertNotSame((String)"99", data1.getNeVersion());
		
		assertNotNull(data1.getImsi());
		assertEquals("123456789", data1.getImsi());
		assertNotSame((String)"99", data1.getImsi());
		
		assertNotNull(data1.getHier3_Id());
		assertEquals("123456789", data1.getHier3_Id());
		assertNotSame((String)"99", data1.getHier3_Id());
		
		assertNotNull(data1.getHier3_Id());
		assertEquals("123456789", data1.getHier32_Id());
		assertNotSame((String)"99", data1.getHier32_Id());
		
		assertNotNull(data1.getHier3_Id());
		assertEquals("123456789", data1.getHier321_Id());
		assertNotSame((String)"99", data1.getHier321_Id());
		
		service.removeErrorData(data1);
	}

}
