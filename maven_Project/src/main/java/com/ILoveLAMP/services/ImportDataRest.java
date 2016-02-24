package com.ILoveLAMP.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Path;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.ILoveLAMP.entities.Base_Data;
import com.ILoveLAMP.entities.Event_Cause;
import com.ILoveLAMP.entities.Failure;
import com.ILoveLAMP.entities.Operator;
import com.ILoveLAMP.entities.User_Equipment;

@Path("/data")
public class ImportDataRest {

	

	@Inject
	private Base_DataService service;
	
	int composetkeyEventClause;
	int composetkeyOperator;
	Collection<Base_Data> baseDataSets= new ArrayList<Base_Data>();
	
	@PersistenceContext
	EntityManager em;
	
	public ImportDataRest(){}
	
	@Path("/add")
	public void populateDB() throws IOException{
		 addBase();
		 }


	
	public void addBase() throws IOException{
    
			
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
				
				int check=0;
				
				String market = null;
				String operator=null;
				
				String event=null;
				String cause_code=null;
				print("in row" + rowcount);
				if( rowcount>0){	
		        while(cellIterator.hasNext()) {
		        	
			     
		            Cell cell = cellIterator.next();
		            
		           if(count ==0){
		        	cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		           dateTime=cell.getDateCellValue().toString();
		            }   
		          if(count ==1){
			        	cell.setCellType(Cell.CELL_TYPE_STRING);
			        	 String temp=cell.getRichStringCellValue().getString();
				           int result = Integer.parseInt(temp);
				           event=temp;
				           check=result;
			       }
		            if(count ==2){
		            	cell.setCellType(Cell.CELL_TYPE_STRING);		            	
			            String temp=cell.getRichStringCellValue().getString();
			            
			            if(temp.contains("(null)")||temp.length()>2){
			            	
			            }
			            else if(temp.contains("0")){
			            	failureId=0;
			            }
			            else{
			            	int result = Integer.parseInt(temp);
					           failureId=result;
			            }
			            
			            print(temp);
//			            if(!(temp.equals("null")||!(temp.equals("(null)"))||!temp.isEmpty())){
//			           int result = Integer.parseInt(temp);
//			           failureId=result;}
			           
			            
			            //print("hey");
		            }
		            if(count ==3){
		            	cell.setCellType(Cell.CELL_TYPE_STRING);
			            String temp=cell.getRichStringCellValue().getString();
			            if(!(temp.equals("null")||(temp.equals("(null)"))||temp.isEmpty())){
			           int result = Integer.parseInt(temp);
			           ueId=result;}
		            }
		            
		            if(count ==4){
		            	cell.setCellType(Cell.CELL_TYPE_STRING);
			            String temp=cell.getRichStringCellValue().getString();
			          // int result = Integer.parseInt(temp);
			           market=temp;
		            }
		            
		            if(count ==5){
		            	cell.setCellType(Cell.CELL_TYPE_STRING);
			            String temp=cell.getRichStringCellValue().getString();
			          // int result = Integer.parseInt(temp);
			           operator=temp;
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
			            if(!(temp.equals("(null)")||(temp.equals("null"))||temp.isEmpty())){
			          // int result = Integer.parseInt(temp);
			           cause_code=temp;}
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
				try{
				String temp = market  + operator + "";
				if(!temp.contains("null")){
				opId=Integer.parseInt(temp);}
				
				String temp1 = event + cause_code +"";
				if(!temp.contains("null")){
				ecId=Integer.parseInt(temp1);}
				}catch(NumberFormatException e){}
				if(rowcount>0&& check!=4099){
				print(dateTime+ "  "+ "  " +cellId+ "  "+ duration+ "  "+neVersion+ "  "+imsi+ "  "+ hier3Id+ "  "+hier32Id+ "  " +hier321Id+ "  "+ueId+ "  "+failureId+ "  "+opId+ "  "+ecId); 
				//print();
				Base_Data data= new Base_Data(0, dateTime, cellId, duration, neVersion, imsi, hier3Id, hier32Id, hier321Id, em.find(Failure.class, failureId), em.find(Event_Cause.class, ecId), em.find(Operator.class, opId),em.find(User_Equipment.class, ueId));
				service.addToBaseDatas(data);
				}
				/*data.setDataId(0);
				data.setDateTime(dateTime);
				data.setCellId(cellId);
				data.setDuration(duration);
				data.setNeVersion(neVersion);
				data.setImsi(imsi);
				data.setHier3Id(hier3Id);
				data.setHier32Id(hier32Id);
				data.setHier321Id(hier321Id);
				data.setFailure(em.find(Failure.class, failureId));
				data.setEventCause(em.find(Event_Cause.class, ecId));
				data.setOperator(em.find(Operator.class, opId));
				data.setUserEquipment(em.find(User_Equipment.class, ueId));*/
				//System.out.print(em.find(Event_Cause.class, 40970).getEcId());
				//service.addToBaseDatas(data);
				
		       // if(ecId!=0&&check!=4099){
//		        	Base_Data data= new Base_Data(0, dateTime, cellId, duration, neVersion, imsi, hier3Id, hier32Id, hier321Id, em.find(Failure.class, failureId), em.find(Event_Cause.class, ecId), em.find(Operator.class, opId),em.find(User_Equipment.class, ueId));
		        	//service.addToBaseDatas(data);
		        	//System.out.print(em.find(Event_Cause.class, 40970).getEcId());
		        	//print(data.getDataId() + " " ); 
		        	//baseDataSets.add(data);
		       // rowcount ++;
		       
		  //  }
		        rowcount ++; 
		        service.addBaseData(baseDataSets);
		    file.close();
		 
		    }  } 
	
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
