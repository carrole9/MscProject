package jaxrs;

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

import dao.UserDAO;
import entities.User;
import entities.UserList;
import services.UserService;

	

	@Path("/users")
	public class UserCRUDService {
		public static String filePath = "/home/user1/conygreJEE/solutions/JavaEE6Workspace/story3/src/main/webapp/Users.xls";
		
		@Inject
		private UserService service;
		
		static private String loginUserType;
		
		@Path("/selecttype")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public boolean verifyUserType(String userType) {
			boolean verified = false;
			System.out.println(UserCRUDService.loginUserType + " in the get method");
			if(this.loginUserType.equals(userType))
				verified = true;
	        return verified;
	    }

		@Path("/login")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public boolean login(User user) {
			String username = user.getUsername();
			String password = user.getPassword();
			loginUserType = service.getUserType(username, password);
			System.out.println(UserCRUDService.loginUserType);
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
	    
		
//		@Path("/add")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public void addUser(User user) {
			System.out.println("in CRUD" + user.getId() + user.getUsername()+ user.getPassword() + user.getUsertype());
			user.setId(0); // make sure the ID is not set
			service.addToUsers(user);
		}
		
		@Path("/excel")
		public void loadUsersFromExcel() {
			System.out.println("in the excel crud");
//			service.loadUsersFromExcel();
			Collection<User> users = new ArrayList<User>();
			
	    	   try {
	               // Create input stream for EXCEL
	               HSSFWorkbook wookbook = new HSSFWorkbook(new FileInputStream(filePath));
	               // the default indexing of 1st sheet in Excel is "0"
	               // The syntax is：HSSFSheet sheet = workbook.getSheetAt(0);
	               HSSFSheet sheet = wookbook.getSheet("Sheet1");
	               // getting the number of rows in the Excel file
	               int rows = sheet.getPhysicalNumberOfRows();
	               System.out.println(rows+"rows");
	               // scan through all the rows
	               for (int i = 0; i < rows; i++) {
	                     // read the top left unit cell
	                     HSSFRow row = sheet.getRow(i);
	                     // if the row is not empty
	                     if (row != null) {
	                           // getting all the columns in the Excel file
	                           int cells = row.getPhysicalNumberOfCells();
	                           System.out.println(cells+"columns");
	                           String value = "";
	                           // scan through all the columns
	                           for (int j = 0; j < cells; j++) {
	                                 // get the data of the cell
	                                 HSSFCell cell = row.getCell(j);
	                                 if (cell != null) {
	                                       switch (cell.getCellType()) {
	                                             case HSSFCell.CELL_TYPE_NUMERIC:
	                                                   value += cell.getNumericCellValue() + ",";
	                                             break; 
	                                             case HSSFCell.CELL_TYPE_STRING:
	                                                   value += cell.getStringCellValue() + ",";
	                                             break;
//	                                             default:
//	                                                   value += "0";
//	                                             break;
	                                 }

	                                 }  
	                     }
	                     
	                     
	                     
	                     String[] val = value.split(",");
	                     System.out.println(val.length);
	                     for(String string : val){
	                    	 System.out.println(string);
	                     }
	                     
	                     double doubleId = Double.parseDouble(val[0]);
	                     int intId=(int)doubleId;
	                     System.out.println(intId);
//	                  // load data into mysql database
	                     User user = new User();
//	                  //now conveting the data
	                     user.setId(0);
	                     user.setUsername(val[1]);
	                     user.setPassword(val[2]);
	                     user.setUsertype(val[3]);
	                     
	                     System.out.println(user.getId()+user.getPassword()+user.getUsername()+user.getUsertype());
//	                     usersFromExcel.add(user);
	                     service.addToUsers(user);
	                     
	                     }
	                     
	                     
	                     
	               }
	    	   }catch (FileNotFoundException e) {
	               e.printStackTrace();
	           }catch (IOException e) {
	               e.printStackTrace();
	         }
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
