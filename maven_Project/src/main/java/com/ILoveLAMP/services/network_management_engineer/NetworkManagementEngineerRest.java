package com.ILoveLAMP.services.network_management_engineer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

@Path("/NetworkManagementEngineer")
@Stateless
public class NetworkManagementEngineerRest {
	
	
	@Inject
	private Base_DataService service;

	@PersistenceContext
	EntityManager em;
	
	
	//http://localhost:8080/maven_Project/rest/NetworkManagementEngineer/findTop10IMSI/2013-01-11%2017:15:00/2013-01-11%2017:19:00
	@POST
	@Path("/findTop10IMSI/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Object[]> getTop10ImsisByDate(String []data) {
		String startTime = data[0];
		String endTime = data[1];
		startTime = startTime.replace("\"", "");
		endTime = endTime.replace("\"", "");
		
		//String og = "01-11-2013 17:00";
		String day=startTime.substring(0,2);
		String month=startTime.substring(3,5);
		String year=startTime.substring(6,10);
		String time=startTime.substring(11,16)+":00";
		startTime=year+"-"+day+"-"+month+" "+time;
		//System.out.println(time1);
		
		String day1=endTime.substring(0,2);
		String month1=endTime.substring(3,5);
		String year1=endTime.substring(6,10);
		String timea=endTime.substring(11,16)+":00";
		endTime=year1+"-"+day1+"-"+month1+" "+timea;
		//System.out.println(time2);
		Date startDate = null;
		Date endDate = null;

		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(startTime);
			endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(endTime);
		} catch (ParseException e) {
		}
		return service.getTop10ImsisByDate(startDate, endDate);
	}
	
	
//	// http://localhost:8080/maven_Project/rest/NetworkManagementEngineer/getAllTotalDurationByPeriodAndImsi/2013-01-11 17:15:00/2013-01-11 17:18:00
//		@POST
//		@Path("/getAllTotalDurationByPeriodAndImsi")
//		@Produces(MediaType.APPLICATION_JSON)
//		@Consumes(MediaType.APPLICATION_JSON)
//		public Map<String, Integer> getAllTotalDurationByPeriodAndImsi(String startTime, String endTime) {
//			
//			Date parsedStartTime = null;
//			Date parsedEndTime = null;
//			try {
//				parsedStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//						.parse(startTime);
//				parsedEndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//				.parse(endTime);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//			return service.getAllTotalDurationByPeriodAndImsi(parsedStartTime, parsedEndTime);
//		}
	
	// http://localhost:8080/maven_Project/rest/NetworkManagementEngineer/getNoOfFailuresTotalDurationsForEachImsiByPeriod/2013-01-11 17:15:00/2013-01-11 17:18:00
	@POST
	@Path("/getNoOfFailuresTotalDurationsForEachImsiByPeriod/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Object[]> getNoOfFailuresTotalDurationsForEachImsiByPeriod(String []data){
		
		String startTime = data[0];
		String endTime = data[1];
		startTime = startTime.replace("\"", "");
		endTime = endTime.replace("\"", "");
		
		//String og = "01-11-2013 17:00";
		String day=startTime.substring(0,2);
		String month=startTime.substring(3,5);
		String year=startTime.substring(6,10);
		String time=startTime.substring(11,16)+":00";
		startTime=year+"-"+day+"-"+month+" "+time;
		//System.out.println(time1);
		
		String day1=endTime.substring(0,2);
		String month1=endTime.substring(3,5);
		String year1=endTime.substring(6,10);
		String timea=endTime.substring(11,16)+":00";
		endTime=year1+"-"+day1+"-"+month1+" "+timea;
		//System.out.println(time2);
		Date startDate = null;
		Date endDate = null;

		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(startTime);
			endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(endTime);
		} catch (ParseException e) {
		}
		return service.getNoOfFailuresTotalDurationsForEachImsiByPeriod(startDate, endDate);
		
	}
	
	@POST
	@Path("/findFailureCauseCodeAndOccurrences/")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Object[]> getFailureCauseCodeAndOcurrences(String model) {	
		model = model.replace("\"", "");
		Base_DataList datas = new Base_DataList();
		//datas.setBaseDataCollection(service.getFailureCauseCodeAndOccurrences(model));
		//return datas;
		return service.getFailureCauseCodeAndOccurrences(model);
	}
	
	

	// http://localhost:8080/maven_Project/rest/basedata/findTop10MarketOperatorCellCombo/2013-01-11 17:11:00/2013-01-11 17:39:00
	@POST
	@Path("/findTop10MarketOperatorCellCombo/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Object[]> gettop10MarketOperatorCellCombo(String [] data) {
		String startTime = data[0];
		String endTime = data[1];
		startTime = startTime.replace("\"", "");
		endTime = endTime.replace("\"", "");
		
		//String og = "01-11-2013 17:00";
		String day=startTime.substring(0,2);
		String month=startTime.substring(3,5);
		String year=startTime.substring(6,10);
		String time=startTime.substring(11,16)+":00";
		startTime=year+"-"+day+"-"+month+" "+time;
		//System.out.println(time1);
		
		String day1=endTime.substring(0,2);
		String month1=endTime.substring(3,5);
		String year1=endTime.substring(6,10);
		String timea=endTime.substring(11,16)+":00";
		endTime=year1+"-"+day1+"-"+month1+" "+timea;
		Date startDate = null;
		Date endDate = null;

		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(startTime);
			endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(endTime);
		} catch (ParseException e) {
		}
		return service.top10MarketOperatorCellIDCombo(startDate, endDate);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}