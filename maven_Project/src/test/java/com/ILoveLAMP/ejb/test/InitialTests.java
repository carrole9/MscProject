package com.ILoveLAMP.ejb.test;


	
	import static org.junit.Assert.*;
import org.junit.Test;

import com.ILoveLAMP.entities.Base_Data;
import com.ILoveLAMP.services.Base_DataServiceEJB;



	public class InitialTests {
		
		@SuppressWarnings("unused")
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
			@SuppressWarnings("unused")
			int id=1;
			Base_Data data = new Base_Data();
			data.setCellId(4);
			assertTrue(data.getCellId() == 4);	
			
		}
	}
