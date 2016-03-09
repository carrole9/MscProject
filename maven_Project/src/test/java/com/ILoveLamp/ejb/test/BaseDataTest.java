package com.ILoveLamp.ejb.test;

import static org.junit.Assert.*;

import javax.inject.Inject;

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
import com.ILoveLAMP.services.Base_DataService;
import com.ILoveLAMP.services.Base_DataServiceEJB;
import com.ILoveLAMP.services.Rest;

@RunWith(Arquillian.class)
public class BaseDataTest {
	
	@Inject
	Base_DataService service;
	
	@Deployment 
	public static JavaArchive createDeployment() {
	return ShrinkWrap.create(JavaArchive.class, "test.jar")
	.addPackage(Base_DataServiceEJB.class.getPackage())
	.addPackage(Base_Data.class.getPackage())
	.addPackage(JPABase_DataDAO.class.getPackage())
	.addPackage(Base_DataDAO.class.getPackage())
	.addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
	.addAsManifestResource("META-INF/persistence.xml","persistence.xml");
	}

		@Test
		public void testThatTheEJBReturnsTheRightBooks() {
			assertEquals(1, service.getDatabyID(1).getFailure().getFailureId(), 0.01);
		    
		}
	}

