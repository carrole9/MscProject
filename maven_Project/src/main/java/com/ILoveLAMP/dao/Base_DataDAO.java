package com.ILoveLAMP.dao;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.ILoveLAMP.entities.Base_Data;

import com.ILoveLAMP.entities.Event_Cause;
import com.ILoveLAMP.entities.Failure;
import com.ILoveLAMP.entities.Operator;

import com.ILoveLAMP.entities.User_Equipment;

@Local
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public interface Base_DataDAO {

	void addBasicData(Base_Data data);
	Base_Data getBaseDataById(int id);
	Collection<Base_Data> getAllBaseDatas();
//	Collection<Base_Data> getAllBaseDatasbyID(int id);
	void addUser_Equipment(Collection<User_Equipment> eq);
	void addOperator(Collection<Operator> op);
	void addFailure(Collection<Failure> failures);
	void addEventCause(Collection<Event_Cause> events);
	void addBaseData(Collection<Base_Data> data);

}
