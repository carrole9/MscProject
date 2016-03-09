package com.ILoveLAMP.dao;


import java.util.Collection;

import javax.ejb.Local;

import com.ILoveLAMP.entities.User;


	@Local
	public interface UserDAO {
		
		void addUser(User user);
		User getUserById(int id);
		Collection<User> getUserByType(String userType);
		Collection<User> getAllUsers();
		void deleteUser(int id);
		User getUserIdByName(String name);
		User getUserIdByPassword(String passwd);
		void loadUsersFromExcel();
		


}
