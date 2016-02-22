package jaxrs;

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

import dao.UserDAO;
import entities.User;
import entities.UserList;
import services.UserService;



	@Path("/users")
	public class UserCRUDService {
		

		@Inject
		private UserService service;
		
	
		

	    
	   // @Path("/all")
		@GET
	    @Produces(MediaType.APPLICATION_JSON)
	    public UserList getUsers() {
	        UserList users = new UserList();
	        users.setDiscCollection(service.getAllUsers());
	        

	        
//	        for(User user : users.getUserCollection()){
//	        	System.out.println(user.getUsername());	        	
//	        }
	        
	        return users;
	    }
	    
		
	//	@Path("/add")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public void addUser(User user) {
			System.out.println("in CRUD" + user.getId() + user.getUsername()+ user.getPassword() + user.getUsertype());
			user.setId(0); // make sure the ID is not set
			service.addToUsers(user);

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
