package com.ILoveLAMP.dao.jpa;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ILoveLAMP.dao.Base_DataDAO;
import com.ILoveLAMP.entities.Base_Data;
import com.ILoveLAMP.entities.Error_Data;
import com.ILoveLAMP.entities.Event_Cause;
import com.ILoveLAMP.entities.Failure;
import com.ILoveLAMP.entities.Operator;
import com.ILoveLAMP.entities.User;
import com.ILoveLAMP.entities.User_Equipment;



@Stateless
@Local
public class JPABase_DataDAO implements Base_DataDAO{

	@PersistenceContext
	EntityManager em;

	public void addUser_Equipment(Collection<User_Equipment> eq) {
		
		for(User_Equipment ue:eq){
			em.merge(ue);
		}
		
		
	}

	
	public void addOperator(Collection<Operator> op) {
		
		for(Operator operator:op){
			em.merge(operator);
		}
		
	}

	public void addFailure(Collection<Failure> failures) {
		
		for(Failure fail:failures){
			em.merge(fail);
		}
		
	}


	public void addEventCause(Collection<Event_Cause> events) {
			
			for(Event_Cause event:events){
				em.merge(event);
			}
		
	}

	public void addBaseData(Collection<Base_Data> data) {
				
				for(Base_Data basedata:data){
					em.merge(basedata);
				}
			
		}

	
	public void addErrorData(Collection<Error_Data> data) {
		for(Error_Data errorData: data){
			em.merge(errorData);
		}
		
	}

	
	public Collection<Base_Data> getDatabyTime(Date time) {
		Query query = em.createNamedQuery("Base_Data.findByTime");
		query.setParameter("time", time);
		@SuppressWarnings("unchecked")
		List<Base_Data> datas = query.getResultList(); 

		return datas;
	}
	
