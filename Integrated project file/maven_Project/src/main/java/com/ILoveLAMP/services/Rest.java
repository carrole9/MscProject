package com.ILoveLAMP.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.ILoveLAMP.entities.Base_Data;
import com.ILoveLAMP.entities.Base_DataList;
import com.ILoveLAMP.entities.Event_Cause;
import com.ILoveLAMP.entities.Failure;
import com.ILoveLAMP.entities.Operator;
import com.ILoveLAMP.entities.User_Equipment;



@Path("/basedata")
public class Rest {

	@Inject
	private Base_DataService service;
	
	int composetkeyEventClause;
	int composetkeyOperator;

	public Rest() {}

	// http://localhost:8080/maven_Project/rest/basedata/getAll
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Base_DataList getBase_Datas() {
		Base_DataList datas = new Base_DataList();
		datas.setBaseDataCollection(service.getAllBaseDatas());
		return datas;
	}

	// http://localhost:8080/maven_Project/rest/basedata/findbyID/1
	@GET
	@Path("/findbyID/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Base_Data getDatabyID(@PathParam("id") int id){
		System.out.println(id);
		return service.getDatabyID(id);
		//return service.getDatabyID(id);
	}
	
	@Path("/base")
	public void populateDB(){
		addUserEquipment();
		addOperator1();
		addFailure();
		addCause_Event();}
	
		
	
	
	
	
	
