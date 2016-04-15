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
import com.ILoveLAMP.entities.User;
import com.ILoveLAMP.services.Base_DataService;
import com.ILoveLAMP.services.Base_DataServiceEJB;

@RunWith(Arquillian.class)
public class UserTest {
	
	@Inject
	Base_DataService service;
	
	@PersistenceContext
	EntityManager em;
	
	private User user,user1;

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addPackage(Base_DataServiceEJB.class.getPackage())
				.addPackage(User.class.getPackage())
				.addPackage(JPABase_DataDAO.class.getPackage())
				.addPackage(Base_DataDAO.class.getPackage())
				.addAsManifestResource(EmptyAsset.INSTANCE,
						ArchivePaths.create("beans.xml"))
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml");
	}
	
	public void addOneBaseDataRow() {
		user = new User("Shanu", "123456", "admin", 1);
		service.addUser(user);
	}
	
	@Test
	public void test() {
		addOneBaseDataRow();
		
		String max = em.createQuery("SELECT max(us.id) FROM User us").getSingleResult().toString();
		int maxId = Integer.parseInt(max);
		
		user1 = service.getUserById(maxId);
		
		assertNotNull(user1.getUsername());
		assertEquals("Shanu", user1.getUsername());
		assertNotSame((String)"shanu1", user1.getUsername());
		
		assertNotNull(user1.getPassword());
		assertEquals("123456", user1.getPassword());
		assertNotSame((String)"123456789", user1.getPassword());
		
		assertNotNull(user1.getUsertype());
		assertEquals("admin", user1.getUsertype());
		assertNotSame((String)"service enginner", user1.getUsertype());
		
		service.removeUser(user1);
	}

}
