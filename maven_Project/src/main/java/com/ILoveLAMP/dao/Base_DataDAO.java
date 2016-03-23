package com.ILoveLAMP.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.ILoveLAMP.entities.Base_Data;
import com.ILoveLAMP.entities.Error_Data;
import com.ILoveLAMP.entities.Event_Cause;
import com.ILoveLAMP.entities.Failure;
import com.ILoveLAMP.entities.Operator;
import com.ILoveLAMP.entities.User;
import com.ILoveLAMP.entities.User_Equipment;

@Local
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public interface Base_DataDAO {

//	Collection<Base_Data> getAllBaseDatasbyID(int id);
	void addUser_Equipment(Collection<User_Equipment> eq);
	void addOperator(Collection<Operator> op);
	void addFailure(Collection<Failure> failures);
	void addEventCause(Collection<Event_Cause> events);
	void addBaseData(Collection<Base_Data> data);
	void addErrorData(Collection<Error_Data> data);
	Collection<Base_Data> getDatabyTime(Date time);
	Collection<Base_Data> getIMSIandCallFailures(Date firsttime, Date secondtime);
	Collection<Base_Data> getEventandCausecodeByIMSI(String imsi);
	Long getFailurebyTimeandIMSI(Date firsttime,Date secondtime, String imsi);
	Integer getNoOfFailuresByPeriodAndModel(Date startTime,
			Date endTime, String model);
	List<String> getIMSIbyId(Integer failId);
	List<String> getIMSIbyIdfailure(String failId);
	List<Object[]> getTop10ImsisByDate(Date startDate, Date endDate);
	Integer getTotalDurationByPeriodAndImsi(Date startTime, Date endTime,
			String imsi);
	Collection<String> getImsiByPeriod(Date startTime, Date endTime);
	List<Object[]> getNoOfFailuresTotalDurationsForEachImsiByPeriod(Date startTime, Date endTime);
	
	// User arquillian test DAO
		void addUser(User user);
		User getUserById(int id);
		Collection<User> getAllUsers();
		Collection<Base_Data> getAllIMSI();
		void removeUser(User user);
		
		// Base Data DAO
		void addBasicData(Base_Data data);
		Base_Data getBaseDataById(int id);
		Collection<Base_Data> getAllBaseDatas();
		Collection<Base_Data> getAllFailureID();
		Collection<Base_Data> getAllManufacturers(); 
		Collection<Base_Data> getAllModels(String model);
		void removeBase_Data(Base_Data data);
		
		// Error Data DAO
		void addErrorData(Error_Data data);
		Error_Data getErrorDataById(int id);
		Collection<Error_Data> getAllErrorDatas();
		void removeErrorData(Error_Data data);
		
		// Failure DAO
		void addFailure(Failure failure);
		Failure getFailureById(int id);
		Collection<Failure> getAllFailures();
		void removeFailure(Failure failure);
		
		// Event Cause DAO
		void addEvent_Cause(Event_Cause eventCause);
		Event_Cause getEvent_CauseById(int id);
		Collection<Event_Cause> getAllEvent_Causes();
		void removeEvent_Cause(Event_Cause eventCause);
		
		// User Equipment DAO
		void addUser_Equipment(User_Equipment ue);
		User_Equipment getUser_EquipmentById(int id);
		Collection<User_Equipment> getAllUser_Equipments();
		void removeUser_Equipment(User_Equipment ue);
		
		// Operator DAO
		void addOperator(Operator operator);
		Operator getOperatorById(int id);
		Collection<Operator> getAllOperators();
		void removeOperator(Operator operator);
		
		List<Object[]> getUniqueCauseCodesForIMSI(String imsi);
		
		List<Object[]>  getFailureCauseCodeAndOccurrences(String model);
		List<Object[]> top10MarketOperatorCellIDCombo(Date firsttime, Date secondtime);
}