	public void addUserEquipment(){

	Collection<User_Equipment> user_equipment= new ArrayList<User_Equipment>();
	
	try {
		
	    FileInputStream file = new FileInputStream(new File("/home/user1/conygreJEE/solutions/JavaEE6Workspace/maven_Project/dataset.xls")); 
	    HSSFWorkbook workbook = new HSSFWorkbook(file);
	    HSSFSheet sheet = workbook.getSheet("UE Table");
	    Iterator<Row> rowIterator = sheet.iterator();
	    Row row = sheet.getRow(1);
	    int rowcount = 0;
	    while(rowIterator.hasNext()) {
	        row = rowIterator.next();
	        Iterator<Cell> cellIterator = row.cellIterator();
	        int count = 0;
	        int userEquipmentId = 0;
	        String marketingName="null";
			String manufacturer="null";
			String accessCapability="null";
			String model="null";
			String vendorName="null";
			String ueType="null";
			String os="null";
			String inputMode="null";
			
			if( rowcount>0){
	        while(cellIterator.hasNext()) {
	        	
		     
	            Cell cell = cellIterator.next();
	            
	           if(count ==0){
	        	cell.setCellType(Cell.CELL_TYPE_STRING);
	            String temp=cell.getRichStringCellValue().getString();
	           int result = Integer.parseInt(temp);
	           	userEquipmentId=result;
	            }

	            if(count ==1){
	            	cell.setCellType(Cell.CELL_TYPE_STRING);
	            	marketingName=cell.getRichStringCellValue().getString();
	            }

	            if(count ==2){
	            	cell.setCellType(Cell.CELL_TYPE_STRING);
	            	manufacturer=cell.getRichStringCellValue().getString();
	            }
	            if(count ==3){
	            	cell.setCellType(Cell.CELL_TYPE_STRING);
	            	accessCapability=cell.getRichStringCellValue().getString();
	            }
	            if(count ==4){
	            	cell.setCellType(Cell.CELL_TYPE_STRING);
	            	model=cell.getRichStringCellValue().getString();
	            }
	            if(count ==5){
	            	cell.setCellType(Cell.CELL_TYPE_STRING);
	            	vendorName=cell.getRichStringCellValue().getString();
	            }
	            if(count ==7){
	            	cell.setCellType(Cell.CELL_TYPE_STRING);
	            	ueType=cell.getRichStringCellValue().getString();
	            }
	            if(count ==8){
	            	cell.setCellType(Cell.CELL_TYPE_STRING);
	            	os=cell.getRichStringCellValue().getString();
	            }
	            if(count ==9){
	            	cell.setCellType(Cell.CELL_TYPE_STRING);
	            	inputMode=cell.getRichStringCellValue().getString();
	            }
	            count++;
	         
	        }
	    }
	        User_Equipment eq= new User_Equipment(userEquipmentId,marketingName ,manufacturer,accessCapability, model, vendorName,ueType, os,inputMode);
	        if(userEquipmentId!=0){
	        user_equipment.add(eq);}
	        rowcount ++;
	    }
	    file.close();
	 
	     
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	
	
	service.addUser_Equipment(user_equipment);
	
}
	
	public void addOperator1(){

		Collection<Operator> operators= new ArrayList<Operator>();
		
		try {
			
		    FileInputStream file = new FileInputStream(new File("/home/user1/conygreJEE/solutions/JavaEE6Workspace/maven_Project/dataset.xls")); 
		    HSSFWorkbook workbook = new HSSFWorkbook(file);
		    HSSFSheet sheet = workbook.getSheet("MCC - MNC Table");
		    Iterator<Row> rowIterator = sheet.iterator();
		    Row row = sheet.getRow(1);
		    int rowcount = 0;
		    while(rowIterator.hasNext()) {
		        row = rowIterator.next();
		        Iterator<Cell> cellIterator = row.cellIterator();
		        int count = 0;
		        int opId; 
		        int mcc = 0;
		        int mnc = 0; 
		        String country = null;
				String operatorName = null;
				
				if( rowcount>0){
		        while(cellIterator.hasNext()) {
		        	
			     
		            Cell cell = cellIterator.next();
		            
		        

		            if(count ==0){
		            	cell.setCellType(Cell.CELL_TYPE_STRING);
			            String temp=cell.getRichStringCellValue().getString();
			           int result = Integer.parseInt(temp);
			           mcc=result;
		            }

		            if(count ==1){
		            	cell.setCellType(Cell.CELL_TYPE_STRING);
			            String temp=cell.getRichStringCellValue().getString();
			           int result = Integer.parseInt(temp);
			           mnc=result;
		            }
		            if(count ==2){
		            	cell.setCellType(Cell.CELL_TYPE_STRING);
		            	country=cell.getStringCellValue();
		            }
		            if(count ==3){
		            	cell.setCellType(Cell.CELL_TYPE_STRING);
		            	operatorName=cell.getRichStringCellValue().getString();
		            }
		            
		            count++;
		         
		        }
		    }
				String result=""+mcc+mnc;
				opId=Integer.parseInt(result);
				composetkeyOperator=opId;
				Operator op= new Operator(opId,mcc,mnc, country,operatorName);
		        if(opId!=0){
		       operators.add(op);}
		        rowcount ++;
		    }
		    file.close();
		 
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		
		service.addOperator(operators);
		
	}
	
	public void addFailure(){

		Collection<Failure> failures= new ArrayList<Failure>();
		
		try {
			
		    FileInputStream file = new FileInputStream(new File("/home/user1/conygreJEE/solutions/JavaEE6Workspace/maven_Project/dataset.xls")); 
		    HSSFWorkbook workbook = new HSSFWorkbook(file);
		    HSSFSheet sheet = workbook.getSheet("Failure Class Table");
		    Iterator<Row> rowIterator = sheet.iterator();
		    Row row = sheet.getRow(1);
		    int rowcount = 0;
		    while(rowIterator.hasNext()) {
		        row = rowIterator.next();
		        Iterator<Cell> cellIterator = row.cellIterator();
		        int count = 0;
		        int failureId=0;
		        String description = null;
				
				if( rowcount>0){
		        while(cellIterator.hasNext()) {
		        	
			     
		            Cell cell = cellIterator.next();
		            
		           if(count ==0){
		        	cell.setCellType(Cell.CELL_TYPE_STRING);
		            String temp=cell.getRichStringCellValue().getString();
		           int result = Integer.parseInt(temp);
		           failureId=result;
		            }

		            if(count ==1){
		            	cell.setCellType(Cell.CELL_TYPE_STRING);
		            	description=cell.getRichStringCellValue().getString();
		            }

		           
		            count++;
		         
		        }
		    }
		        Failure failure= new Failure(failureId,description);
		       // if(failureId!=0){
		        	failures.add(failure);//}
		        rowcount ++;
		    }
		    file.close();
		 
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		
		service.addFailure(failures);
		
	}
	
	public void addCause_Event(){

		Collection<Event_Cause> events= new ArrayList<Event_Cause>();
		
		try {
			
		    FileInputStream file = new FileInputStream(new File("/home/user1/conygreJEE/solutions/JavaEE6Workspace/maven_Project/dataset.xls")); 
		    HSSFWorkbook workbook = new HSSFWorkbook(file);
		    HSSFSheet sheet = workbook.getSheet("Event-Cause Table");
		    Iterator<Row> rowIterator = sheet.iterator();
		    Row row = sheet.getRow(1);
		    int rowcount = 0;
		    while(rowIterator.hasNext()) {
		        row = rowIterator.next();
		        Iterator<Cell> cellIterator = row.cellIterator();
		        int count = 0;
		        
		        int ecId=0;
		        int causeCode=0;
		        int eventId=0;
				String description=null;
				
				if( rowcount>0){
		        while(cellIterator.hasNext()) {
		        	
			     
		            Cell cell = cellIterator.next();
		            
		           if(count ==0){
		        	cell.setCellType(Cell.CELL_TYPE_STRING);
		            String temp=cell.getRichStringCellValue().getString();
		           int result = Integer.parseInt(temp);
		           causeCode=result;
		            }

		            if(count ==1){
		            	cell.setCellType(Cell.CELL_TYPE_STRING);
			            String temp=cell.getRichStringCellValue().getString();
			           int result = Integer.parseInt(temp);
			           eventId=result;
		            }
		            if(count ==2){
		            	cell.setCellType(Cell.CELL_TYPE_STRING);
		            	description=cell.getRichStringCellValue().getString();
		            }

		           
		            count++;
		         
		        }
		    }
				String result=""+eventId+causeCode;
				ecId=Integer.parseInt(result);
				composetkeyEventClause=ecId;
				Event_Cause event= new Event_Cause(ecId,causeCode,eventId,description);
		        if(ecId!=0){
		        events.add(event);}
		        rowcount ++;
		    }
		    file.close();
		 
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		
		service.addEventCause(events);
		
	}
	
	
	
	
	
	public void print(String text) throws IOException{
		
	    PrintWriter fileOut;
	 	 String fileName=("/home/user1/conygreJEE/solutions/JavaEE6Workspace/maven_Project/debug.txt");
	 	 
	 	try
	    {
	      

	      fileOut =  new PrintWriter(new FileWriter( fileName, true));

	      fileOut.println(text);

	      fileOut.close();
	    }//end try

	    catch (FileNotFoundException e)
	    {
	      System.out.println("Error: " + e.getMessage());
	    }//end catch


	  } // 

	}
	
	
	



		

	
