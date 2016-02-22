package dao.jpa;


	import java.util.Collection;
import java.util.List;

	import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

	import dao.UserDAO;
import entities.User;


	@Stateless
	@Local
	public class JPAUserDAO implements UserDAO {

		@PersistenceContext
		private EntityManager em;
		
//		public JPAUserDAO() {
//
//			//factory = Persistence.createEntityManagerFactory("conygrePersistentUnit");
//		}
		
//		private EntityManager getEntityManager() {
//			//EntityManager em = factory.createEntityManager();
//			return em;
//		}
		
		public void addUser(User user) {
			System.out.println("IN userDAO" + user.getId() + user.getUsername()+ user.getPassword() + user.getUsertype());

			Query query = em.createQuery("from User");
			
//			User newUser = new User(user.getUsername(), user.getPassword(), user.getUsertype(),  user.getId());
			List<User> users = query.getResultList(); 
			if (!users.contains(user))
			
				 em.persist(user);
			
			
		}
		
		private void closeEntityManager(EntityManager em) {
			// TODO Auto-generated method stub
			em.close();
		}


		


		public Collection<User> getUserByType(String usertype) {

			Query query  = em.createQuery("from User user where user.usertype = :usertype");
			query.setParameter("usertype", usertype);
			
			List<User> result = query.getResultList();
			return result;
		}


		public Collection<User> getAllUsers() {

			Query query = em.createQuery("from User");
			List<User> users = query.getResultList(); 
			return users;
		}


		public void deleteUser(int id) {
			//Query query  = em.createQuery("from User user where user.id = :id");
			//query.setParameter("id", id);
		
			//User result = (User) query.getSingleResult();
			//Entity same = em.find(null, User); 
			User user= em.find(User.class, 7);
			em.remove(user);
			
		}


		public User getUserIdByName(String username) {
			Query query  = em.createQuery(" from User user where user.username = :username");
			
			query.setParameter("username", username);			
			User result = (User) query.getSingleResult();
			return result;
		}
		
		public User getUserIdByPassword(String password) {
			Query query  = em.createQuery(" from User user where user.password = :password");
			
			query.setParameter("password", password);			
			User result = (User) query.getSingleResult();
			return result;
		}
		
		public User getUserById(int id) {
			
			Query query  = em.createQuery("from User user where user.id = :id");
			query.setParameter("id", id);
		
			User result = (User) query.getSingleResult();
			return result;
		}





		


	}
		
	
	

