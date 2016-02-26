package com.ILoveLAMP.dao.jpa;

import java.util.Collection;
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
import com.ILoveLAMP.entities.User_Equipment;



@Stateless
@Local
public class JPABase_DataDAO implements Base_DataDAO{

	@PersistenceContext
	EntityManager em;


	public void addBasicData(Base_Data data) {
		Query query = em.createQuery("from Base_Data");
		List<Base_Data> datas = query.getResultList(); 
		//if (!datas.contains(data))
			em.merge(data);
	}

	public Collection<Base_Data> getAllBaseDatas() {
	//	Query query = em.createQuery("from Base_Data");
		Query query = em.createNamedQuery("Base_Data.getAll");
		@SuppressWarnings("unchecked")
		List<Base_Data> datas = query.getResultList(); 

		return datas;
	}
	
	public Base_Data getBaseDataById(int id) {
	//	Query query  = em.createQuery("select bd from Base_Data bd where bd.dataId=:id");
		Query query = em.createNamedQuery("Base_Data.findById");
		query.setParameter("id", id);
		return (Base_Data) query.getSingleResult();
	}

	
	public void addUser_Equipment(Collection<User_Equipment> eq) {
		Query query = em.createQuery("from User_Equipment");
		
		for(User_Equipment ue:eq){
			em.merge(ue);
		}
		
		
	}

	
	public void addOperator(Collection<Operator> op) {
    Query query = em.createQuery("from Operator");
		
		for(Operator operator:op){
			em.merge(operator);
		}
		
	}

	public void addFailure(Collection<Failure> failures) {
    Query query = em.createQuery("from Failure");
		
		for(Failure fail:failures){
			em.merge(fail);
		}
		
	}


	public void addEventCause(Collection<Event_Cause> events) {
		 Query query = em.createQuery("from Event_Cause");
			
			for(Event_Cause event:events){
				em.merge(event);
			}
		
	}

	@Override
	public void addBaseData(Collection<Base_Data> data) {
			 Query query = em.createQuery("from Base_Data");
				
				for(Base_Data basedata:data){
					em.merge(basedata);
					System.out.print(basedata);
				}
			
		}

	@Override
	public void addErrorData(Collection<Error_Data> data) {
		for(Error_Data errorData: data){
			em.merge(errorData);
		}
		
	}
		
	}


	
		
	


