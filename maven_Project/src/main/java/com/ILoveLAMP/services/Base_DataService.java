package com.ILoveLAMP.services;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.ILoveLAMP.entities.Base_Data;
import com.ILoveLAMP.entities.Error_Data;
import com.ILoveLAMP.entities.Event_Cause;
import com.ILoveLAMP.entities.Failure;
import com.ILoveLAMP.entities.Operator;
import com.ILoveLAMP.entities.User;
import com.ILoveLAMP.entities.User_Equipment;

@Local
public interface Base_DataService {

//	public Collection<Base_Data> getAllBaseDatasbyID(int id);
	
	public void addUser_Equipment(Collection<User_Equipment> eq);
	public void addOperator(Collection<Operator> op);
	public void addFailure(Collection<Failure> failures);
	public void addEventCause(Collection<Event_Cause> events);
	public void addBaseData(Collection<Base_Data> data);
	public void addErrorData(Collection<Error_Data> data);
	public Collection<Base_Data> getDatabyTime(Date date);
	Collection<Base_Data> getIMSIandCallFailures(Date firsttime, Date secondtime);
	Long getFailurebyTimeandIMSI(Date firsttime, Date secondtime, String imsi);
	Collection<Base_Data> getEventandCausecodeByIMSI(String imsi);	
	Integer getNoOfFailuresByPeriodAndModel(Date startTime,Date endTime,String model);
	public List<String> getIMSIbyFailureId(Integer failId);
	
	public List<Object[]> getTop10ImsisByDate(Date startDate, Date endDate);
	public Map<String, Integer> getAllTotalDurationByPeriodAndImsi(Date startTime, Date endTime);
	Collection<String> getImsiByPeriod(Date startTime, Date endTime);
	Integer getTotalDurationByPeriodAndImsi(Date startTime,
			Date endTime, String imsi);
	public List<Object[]> getNoOfFailuresTotalDurationsForEachImsiByPeriod(Date startTime, Date endTime);
	
	// User arquillian test DAO
		public void addUser(User user);
		public User getUserById(int id);
		public Collection<User> getAllUsers();
		public void removeUser(User user);
	
		// Base Data Service
		public void addToBaseDatas(Base_Data data);
		public Collection<Base_Data> getAllBaseDatas();
		public Collection<Base_Data> getAllIMSI();
		public Base_Data getDatabyID(int id);
		public void removeBase_Data(Base_Data data);
		
		// Error Data DAO
		public void addErrorData(Error_Data data);
		public Error_Data getErrorDataById(int id);
		public Collection<Error_Data> getAllErrorDatas();
		public void removeErrorData(Error_Data data);
		
		// Failure Service
		public void addToFailures(Failure failure);
		public Collection<Failure> getAllFailures();
		public Failure getFailurebyID(int id);
		public void removeFailure(Failure failure);
		
		// Event Cause Service
		public void addEvent_Cause(Event_Cause eventCause);
		public Event_Cause getEvent_CauseById(int id);
		public Collection<Event_Cause> getAllEvent_Causes();
		public void removeEvent_Cause(Event_Cause eventCause);
		
		// User Equipment Service
		public void addUser_Equipment(User_Equipment ue);
		public User_Equipment getUser_EquipmentById(int id);
		public Collection<User_Equipment> getAllUser_Equipments();
		public void removeUser_Equipment(User_Equipment ue);
		
		// Operator DAO
		public void addOperator(Operator operator);
		public Operator getOperatorById(int id);
		public Collection<Operator> getAllOperators();
		public void removeOperator(Operator operator);
		
		List<Object[]> getUniqueCauseCodesForIMSI(String imsi);
		List<Object[]>  getFailureCauseCodeAndOccurrences(String model);
		public List<Object[]> top10MarketOperatorCellIDCombo(Date firsttime, Date secondtime);
}

