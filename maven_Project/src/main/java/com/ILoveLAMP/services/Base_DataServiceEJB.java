package com.ILoveLAMP.services;

import java.util.Collection;
import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ILoveLAMP.dao.Base_DataDAO;
import com.ILoveLAMP.entities.Base_Data;
import com.ILoveLAMP.entities.Error_Data;
import com.ILoveLAMP.entities.Event_Cause;
import com.ILoveLAMP.entities.Failure;
import com.ILoveLAMP.entities.Operator;
import com.ILoveLAMP.entities.User_Equipment;

@Stateless
public class Base_DataServiceEJB implements Base_DataService{

	@Inject
	private Base_DataDAO dao;
	
	
	public void setDao(Base_DataDAO dao) {
		this.dao = dao;
	}

	public void addToBaseDatas(Base_Data data) {
	//	if(!dao.getAllBaseDatas().contains(data))
			dao.addBasicData(data);		
	}

	public Collection<Base_Data> getAllBaseDatas() {
		return dao.getAllBaseDatas();
	}

	public Base_Data getDatabyID(int id) {
		return dao.getBaseDataById(id);
	}

	
	public void addUser_Equipment(Collection<User_Equipment> eq) {
		dao.addUser_Equipment(eq);
		
	}

	
	public void addOperator(Collection<Operator> op) {
		dao.addOperator(op);
	}

	
	public void addFailure(Collection<Failure> failures) {
		dao.addFailure(failures);
		
	}

	
	public void addEventCause(Collection<Event_Cause> events) {
		dao.addEventCause(events);
		
	}


	public void addBaseData(Collection<Base_Data> data) {
		dao.addBaseData(data);
		
	}

	public void addErrorData(Collection<Error_Data> data) {
		dao.addErrorData(data);
	}

	public Collection<Base_Data> getDatabyTime(Date time) {
		return dao.getDatabyTime(time);
	}


/*	public Collection<Base_Data> getAllBaseDatasbyID(int id) {
		return dao.getAllBaseDatasbyID(id);
	}*/

	
}