	//select bd from Base_Data bd where bd.dateTime=:time")
	
	
	public Collection<Base_Data> getIMSIandCallFailures(Date firsttime, Date secondtime) {
		Query query = em.createNamedQuery("Base_Data.IMSIandCallFailuresfindByTime");
		query.setParameter("firsttime", firsttime);
		query.setParameter("secondtime", secondtime);
		@SuppressWarnings("unchecked")
		List<Base_Data> datas = query.getResultList();  

		return datas;
	}

	
	public Collection<Base_Data> getEventandCausecodeByIMSI(String imsi) {
		Query query = em.createNamedQuery("Base_Data.EventandCausecodeByIMSI");
		query.setParameter("imsi", imsi);
		@SuppressWarnings("unchecked")
		List<Base_Data> datas = query.getResultList();  
		return datas;
	}

	
	public Long getFailurebyTimeandIMSI(Date firsttime, Date secondtime, String imsi) {
		Query query = em.createNamedQuery("Base_Data.FailurebyTimeandIMSI");
		query.setParameter("firsttime", firsttime);
		query.setParameter("secondtime", secondtime);
		query.setParameter("imsi", imsi);
		Long datas =  (Long) query.getSingleResult();  

		return datas;
	}

	
	
	
	public Integer getNoOfFailuresByPeriodAndModel(Date startTime,
			Date endTime, String model) {
		Number number;
		Query query = em.createNamedQuery("Base_Data.findByPeriodAndModel");
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);
		query.setParameter("userEquipment", getUserEquipmentByModel(model));
		number = (Number)query.getSingleResult();
		return number.intValue();
	}
	
	public User_Equipment getUserEquipmentByModel(String model) {
		Query query = em.createNamedQuery("User_Equipment.findUserEquipmentByModel");
		query.setParameter("model", model);
		return (User_Equipment)query.getSingleResult();
	}



	public List<String> getIMSIbyId(Integer failId) {
		Query query = em.createNamedQuery("Base_Data.findIMSIbyFailureID");
		query.setParameter("failId", failId);
		@SuppressWarnings("unchecked")
		List<String> datas = (List<String>) query.getResultList();
		
		if(datas.size() == 0)
			return null;
		else
			return datas;
	}
	
	
	public List<Object[]> getTop10ImsisByDate(Date startDate, Date endDate) {
		Query query = em.createNamedQuery("Base_Data.findTop10IMSIinDateRange");
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		@SuppressWarnings("unchecked")
		List<Object[]> datas = (List<Object[]>) query.getResultList();
		int size = datas.size(); 
		
		// sort datas by count (high to low.)
		Collections.sort(datas, new Comparator<Object[]>() {
			@Override
			public int compare(Object[] object1, Object[] object2) {
//				System.out.println("Object 1 " + object1[0] + " "+ object1[1]);
//				System.out.println("Object 2 " + object2[0] + " "+ object2[1]);
//				System.out.println("Result = "+((Long) object2[1]).compareTo( (Long) object1[1]));
				// obj1.compareTo(obj2) will compare by low to high
				// obj2.compareTo(obj1) will compare by high to low.
				return ((Long) object2[1]).compareTo( (Long) object1[1]);
			}
		});
		
		if(size >= 10){
			return datas.subList(0, 10);
		} else if (size >= 1 && size < 10) {
			return datas.subList(0, size);
		} else {
			return null;
		}
	}



	public Integer getTotalDurationByPeriodAndImsi(Date startTime,
			Date endTime, String imsi) {		
			Number number;
			Query query = em.createNamedQuery("Base_Data.getTotalDurationByPeriodAndImsi");
			query.setParameter("startTime", startTime);
			query.setParameter("endTime", endTime);
			query.setParameter("imsi", imsi);
			number = (Number)query.getSingleResult();
			return number.intValue();		
		}
	

	public Collection<String> getImsiByPeriod(Date startTime, Date endTime) {
		Query query = em.createNamedQuery("Base_Data.findImsiByPeriod");
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);
		@SuppressWarnings("unchecked")
		List<String> datas = query.getResultList();
		return datas;
	}
	
	public List<Object[]> getNoOfFailuresTotalDurationsForEachImsiByPeriod(Date startTime, Date endTime) {
		
		Query query = em.createNamedQuery("Base_Data.getNoOfFailuresTotalDurationsForEachImsiByPeriod");		
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime).getResultList();	
		@SuppressWarnings("unchecked")
		List<Object[]> base_Data = query.getResultList();
		
		if (base_Data.size() == 0)
			return null;
		else 
			return base_Data;
	}
	
	@Override
	public void addUser(User user) {
		em.merge(user);
	}
	@Override
	public User getUserById(int id) {
		Query query = em.createNamedQuery("User.findById");
		query.setParameter("id", id);
		return (User) query.getSingleResult();
	}
	@Override
	public Collection<User> getAllUsers() {
		Query query = em.createNamedQuery("User.getAll");
		@SuppressWarnings("unchecked")
		List<User> datas = query.getResultList();
		return datas;
	}
	@Override
	public void removeUser(User user) {
		em.createQuery("DELETE FROM User us WHERE us.id=:id")
		.setParameter("id", user.getId())
		.executeUpdate();
	}

	public void addBasicData(Base_Data data) {
		em.merge(data);
	}
	public Collection<Base_Data> getAllBaseDatas() {
		Query query = em.createNamedQuery("Base_Data.getAll");
		@SuppressWarnings("unchecked")
		List<Base_Data> datas = query.getResultList();

		return datas;
	}
	public Base_Data getBaseDataById(int id) {
		Query query = em.createNamedQuery("Base_Data.findById");
		query.setParameter("id", id);
		return (Base_Data) query.getSingleResult();
	}
	@Override
	public void removeBase_Data(Base_Data data) {
		em.createQuery("DELETE FROM Base_Data bd WHERE bd.dataId=:id")
		.setParameter("id", data.getDataId())
		.executeUpdate();
		//em.createNativeQuery("ALTER TABLE Base_Data AUTO_INCREMENT = 1").executeUpdate();
		//em.remove(em.find(Base_Data.class, data.getDataId()));
	}
	
	// Failure JPA DAO
		@Override
		public void addFailure(Failure failure) {
			//em.merge(failure);
			em.persist(em.merge(failure));
		}
		@Override
		public Failure getFailureById(int id) {
			Query query = em.createNamedQuery("Failure.findById");
			query.setParameter("id", id);
			return (Failure) query.getSingleResult();
		}
		@Override
		public Collection<Failure> getAllFailures() {
			Query query = em.createNamedQuery("Failure.getAll");
			@SuppressWarnings("unchecked")
			List<Failure> datas = query.getResultList();
			return datas;
		}
		@Override
		public void removeFailure(Failure failure) {
			em.remove(em.merge(failure));
		}

		@Override
		public void addEvent_Cause(Event_Cause eventCause) {
			em.persist(em.merge(eventCause));
			
		}
		@Override
		public Event_Cause getEvent_CauseById(int id) {
			Query query = em.createNamedQuery("EventCause.findById");
			query.setParameter("id", id);
			return (Event_Cause) query.getSingleResult();
		}
		@Override
		public Collection<Event_Cause> getAllEvent_Causes() {
			Query query = em.createNamedQuery("EventCause.getAll");
			@SuppressWarnings("unchecked")
			List<Event_Cause> datas = query.getResultList();
			return datas;
		}
		@Override
		public void removeEvent_Cause(Event_Cause eventCause) {
			em.remove(em.merge(eventCause));
		}

		@Override
		public void addUser_Equipment(User_Equipment ue) {
			em.persist(em.merge(ue));
		}
		@Override
		public User_Equipment getUser_EquipmentById(int id) {
			Query query = em.createNamedQuery("User_Equipment.findById");
			query.setParameter("id", id);
			return (User_Equipment) query.getSingleResult();
		}
		@Override
		public Collection<User_Equipment> getAllUser_Equipments() {
			Query query = em.createNamedQuery("User_Equipment.getAll");
			@SuppressWarnings("unchecked")
			List<User_Equipment> datas = query.getResultList();
			return datas;
		}
		@Override
		public void removeUser_Equipment(User_Equipment ue) {
			em.remove(em.merge(ue));
		}

		
		@Override
		public void addOperator(Operator operator) {
			em.persist(em.merge(operator));
		}
		@Override
		public Operator getOperatorById(int id) {
			Query query = em.createNamedQuery("Operator.findById");
			query.setParameter("id", id);
			return (Operator) query.getSingleResult();
		}
		@Override
		public Collection<Operator> getAllOperators() {
			Query query = em.createNamedQuery("Operator.getAll");
			@SuppressWarnings("unchecked")
			List<Operator> datas = query.getResultList();
			return datas;
		}
		@Override
		public void removeOperator(Operator operator) {
			em.remove(em.merge(operator));
		}

		@Override
		public void addErrorData(Error_Data data) {
			em.merge(data);
		}
		@Override
		public Error_Data getErrorDataById(int id) {
			Query query = em.createNamedQuery("Error_Data.findById");
			query.setParameter("id", id);
			return (Error_Data) query.getSingleResult();
		}
		@Override
		public Collection<Error_Data> getAllErrorDatas() {
			Query query = em.createNamedQuery("Error_Data.getAll");
			@SuppressWarnings("unchecked")
			List<Error_Data> datas = query.getResultList();
			return datas;
		}
		@Override
		public void removeErrorData(Error_Data data) {
			em.createQuery("DELETE FROM Error_Data ed WHERE ed.dataId=:id")
			.setParameter("id", data.getDataId())
			.executeUpdate();
		}
		
		public List<Object[]> getUniqueCauseCodesForIMSI(String imsi) {
	        Query query = em.createNamedQuery("Base_Data.UniqueCauseCodesForIMSI");
	        query.setParameter("imsi", imsi);
	        @SuppressWarnings("unchecked")
	        List<Object[]> datas = query.getResultList();
	 
	        return datas;		
		}
		
		public List<Object[]> getFailureCauseCodeAndOccurrences(String model) {
			Query query = em.createNamedQuery("Base_Data.FailureCauseCodeAndOcurrences");
			query.setParameter("model", model);
			
			@SuppressWarnings("unchecked")
			List<Object[]> datas = query.getResultList();  
			return datas;
		}
		
		
		public List<Object[]> top10MarketOperatorCellIDCombo(Date firsttime, Date secondtime) {
			Query query = em.createNamedQuery("Base_Data.Top10MarketOperatorCellIdCombo");
			query.setParameter("firsttime", firsttime);
			query.setParameter("secondtime", secondtime);
			@SuppressWarnings("unchecked")
			List<Object[]> datas = query.getResultList();  

			return datas;
		}


		@Override
		public Collection<Base_Data> getAllIMSI() {
			Query query = em.createNamedQuery("Base_Data.getAllIMSI");
			@SuppressWarnings("unchecked")
			List<Base_Data> datas = query.getResultList();

			return datas;
		}
}


	
		
	


