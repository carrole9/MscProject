package com.ILoveLAMP.services.users;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.codehaus.jackson.map.ObjectMapper;

import com.ILoveLAMP.entities.User;
import com.ILoveLAMP.entities.UserList;


	

	@Path("/users")
	public class UserCRUDService {
		public static String filePath = "/home/user1/conygreJEE/solutions/JavaEE6Workspace/maven_Project/src/main/webapp/Users.xls";
		
		@Inject
		private UserService service;
		
		static private String loginUserType;
		
		@Path("/checktype")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Integer checkUserType(User user) {
			
			String username = user.getUsername();
			String password = user.getPassword();
			loginUserType = service.getUserType(username, password);
			
			if(loginUserType.equals("admin") || loginUserType.equals("System Administrator"))
					return 1;
			else if(loginUserType.equals("customer service rep") || loginUserType.equals("Customer Service Rep"))
					return 2;
			else if(loginUserType.equals("support engineer") || loginUserType.equals("Support Engineer"))
					return 3;
			else if(loginUserType.equals("network management engineer") || loginUserType.equals("Network Management Engineer"))
					return 4;
			else
				return 0;
	    }

		@Path("/login")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public boolean login(User user) {
			String username = user.getUsername();
			String password = user.getPassword();
//			loginUserType = service.getUserType(username, password);
//			System.out.println(UserCRUDService.loginUserType);
			return service.login(username, password);
		}
		

	    
//	    @Path("/all")
		@GET
	    @Produces(MediaType.APPLICATION_JSON)
	    public UserList getUsers() {
	        UserList users = new UserList();
	        users.setDiscCollection(service.getAllUsers());	        
	        return users;
	    }
	    
		
		@Path("/add")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public void addUser(User user) {
			System.out.println("in CRUD" + user.getId() + user.getUsername()+ user.getPassword() + user.getUsertype());
			user.setId(0); // make sure the ID is not set
			service.addToUsers(user);
		}
		
		@Path("/checkUserExistence")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
		public boolean checkUserExistence(String username){
			return service.checkUserExistence(username);
		}
		


/*		@GET
		@Path("/type{type}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public UserList getUsersByType(@PathParam("type") String type) {
	        UserList users = new UserList();
	        users.setDiscCollection(service.getUserByType(type));
	        return users;
	    
		}
		
		@DELETE
	    @Path("/delete{id}")
		public void deleteUser(@PathParam("id") int id) {
			service.deleteUsers(id);
		}
		
		@GET
		@Path("/id{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public User getUsersByid(@PathParam("id") int id) {
	        //UserList users = new UserList();
	        User user=service.getUserById(id);
	        return user;
	    
		}
		
		@GET
		@Path("/userid{name}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public int getidByUserName(@PathParam("name") String name) {
	        //UserList users = new UserList();
	        User user=service.getIdByUsername(name);
			//int id = service.getIdByUsername(name);
	        return user.getId();
	    
		}
		
		@GET
		@Path("/useridfrompasswd{passwd}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public int getidByPassword(@PathParam("passwd") String passwd) {
	        //UserList users = new UserList();
	        User user=service.getIdByPassword(passwd);
			//int id = service.getIdByUsername(name);
	        return user.getId();
	    
		}
		
		@GET
		@Path("/admin{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public User isadmin(@PathParam("id") int id) {
			  User user=service.getUserById(id);
		      if(user.getUsertype().equals("admin")){
		    	  return user;
		      }
		      
		      return null;
	    
		}*/


	

}
