package com.ILoveLAMP.ejb.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.ILoveLAMP.entities.Base_Data;
import com.ILoveLAMP.entities.Base_DataList;
import com.ILoveLAMP.entities.User;
import com.ILoveLAMP.entities.UserList;

public class Base_DataListTest {
	
	Base_DataList list = new Base_DataList();
	
	@Test
	public void Base_DataListSetter_Getter(){
		
		Collection<Base_Data> data = new ArrayList<Base_Data>();
		
		Base_Data data1= new Base_Data();
		
		data.add(data1);
		
		list.setBaseDataCollection(data);
		assertNotNull(list.getBaseDataCollection());
		
		assertEquals(list.getBaseDataCollection(),data);
		
		
	/*	users.add(user1);
		users.add(user2);
		assertNull(list.getUserCollection());
	
		list.setDiscCollection(users);
		assertNotNull(list.getUserCollection());
		
		assertEquals(list.getUserCollection(),users);*/
		
	}
	

}