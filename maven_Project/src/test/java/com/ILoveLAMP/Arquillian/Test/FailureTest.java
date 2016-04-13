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
import com.ILoveLAMP.entities.Failure;
import com.ILoveLAMP.services.Base_DataService;
import com.ILoveLAMP.services.Base_DataServiceEJB;

@RunWith(Arquillian.class)
public class FailureTest {

	@Inject
	Base_DataService service;
	
	private Failure failure;
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addPackage(Base_DataServiceEJB.class.getPackage())
				.addPackage(Failure.class.getPackage())
				.addPackage(JPABase_DataDAO.class.getPackage())
				.addPackage(Base_DataDAO.class.getPackage())
				.addAsManifestResource(EmptyAsset.INSTANCE,
						ArchivePaths.create("beans.xml"))
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml");
	}
	
	@Before
	public void persistdb() {
		failure = new Failure(99, "test");
		service.addToFailures(failure);
	}
	
	@After
	public void removedb() {
		service.removeFailure(failure);
	}
	
	
	@Test
	public void testObjectInsert() {
		String result = service.getFailurebyID(99).getDescription().toString();
		assertEquals("test", result);
		assertNotSame((String)"testing", result);
	}

}
