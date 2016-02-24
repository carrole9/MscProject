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
	
		//addBaseData();
		/*Collection<Base_Data> baseDataSets= new ArrayList<Base_Data>();
		
		try {
			
		    FileInputStream file = new FileInputStream(new File("C:\\Users\\D14129133\\Desktop\\dataset.xls")); 
		    HSSFWorkbook workbook = new HSSFWorkbook(file);
		    HSSFSheet sheet = workbook.getSheet("Base Data");
		    Iterator<Row> rowIterator = sheet.iterator();
		    Row row = sheet.getRow(1);
		    int rowcount = 0;
		    while(rowIterator.hasNext()) {
		        row = rowIterator.next();
		        Iterator<Cell> cellIterator = row.cellIterator();
		        int count = 0;
		        
		        String dateTime=null;
		        int cellId=0;
		        int duration=0;		        
				String neVersion=null;
				String imsi = null;
				String hier3Id = null;
				String hier32Id = null;
				String hier321Id = null;
				int ueId=0;
				int failureId=0;
				int opId=0;
				int ecId=0;
				
				int market = 0;
				int operator=0;
				
				int event=0;
				int cause_code=0;
				
				if( rowcount>0){
		        while(cellIterator.hasNext()) {
		        	
			     
		            Cell cell = cellIterator.next();
		            
		           if(count ==0){
		        	cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		           // String temp=cell.getRichStringCellValue().getString();
		           // System.out.println(temp);
		           // DateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss",Locale.ENGLISH);
		           // format.setTimeZone((TimeZone.getTimeZone("America/Rosario")));
		          //  dateTime = (Date) format.parse(temp);
		           
		        	
		        	//java.util.Date temp=cell.getDateCellValue();
		        	//d=new java.sql.Date(temp.getTime());
		        	//dateTime = new Date(System.currentTimeMillis());
		          // System.out.println(dateTime);
		         //  dateTime=cell.getStringCellValue();
		           dateTime=cell.getDateCellValue().toString();
		            }
		           
		           if(count ==1){
			        	cell.setCellType(Cell.CELL_TYPE_STRING);
			        	 String temp=cell.getRichStringCellValue().getString();
				           int result = Integer.parseInt(temp);
				           event=result;
			          
			            }

		            if(count ==2){
		            	cell.setCellType(Cell.CELL_TYPE_STRING);
		            //	 if( row.getCell() == null ){
			            String temp=cell.getRichStringCellValue().getString();
			            if(!(temp.equals(null)||(temp.equals("(null)")))){
			           int result = Integer.parseInt(temp);
			           
			           failureId=result;}
		            }
		            if(count ==3){
		            	cell.setCellType(Cell.CELL_TYPE_STRING);
			            String temp=cell.getRichStringCellValue().getString();
			           int result = Integer.parseInt(temp);
			           ueId=result;
		            }
		            
		            if(count ==4){
		            	cell.setCellType(Cell.CELL_TYPE_STRING);
			            String temp=cell.getRichStringCellValue().getString();
			           int result = Integer.parseInt(temp);
			           market=result;
		            }
		            
		            if(count ==5){
		            	cell.setCellType(Cell.CELL_TYPE_STRING);
			            String temp=cell.getRichStringCellValue().getString();
			           int result = Integer.parseInt(temp);
			           operator=result;
		            }
		            if(count ==6){
		            	cell.setCellType(Cell.CELL_TYPE_STRING);
			            String temp=cell.getRichStringCellValue().getString();
			           int result = Integer.parseInt(temp);
			           cellId=result;
		            }
		            
		            
		            
		            if(count ==7){
		            	cell.setCellType(Cell.CELL_TYPE_STRING);
			            String temp=cell.getRichStringCellValue().getString();
			           int result = Integer.parseInt(temp);
			           duration=result;
		            }
		            
		            if(count ==8){
		            	cell.setCellType(Cell.CELL_TYPE_STRING);
			            String temp=cell.getRichStringCellValue().getString();
			            if(!(temp.equals("(null)")||(temp.equals("null")))){
			           int result = Integer.parseInt(temp);
			           cause_code=result;}
		            }
		        
		            if(count ==9){
		            	cell.setCellType(Cell.CELL_TYPE_STRING);
			           neVersion=cell.getRichStringCellValue().getString();;
		            }
		            if(count ==10){
		            	cell.setCellType(Cell.CELL_TYPE_STRING);
		            	imsi =cell.getRichStringCellValue().getString();
			         // imsi = new BigInteger(temp);
			       
		            }
		            if(count ==11){
		            	cell.setCellType(Cell.CELL_TYPE_STRING);
		            	hier3Id=cell.getRichStringCellValue().getString();
				          //hier3Id = new BigInteger(temp);    
		            }
		            if(count ==12){		 
				            	cell.setCellType(Cell.CELL_TYPE_STRING);
				            	hier32Id =cell.getRichStringCellValue().getString();
						        //  hier32Id = new BigInteger(temp);
		            }
		            if(count ==13){
				            	cell.setCellType(Cell.CELL_TYPE_STRING);
				            	hier321Id=cell.getRichStringCellValue().getString();
						          //hier321Id = new BigInteger(temp);
		            }

		           
		            count++;
		         
		        }
		    }
				String temp = market  + operator + "";
				opId=Integer.parseInt(temp);
				
				String temp1 = event + cause_code +"";
				ecId=Integer.parseInt(temp1);
				
				
				Base_Data data= new Base_Data(0,dateTime,cellId,duration,
						neVersion, imsi, hier3Id,
						hier32Id, hier321Id, ueId,
						failureId, opId, ecId);
		        if(ecId!=0&&event!=4099){
		        	
		        	System.out.print(data.getCellId());
		        	baseDataSets.add(data);
		        	//if(!dateTime.equals(null) || cellId != 0 || duration!= 0 ||!neVersion.equals(null)
		        	//		|| !imsi.equals(null) || !hier3Id.equals(null) || !hier32Id.equals(null) || !hier321Id.equals(null)
		        	//		|| ueId!=0 || failureId !=0 || opId!= 0 || ecId!=0){
		        	//service.addToBaseDatas(data);
		        	 print("dateTime: " +dateTime.toString() +" cellId: " + cellId + "  duration: " + duration + " neVersion: " + neVersion + " "
				        		+ " imsi: " + imsi + "  hier3Id: " +  hier3Id + " hier32Id: " +
								hier32Id + " hier321Id: " + hier321Id+ " ueId: " + ueId+ " failureId: " + 
								failureId+ " opId: " + opId+ " ecId: " + ecId);}//}
		        rowcount ++;
		       
		    }
		    file.close();
		 
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		try{
			//service.addBaseData(baseDataSets);
		}catch(Exception e){
			
		}*/
	
	
	
	
	
	
	public void addUserEquipment(){

	Collection<User_Equipment> user_equipment= new ArrayList<User_Equipment>();
	
	try {
		
	    FileInputStream file = new FileInputStream(new File("C:\\Users\\D14129133\\Desktop\\dataset.xls")); 
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
			
		    FileInputStream file = new FileInputStream(new File("C:\\Users\\D14129133\\Desktop\\dataset.xls")); 
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
			
		    FileInputStream file = new FileInputStream(new File("C:\\Users\\D14129133\\Desktop\\dataset.xls")); 
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
			
		    FileInputStream file = new FileInputStream(new File("C:\\Users\\D14129133\\Desktop\\dataset.xls")); 
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
	 	 String fileName=("C:\\Users\\D14129133\\Desktop\\debug.txt");
	 	 
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
	
	
	



		

	
