package com.ILoveLAMP.services.support_engineer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
	public Base_DataList getIMSIandFailurebyTime(String[] time) throws IOException {	
		Date parsedDate1 = null;
		Date parsedDate2 = null;
		
		String time1 = time[0];
		String time2 = time[1];
		time1 = time1.replace("\"", "");
		time2 = time2.replace("\"", "");
		
		try {
			parsedDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(time1);
			parsedDate2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(time2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Base_DataList datas = new Base_DataList();
		datas.setBaseDataCollection(service.getIMSIandCallFailures(parsedDate1, parsedDate2));
	
		
		return datas;
	}
	
	// http://localhost:8080/maven_Project/rest/supportEngineer/findNoOfFailuresByPeriodAndModel/2013-01-11%2017:15:00/2013-01-11%2017:34:00/LMU
	@POST
	@Path("/findNoOfFailuresByPeriodAndModel/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Integer getNoOfFailuresByPeriodAndModel(String[] data) {
		String startTime = data[0];
		String endTime = data[1];
		String model = data[2];
		startTime = startTime.replace("\"", "");
		endTime = endTime.replace("\"", "");
		model = model.replace("\"", "");
		
		Date parsedStartTime = null;
		Date parsedEndTime = null;
		try {
			parsedStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(startTime);
			parsedEndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.parse(endTime);
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
		Integer id = Integer.parseInt(data);
		return service.getIMSIbyFailureId(id);
	}


}
