package com.ILoveLAMP.ejb.test;


	
	import static org.junit.Assert.*;
	import static org.mockito.Mockito.*;
	import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
	import org.junit.Test;

import com.ILoveLAMP.dao.jpa.JPABase_DataDAO;
import com.ILoveLAMP.entities.Base_Data;
import com.ILoveLAMP.services.Base_DataServiceEJB;



	public class InitialTests {
		
		private Base_DataServiceEJB service;
		
	
		/*@Test
		public void testAllBaseData() {
			Collection<Base_Data> data = service.getAllBaseDatas();
			assertTrue(data.size() > 1);	
			for (Base_Data base_data : data) {
				System.out.println(base_data.getDataId());
			}
		}*/
		
		@Test
		public void testBaseDatabyID() {
			int id=1;
			Base_Data data = new Base_Data();
			data.setCellId(4);
			assertTrue(data.getCellId() == 4);	
			
		}
	}
