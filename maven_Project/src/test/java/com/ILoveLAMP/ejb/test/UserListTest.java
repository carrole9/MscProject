package com.ILoveLAMP.ejb.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import com.ILoveLAMP.entities.User;
import com.ILoveLAMP.entities.UserList;

public class UserListTest {
	
	UserList list = new UserList();
	
	@Test
	public void UserListSetter_Getter(){
		
		Collection<User> users = new ArrayList<User>();
		
		User user1 = new User("Emer","password","admin");
		User user2 = new User("Yang","password","customer");
		
		users.add(user1);
		users.add(user2);
		assertNull(list.getUserCollection());
	
		list.setDiscCollection(users);
		assertNotNull(list.getUserCollection());
		
		assertEquals(list.getUserCollection(),users);
		
	}
	

}
