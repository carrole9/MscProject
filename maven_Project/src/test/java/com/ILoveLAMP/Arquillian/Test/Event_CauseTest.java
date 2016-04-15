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
import com.ILoveLAMP.entities.Event_Cause;
import com.ILoveLAMP.services.Base_DataService;
import com.ILoveLAMP.services.Base_DataServiceEJB;

@RunWith(Arquillian.class)
public class Event_CauseTest {

	@Inject
	Base_DataService service;
	
	private Event_Cause eventCause;
	
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addPackage(Base_DataServiceEJB.class.getPackage())
				.addPackage(Event_Cause.class.getPackage())
				.addPackage(JPABase_DataDAO.class.getPackage())
				.addPackage(Base_DataDAO.class.getPackage())
				.addAsManifestResource(EmptyAsset.INSTANCE,
						ArchivePaths.create("beans.xml"))
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml");
	}
	
	@Before
	public void persistdb() {
		eventCause = new Event_Cause(99, 9, 9, "test");
		service.addEvent_Cause(eventCause);
	}
	
	@After
	public void removedb() {
		service.removeEvent_Cause(eventCause);
	}
	
	@Test
	public void testObjectInsert() {
		Event_Cause event = service.getEvent_CauseById(99);
		
		assertNotNull(event.getCauseCode());
		assertEquals((Integer)9, event.getCauseCode());
		assertNotSame((Integer)99, event.getCauseCode());
		
		assertNotNull(event.getEventId());
		assertEquals((Integer)9, event.getEventId());
		assertNotSame((Integer)99, event.getEventId());
		
		assertNotNull(event.getDescription());
		assertEquals("test", event.getDescription());
		assertNotSame((String)"testing", event.getDescription());
	}

}
