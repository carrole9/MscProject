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
import com.ILoveLAMP.entities.Operator;
import com.ILoveLAMP.services.Base_DataService;
import com.ILoveLAMP.services.Base_DataServiceEJB;

@RunWith(Arquillian.class)
public class OperatorTest {

	@Inject
	Base_DataService service;
	
	private Operator operator;
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addPackage(Base_DataServiceEJB.class.getPackage())
				.addPackage(Operator.class.getPackage())
				.addPackage(JPABase_DataDAO.class.getPackage())
				.addPackage(Base_DataDAO.class.getPackage())
				.addAsManifestResource(EmptyAsset.INSTANCE,
						ArchivePaths.create("beans.xml"))
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml");
	}
	
	@Before
	public void persistdb() {
		operator = new Operator(99, 9, 9, "IRELAND", "VODAFONE");
		service.addOperator(operator);
	}
	
	@After
	public void removedb() {
		service.removeOperator(operator);
	}
	
	@Test
	public void testObjectInsert() {
		Operator op = service.getOperatorById(99);
		
		assertNotNull(op.getMcc());
		assertEquals((Integer)9, op.getMcc());
		assertNotSame((Integer)99, op.getMcc());
		
		assertNotNull(op.getMnc());
		assertEquals((Integer)9, op.getMnc());
		assertNotSame((Integer)99, op.getMnc());
		
		assertNotNull(op.getCountry());
		assertEquals("IRELAND", op.getCountry());
		assertNotSame((String)"SPAIN", op.getCountry());
		
		assertNotNull(op.getOperatorName());
		assertEquals("VODAFONE", op.getOperatorName());
		assertNotSame((String)"METEOR", op.getOperatorName());
	}

}
