package services;


	import java.util.Collection;

	import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.jws.WebService;

import dao.UserDAO;
import entities.User;
	@Stateless
	@Local
	@TransactionAttribute (TransactionAttributeType.REQUIRED)
	public class UserServiceEJB implements UserService {
		
			
		@EJB // uses reflection
		private UserDAO dao;
		
//		@Resource
//		private SessionContext context;

		public void setDao(UserDAO dao) {
			// do something really important on injection
			this.dao = dao;
		}



		public Collection<User> getAllUsers() {
			return dao.getAllUsers();
		}


		public void addToUsers(User user) {
			 System.out.println("IN SERVICE" + user.getId() + user.getUsername()+ user.getPassword() + user.getUsertype());
			 dao.addUser(user);
		}
		
		public Collection<User> getUserByType(String type) {
			return dao.getUserByType(type);
			
		}



		public void deleteUsers(int id) {
			dao.deleteUser(id);
			
		}



		public User getUserById(int id) {
			return dao.getUserById(id);
		}



		public User getIdByUsername(String name) {
			return  dao.getUserIdByName(name);
		}
		
		public User getIdByPassword(String passwd) {
			return  dao.getUserIdByPassword(passwd);
		}



		


	

}
