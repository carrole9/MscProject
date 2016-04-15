package com.ILoveLAMP.services;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ILoveLAMP.dao.Base_DataDAO;
import com.ILoveLAMP.entities.Base_Data;
import com.ILoveLAMP.entities.Error_Data;
import com.ILoveLAMP.entities.Event_Cause;
import com.ILoveLAMP.entities.Failure;
import com.ILoveLAMP.entities.Operator;
import com.ILoveLAMP.entities.User;
import com.ILoveLAMP.entities.User_Equipment;

@Stateless
public class Base_DataServiceEJB implements Base_DataService{

	@Inject
	private Base_DataDAO dao;
	
	
	public void setDao(Base_DataDAO dao) {
		this.dao = dao;
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


	public Collection<Base_Data> getIMSIandCallFailures(Date firsttime, Date secondtime) {
		// TODO Auto-generated method stub
		return  dao.getIMSIandCallFailures(firsttime,secondtime);
	}


	public Collection<Base_Data> getEventandCausecodeByIMSI(String imsi) {
		return dao.getEventandCausecodeByIMSI(imsi);
	}

	
	public Long getFailurebyTimeandIMSI(Date firsttime, Date secondtime, String imsi) {
		return dao.getFailurebyTimeandIMSI(firsttime,secondtime,imsi);
	}

	
	public Integer getNoOfFailuresByPeriodAndModel(Date startTime,
			Date endTime, String model) {
		return dao.getNoOfFailuresByPeriodAndModel(startTime, endTime, model);
	}
	public List<String> getIMSIbyFailureId(String failId) {
		return dao.getIMSIbyIdfailure(failId);
	}
	
	public List<Object[]> getTop10ImsisByDate(Date startDate, Date endDate) {
		return dao.getTop10ImsisByDate(startDate, endDate);
	}


	
	public Map<String, Integer> getAllTotalDurationByPeriodAndImsi(Date startTime, Date endTime) {
		Integer totalDuration;
		Collection<String> imsis = getImsiByPeriod(startTime, endTime);
		Map<String, Integer> map = new HashMap<String, Integer>();		
		for(String imsi : imsis){
			totalDuration = getTotalDurationByPeriodAndImsi(startTime, endTime,imsi);
			map.put(imsi, totalDuration);
		}
		return map;
	}
	
	public Collection<String> getImsiByPeriod(Date startTime, Date endTime) {
		return dao.getImsiByPeriod(startTime, endTime);
	}
	
	public Integer getTotalDurationByPeriodAndImsi(Date startTime,
			Date endTime, String imsi) {
		return dao.getTotalDurationByPeriodAndImsi(startTime, endTime, imsi);
	}

	@Override
	public List<Object[]> getNoOfFailuresTotalDurationsForEachImsiByPeriod(
			Date startTime, Date endTime) {
		return dao.getNoOfFailuresTotalDurationsForEachImsiByPeriod(startTime, endTime);
	}


	@Override
	public void addUser(User user) {
		dao.addUser(user);
	}

	@Override
	public User getUserById(int id) {
		return dao.getUserById(id);
	}

	@Override
	public Collection<User> getAllUsers() {
		return dao.getAllUsers();
	}

	@Override
	public void removeUser(User user) {
		dao.removeUser(user);
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
		@Override
		public void removeBase_Data(Base_Data data) {
			dao.removeBase_Data(data);
		}
		@Override
		public void addToFailures(Failure failure) {
			dao.addFailure(failure);
		}
		@Override
		public Collection<Failure> getAllFailures() {
			return dao.getAllFailures();
		}
		@Override
		public Failure getFailurebyID(int id) {
			return dao.getFailureById(id);
		}
		@Override
		public void removeFailure(Failure failure) {
			dao.removeFailure(failure);
		}

		@Override
		public void addEvent_Cause(Event_Cause eventCause) {
			dao.addEvent_Cause(eventCause);
		}
		@Override
		public Event_Cause getEvent_CauseById(int id) {
			return dao.getEvent_CauseById(id);
		}
		@Override
		public Collection<Event_Cause> getAllEvent_Causes() {
			return dao.getAllEvent_Causes();
		}
		@Override
		public void removeEvent_Cause(Event_Cause eventCause) {
			dao.removeEvent_Cause(eventCause);
		}

		@Override
		public void addUser_Equipment(User_Equipment ue) {
			dao.addUser_Equipment(ue);
		}
		@Override
		public User_Equipment getUser_EquipmentById(int id) {
			return dao.getUser_EquipmentById(id);
		}
		@Override
		public Collection<User_Equipment> getAllUser_Equipments() {
			return dao.getAllUser_Equipments();
		}
		@Override
		public void removeUser_Equipment(User_Equipment ue) {
			dao.removeUser_Equipment(ue);
		}

		
		@Override
		public void addOperator(Operator operator) {
			dao.addOperator(operator);
		}
		@Override
		public Operator getOperatorById(int id) {
			return dao.getOperatorById(id);
		}
		@Override
		public Collection<Operator> getAllOperators() {
			return dao.getAllOperators();
		}
		@Override
		public void removeOperator(Operator operator) {
			dao.removeOperator(operator);
		}

		@Override
		public void addErrorData(Error_Data data) {
			dao.addErrorData(data);
		}
		@Override
		public Error_Data getErrorDataById(int id) {
			return dao.getErrorDataById(id);
		}
		@Override
		public Collection<Error_Data> getAllErrorDatas() {
			return dao.getAllErrorDatas();
		}
		@Override
		public void removeErrorData(Error_Data data) {
			dao.removeErrorData(data);
		}
		
		public List<Object[]> getUniqueCauseCodesForIMSI(String imsi) 
	    {
	        return dao.getUniqueCauseCodesForIMSI(imsi);
	    }
		public List<Object[]>  getFailureCauseCodeAndOccurrences(String model) {
			// TODO Auto-generated method stub
			return dao.getFailureCauseCodeAndOccurrences(model);
		}
		
		public List<Object[]> top10MarketOperatorCellIDCombo(Date firsttime, Date secondtime) 		{
			return dao.top10MarketOperatorCellIDCombo(firsttime, secondtime);
		}

		
		public Collection<Base_Data> getAllIMSI() {
			// TODO Auto-generated method stub
			return dao.getAllIMSI();
		}
		public Collection<Base_Data> getAllFailureID() {
			// TODO Auto-generated method stub
			return dao.getAllFailureID();
		}
		
		public Collection<Base_Data> getAllManufacturers() {
			// TODO Auto-generated method stub
			return dao.getAllManufacturers();
		}
		public Collection<Base_Data> getAllModels(String model) {
			// TODO Auto-generated method stub
			return dao.getAllModels(model);
		}
		
}

