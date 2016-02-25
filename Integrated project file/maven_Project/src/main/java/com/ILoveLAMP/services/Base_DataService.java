package com.ILoveLAMP.services;

import java.util.Collection;

import javax.ejb.Local;

import com.ILoveLAMP.entities.Base_Data;

import com.ILoveLAMP.entities.Event_Cause;
import com.ILoveLAMP.entities.Failure;
import com.ILoveLAMP.entities.Operator;

import com.ILoveLAMP.entities.User_Equipment;

@Local
public interface Base_DataService {

	public void addToBaseDatas(Base_Data data);
	public Collection<Base_Data> getAllBaseDatas();
	public Base_Data getDatabyID(int id);
//	public Collection<Base_Data> getAllBaseDatasbyID(int id);
	
	public void addUser_Equipment(Collection<User_Equipment> eq);
	public void addOperator(Collection<Operator> op);
	public void addFailure(Collection<Failure> failures);
	public void addEventCause(Collection<Event_Cause> events);
	public void addBaseData(Collection<Base_Data> data);
}

