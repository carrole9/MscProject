package services;

import java.util.Collection;

import javax.ejb.Local;

import entities.User;

@Local
public interface UserService {
	
	public Collection<User> getAllUsers() ;
	public void addToUsers(User user);
	public Collection<User> getUserByType(String type);
	public User getUserById(int id);
	public void deleteUsers(int id);
	public User getIdByUsername(String name);
	public User getIdByPassword(String passwd);
	void loadUsersFromExcel();
	public boolean login(String username, String password);
	public String getUserType(String username, String password);
	
	}


