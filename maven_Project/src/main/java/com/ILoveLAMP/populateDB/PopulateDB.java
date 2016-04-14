package com.ILoveLAMP.populateDB;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.ILoveLAMP.entities.Base_Data;
import com.ILoveLAMP.entities.Error_Data;
import com.ILoveLAMP.entities.Event_Cause;
import com.ILoveLAMP.entities.Failure;
import com.ILoveLAMP.entities.Operator;
import com.ILoveLAMP.entities.User_Equipment;
import com.ILoveLAMP.services.Base_DataService;

@Path("/database")
@Stateless
public class PopulateDB {

	@Inject
	private Base_DataService service;

	@PersistenceContext
	EntityManager em;

	private String path = "/home/shanu/Desktop/Data/dataset.xls";
	private String file ="";

	int composetkeyEventClause;
	int composetkeyOperator;
	
	public PopulateDB() {
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
		try {
			populateDB();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	

	
	@POST
	@Path("/AdminAddData")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
		public void adminAddData(String path){
		System.out.println(path);
		path = path.replace("\"", "");
		setFile(path);
		System.out.println(path);
		this.path = path;
		try {
			populateDB();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return "Success";
	}
	
	
	
	@Asynchronous
	public void watch() throws ParseException{
			
			System.out.println("Watcher started");
			
			try(WatchService watcher = FileSystems.getDefault().newWatchService()){
				java.nio.file.Path dir = Paths.get("/home/shanu/Desktop/Data");
				dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
				
				Runtime.getRuntime().addShutdownHook(new Thread(){
					public void run(){
					try{
						watcher.close();}
					catch(IOException e){
						System.out.print("It ain't working");
					}
					}});
				
				System.out.println("Watch Service registered for dir: " + dir.getFileName());
				setFile(""+dir.getFileName());
				
				
				while (true) {
					WatchKey key;
					
					try {
						key = watcher.take();
					} catch (InterruptedException ex) {
						break;
					}
					
					for (WatchEvent<?> event : key.pollEvents()) {
						WatchEvent.Kind<?> kind = event.kind();
						
						@SuppressWarnings("unchecked")
						WatchEvent<java.nio.file.Path> ev = (WatchEvent<java.nio.file.Path>) event;
						java.nio.file.Path fileName = ev.context();
						
						System.out.println(kind.name() + ": " + fileName);
						System.out.print(kind.name());
						if(kind.name().equals("ENTRY_CREATE")){
							System.out.println("new data to add to db "+dir+"/"+fileName);
							String file=""+dir+"/"+fileName;
							System.out.println(file);
						//	populateDB(file);
						}
						
						if (kind == ENTRY_MODIFY) {
							System.out.println("My source file has changed!!!");
							String file=""+dir+"/"+fileName;
							System.out.println(file);
							setPath(file); 
						}
					}
					
					boolean valid = key.reset();
					if (!valid) {
						break;
					}
				} 
				
				
			} catch (IOException ex) {
				System.err.println(ex);
			}
			
			
			
		
	
	}
	
	/* public void doMove() {
		 	System.out.println("Moving");
	        // File (or Directory) to be moved
	        File file = new File(getFile());

	        // Destination directory
	        File dir = new File("/home/shanu/Desktop/Data/");

	        // Move file to a new directory
	        boolean success = file.renameTo(new File(dir, file.getName()));

	        if (success) {
	            System.out.println("File was successfully moved.\n");
	        } else {
	            System.out.println("File was not successfully moved.\n");
	        }


	    }*/

	// http://localhost:8080/maven_Project/rest/database/populateDB
	
	public String populateDB() throws IOException, ParseException {
		long start = System.currentTimeMillis();

		addUserEquipment();
		addOperator();
		addFailure();
		addCause_Event();
		addBase();

		long finish = System.currentTimeMillis();
		System.out.println("Execution time = " + (finish - start)
				+ " milliseconds");
		//doMove();
		return "{Data Successfully Loaded}";
	}
	
	public void addBase() throws IOException, ParseException {
		Collection<Base_Data> baseDataSets = new ArrayList<Base_Data>();
		Collection<Error_Data> errorDataSets = new ArrayList<Error_Data>();

		FileInputStream file = new FileInputStream(new File(path));
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		Row row = sheet.getRow(1);
		int rowcount = 0;
		Iterator<Cell> cellIterator;
		int count;
		Date dateTime;
		int cellId;
		int duration;
		String neVersion;
		String imsi;
		String hier3Id;
		String hier32Id;
		String hier321Id;
		int ueId;
		int failureId;
		int opId;
		int ecId;
		String market;
		String operator;
		Date dayTime;
		String event;
		String failure;
		String ueType;
		String cause_code;
		String cell_Id;
		String durationId;
		String causeCode;
		Cell cell;
		int check;
		String temp;
		SimpleDateFormat sdf;
		Base_Data data;
		Error_Data error;
		boolean isError = false;

		while (rowIterator.hasNext()) {
			row = rowIterator.next();
			cellIterator = row.cellIterator();
			count = 0;
			dateTime = null;
			cellId = 0;
			duration = 0;
			neVersion = null;
			imsi = null;
			hier3Id = null;
			hier32Id = null;
			hier321Id = null;
			ueId = 0;
			failureId = 0;
			opId = 0;
			ecId = 0;
			check = 0;
			market = null;
			operator = null;
			dayTime = null;
			event = null;
			failure = null;
			ueType = null;
			cause_code = null;
			cell_Id = null;
			durationId = null;
			causeCode = null;

			if (rowcount > 0) {
				while (cellIterator.hasNext()) {

					cell = cellIterator.next();

					if (count == 0) {
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						dateTime = cell.getDateCellValue();
						temp = (dateTime.toString());

						sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
								Locale.ENGLISH);
						dayTime = sdf.parse(temp);
					}
					if (count == 1) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						event = cell.getRichStringCellValue().getString();
						
						check = Integer.parseInt(event);
					}
					if (count == 2) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						failure = cell.getRichStringCellValue().getString();
						
						if (failure.contains("(null)")) 
							isError = true;
						else if(em.find(Failure.class, Integer.parseInt(failure))==null)
							isError = true;
						else 
							failureId = Integer.parseInt(failure);
					}
					if (count == 3) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						ueType = cell.getRichStringCellValue().getString();

						if (ueType.contains("(null)")) 
							isError = true;
						else if(em.find(User_Equipment.class, Integer.parseInt(ueType))==null)
							isError=true;
						else {
							ueId = Integer.parseInt(ueType);
						}
					}

					if (count == 4) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						//market = cell.getRichStringCellValue().getString();
						String tempd = cell.getRichStringCellValue().getString();
						
						if (!tempd.contains("(null)"))
							market = tempd;
					}

					if (count == 5) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						//operator = cell.getRichStringCellValue().getString();
						String tempd = cell.getRichStringCellValue().getString();
						
						if (!tempd.contains("(null)"))
							operator = tempd;
					}
					if (count == 6) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell_Id = cell.getRichStringCellValue().getString();
						cellId = Integer.parseInt(cell_Id);
					}

