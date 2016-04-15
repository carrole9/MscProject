package com.ILoveLAMP.services.support_engineer;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ILoveLAMP.entities.Base_DataList;
import com.ILoveLAMP.services.Base_DataService;


@Path("/supportEngineer")
@Stateless
public class SupportEngineerRest {
	
	@Inject
	private Base_DataService service;

	@PersistenceContext
	EntityManager em;
	
	
	//http://localhost:8080/maven_Project/rest/supportEngineer/findIMSIandFailurebyTime/2013-01-11%2017:15:00/2013-01-11%2017:15:00
	@POST
	@Path("/findIMSIandFailurebyTime/")
	//@Path("/findIMSIandFailurebyTime")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	//public Base_DataList getIMSIandFailurebyTime(String time1,String time2) throws IOException {
	public Base_DataList getIMSIandFailurebyTime(String[] data) throws IOException {	
		
		String time1 = data[0];
		String time2 = data[1];
		time1 = time1.replace("\"", "");
		time2 = time2.replace("\"", "");
		
		
		
		//String og = "01-11-2013 17:00";
		String day=time1.substring(0,2);
		String month=time1.substring(3,5);
		String year=time1.substring(6,10);
		String time=time1.substring(11,16)+":00";
		time1=year+"-"+month+"-"+day+" "+time;
		
		String day1=time2.substring(0,2);
		String month1=time2.substring(3,5);
		String year1=time2.substring(6,10);
		String timea=time2.substring(11,16)+":00";
		time2=year1+"-"+month1+"-"+day1+" "+timea;
		
		
		Date parsedStartTime = null;
		Date parsedEndTime = null;
		try {
			
			parsedStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time1);
			parsedEndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time2);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		Base_DataList datas = new Base_DataList();
		datas.setBaseDataCollection(service.getIMSIandCallFailures(parsedStartTime, parsedEndTime));
	
		
		return datas;
	}
	
	// http://localhost:8080/maven_Project/rest/supportEngineer/findNoOfFailuresByPeriodAndModel/2013-01-11%2017:15:00/2013-01-11%2017:34:00/LMU
	@POST
	@Path("/findNoOfFailuresByPeriodAndModel/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Integer getNoOfFailuresByPeriodAndModel(String[] data) throws IOException {
		String time1 = data[0];
		String time2 = data[1];
		String model = data[2];
		time1 = time1.replace("\"", "");
		time2 = time2.replace("\"", "");
		model = model.replace("\"", "");
		
		
		
		//String og = "01-11-2013 17:00";
		String day=time1.substring(0,2);
		String month=time1.substring(3,5);
		String year=time1.substring(6,10);
		String time=time1.substring(11,16)+":00";
		time1=year+"-"+month+"-"+day+" "+time;
		
		String day1=time2.substring(0,2);
		String month1=time2.substring(3,5);
		String year1=time2.substring(6,10);
		String timea=time2.substring(11,16)+":00";
		time2=year1+"-"+month1+"-"+day1+" "+timea;
		
		
		Date parsedStartTime = null;
		Date parsedEndTime = null;
		try {
			System.out.println("noofFailureBefore" + time1 + time2);
			parsedStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(time1);
			parsedEndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.parse(time2);
			System.out.println("noofFailureAfter" + parsedStartTime + parsedEndTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return service.getNoOfFailuresByPeriodAndModel(parsedStartTime, parsedEndTime, model);
	}
	
	// http://localhost:8080/maven_Project/rest/supportEngineer/findIMSIbyFailureId/2
	@POST
	@Path("/findIMSIbyFailureId/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<String> getIMSIbyFailId(String data) {
		return service.getIMSIbyFailureId(data);
	}
	
	


}