					if (count == 7) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						durationId = cell.getRichStringCellValue().getString();
						duration = Integer.parseInt(durationId);
					}

					if (count == 8) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						causeCode = cell.getRichStringCellValue().getString();

						if (!causeCode.contains("(null)")) {
							cause_code = causeCode;
						}
					}

					if (count == 9) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						neVersion = cell.getRichStringCellValue().getString();
						
					}
					if (count == 10) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						imsi = cell.getRichStringCellValue().getString();

					}
					if (count == 11) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						hier3Id = cell.getRichStringCellValue().getString();
					}
					if (count == 12) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						hier32Id = cell.getRichStringCellValue().getString();
					}
					if (count == 13) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						hier321Id = cell.getRichStringCellValue().getString();
					}

					count++;

				}
			}
			try {
				//if(!market.contains("(null)") && !operator.contains("(null)")){
					temp = market + operator + "";
					if (temp.contains("null"))
						isError = true;
					else if(em.find(Operator.class, Integer.parseInt(temp))==null)
						isError = true;
					else
						opId = Integer.parseInt(temp);
				//}
				
				//if(!event.contains("(null)") && !cause_code.contains("(null)")){
					temp = event + cause_code + "";
					if (temp.contains("null")) 
						isError = true;
					else if(em.find(Event_Cause.class, Integer.parseInt(temp))==null)
						isError = true;
					else
						ecId = Integer.parseInt(temp);
					
				//}
			} catch (NumberFormatException e) {

			}
			
			if (rowcount > 0 && !isError) {
				data = new Base_Data(0, dayTime, cellId, duration, neVersion,
						imsi, hier3Id, hier32Id, hier321Id, em.find(
								Failure.class, failureId), em.find(
								Event_Cause.class, ecId), em.find(
								Operator.class, opId), em.find(
								User_Equipment.class, ueId));

				baseDataSets.add(data);
			} else if (rowcount > 0) {
				error = new Error_Data(0, dateTime.toString(), event, failure,
						ueType, market, operator, cell_Id, durationId,
						causeCode, neVersion, imsi, hier3Id, hier32Id,
						hier321Id);
				errorDataSets.add(error);
			}
			rowcount++;
			isError = false;

		}
		file.close();
		workbook.close();
		service.addBaseData(baseDataSets);
		service.addErrorData(errorDataSets);

	}

	public void addUserEquipment() {

		Collection<User_Equipment> user_equipment = new ArrayList<User_Equipment>();

		try {

			FileInputStream file = new FileInputStream(new File(path));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(3);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row = sheet.getRow(1);
			int rowcount = 0;
			Iterator<Cell> cellIterator;
			int count;
			int userEquipmentId;
			String marketingName;
			String manufacturer;
			String accessCapability;
			String model;
			String vendorName;
			String ueType;
			String os;
			String inputMode;
			Cell cell;
			String temp;
			User_Equipment eq;

			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				cellIterator = row.cellIterator();
				count = 0;
				userEquipmentId = 0;
				marketingName = "null";
				manufacturer = "null";
				accessCapability = "null";
				model = "null";
				vendorName = "null";
				ueType = "null";
				os = "null";
				inputMode = "null";

				if (rowcount > 0) {
					while (cellIterator.hasNext()) {

						cell = cellIterator.next();

						if (count == 0) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							temp = cell.getRichStringCellValue().getString();
							userEquipmentId = Integer.parseInt(temp);
						}

						if (count == 1) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							marketingName = cell.getRichStringCellValue()
									.getString();
						}

						if (count == 2) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							manufacturer = cell.getRichStringCellValue()
									.getString();
						}
						if (count == 3) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							accessCapability = cell.getRichStringCellValue()
									.getString();
						}
						if (count == 4) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							model = cell.getRichStringCellValue().getString();
						}
						if (count == 5) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							vendorName = cell.getRichStringCellValue()
									.getString();
						}
						if (count == 7) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							ueType = cell.getRichStringCellValue().getString();
						}
						if (count == 8) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							os = cell.getRichStringCellValue().getString();
						}
						if (count == 9) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							inputMode = cell.getRichStringCellValue()
									.getString();
						}
						count++;

					}
				}
				eq = new User_Equipment(userEquipmentId, marketingName,
						manufacturer, accessCapability, model, vendorName,
						ueType, os, inputMode);
				if (userEquipmentId != 0) {
					user_equipment.add(eq);
				}
				rowcount++;
			}
			file.close();
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		service.addUser_Equipment(user_equipment);

	}

	public void addOperator() {

		Collection<Operator> operators = new ArrayList<Operator>();

		try {

			FileInputStream file = new FileInputStream(new File(path));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(4);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row = sheet.getRow(1);
			int rowcount = 0;
			Iterator<Cell> cellIterator;
			int count;
			int opId;
			int mcc;
			int mnc;
			String country;
			String operatorName;
			Cell cell;
			String temp;
			String result;
			Operator op;

			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				cellIterator = row.cellIterator();
				count = 0;
				mcc = 0;
				mnc = 0;
				country = null;
				operatorName = null;

				if (rowcount > 0) {
					while (cellIterator.hasNext()) {

						cell = cellIterator.next();

						if (count == 0) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							temp = cell.getRichStringCellValue().getString();
							mcc = Integer.parseInt(temp);
						}

						if (count == 1) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							temp = cell.getRichStringCellValue().getString();
							mnc = Integer.parseInt(temp);
						}
						if (count == 2) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							country = cell.getStringCellValue();
						}
						if (count == 3) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							operatorName = cell.getRichStringCellValue()
									.getString();
						}

						count++;

					}
				}
				result = "" + mcc + mnc;
				opId = Integer.parseInt(result);
				composetkeyOperator = opId;
				op = new Operator(opId, mcc, mnc, country, operatorName);
				if (opId != 0) {
					operators.add(op);
				}
				rowcount++;
			}
			file.close();
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		service.addOperator(operators);

	}

	public void addFailure() {

		Collection<Failure> failures = new ArrayList<Failure>();

		try {

			FileInputStream file = new FileInputStream(new File(path));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(2);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row = sheet.getRow(1);
			int rowcount = 0;
			Iterator<Cell> cellIterator;
			int count;
			int failureId;
			String description;
			Cell cell;
			String temp;
			Failure failure;

			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				cellIterator = row.cellIterator();
				count = 0;
				failureId = 0;
				description = null;

				if (rowcount > 0) {
					while (cellIterator.hasNext()) {

						cell = cellIterator.next();

						if (count == 0) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							temp = cell.getRichStringCellValue().getString();
							failureId = Integer.parseInt(temp);
						}

						if (count == 1) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							description = cell.getRichStringCellValue()
									.getString();
							System.out.println(description);
						}

						count++;
					}
					failure = new Failure(failureId, description);
					failures.add(failure);
					
				}

				rowcount++;
			}
			file.close();
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		service.addFailure(failures);

	}

	public void addCause_Event() {

		Collection<Event_Cause> events = new ArrayList<Event_Cause>();

		try {

			FileInputStream file = new FileInputStream(new File(path));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(1);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row = sheet.getRow(1);
			int rowcount = 0;
			Iterator<Cell> cellIterator;
			int count;
			int ecId;
			int causeCode;
			int eventId;
			String description;
			Cell cell;
			String temp;
			String result;
			Event_Cause event;

			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				cellIterator = row.cellIterator();
				count = 0;

				ecId = 0;
				causeCode = 0;
				eventId = 0;
				description = null;

				if (rowcount > 0) {
					while (cellIterator.hasNext()) {

						cell = cellIterator.next();

						if (count == 0) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							temp = cell.getRichStringCellValue().getString();
							causeCode = Integer.parseInt(temp);
						}

						if (count == 1) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							temp = cell.getRichStringCellValue().getString();
							eventId = Integer.parseInt(temp);
						}

						if (count == 2) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							description = cell.getRichStringCellValue()
									.getString();
						}

						count++;
					}
				}
				result = "" + eventId + causeCode;
				ecId = Integer.parseInt(result);
				composetkeyEventClause = ecId;
				event = new Event_Cause(ecId, causeCode, eventId, description);

				if (ecId != 0) {
					events.add(event);
				}
				rowcount++;
			}
			file.close();
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		service.addEventCause(events);

	}

	/*public void print(String text) throws IOException {

		PrintWriter fileOut;
		String fileName = ("C:\\Users\\D14129133\\Desktop\\debug.txt");

		try {

			fileOut = new PrintWriter(new FileWriter(fileName, true));

			fileOut.println(text);

			fileOut.close();
		} // end try

		catch (FileNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		} // end catch

	} */

}