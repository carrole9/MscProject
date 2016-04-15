package com.ILoveLAMP.services.customer_service_rep;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ILoveLAMP.entities.Base_Data;
import com.ILoveLAMP.entities.Base_DataList;
import com.ILoveLAMP.services.Base_DataService;

@Path("/customer")
@Stateless
public class CustomerServiceRepRest {

	@Inject
	private Base_DataService service;

	//@PersistenceContext
	//EntityManager em;

	//private String path = "/home/user1/Desktop/dataset.xls";
	int composetkeyEventClause;
	int composetkeyOperator;

	public CustomerServiceRepRest() {
	}
	//http://localhost:8080/maven_Project/rest/customer/findEventandCausecodeByIMSI/344930000000011
	@POST
	@Path("/findEventandCausecodeByIMSI/")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Base_DataList getEventandCausecodeByIMSI(String imsi) {
		imsi = imsi.replace("\"", "");
		Base_DataList datas = new Base_DataList();
		datas.setBaseDataCollection(service.getEventandCausecodeByIMSI(imsi));
//		System.out.println(datas.getBaseDataCollection().size());
		return datas;
	}
	
	//http://localhost:8080/maven_Project/rest/customer/findFailurebyTimeandIMSI/2013-01-11%2017:15:00/2013-01-11%2017:15:00/344930000000011
	@POST
	@Path("/findFailurebyTimeandIMSI/")
	//@Path("/findIMSIandFailurebyTime")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public Base_DataList getIMSIandFailurebyTime(String time1,String time2) throws IOException {
	public Long getFailurebyTimeandIMSI(String [] data) throws IOException {		
		
		String time1 = data[0];
		String time2 = data[1];
		String imsi = data[2];
		imsi = imsi.replace("\"", "");
		time1 = time1.replace("\"", "");
		time2 = time2.replace("\"", "");
		
		//String og = "01-11-2013 17:00";
		String day=time1.substring(0,2);
		String month=time1.substring(3,5);
		String year=time1.substring(6,10);
		String time=time1.substring(11,16)+":00";
		time1=year+"-"+month+"-"+day+" "+time;
		//System.out.println(time1);
		
		String day1=time2.substring(0,2);
		String month1=time2.substring(3,5);
		String year1=time2.substring(6,10);
		String timea=time2.substring(11,16)+":00";
		time2=year1+"-"+month1+"-"+day1+" "+timea;
		//System.out.println(time2);
		
		//print(time1);
		//print(time2);
		//print(imsi);
		//imsi = imsi.replace("\"", "");
		//time1 = time1.replace("\"", "");
		//time2 = time2.replace("\"", "");
		Date parsedDate1 = null;
		Date parsedDate2 = null;
		try {
			parsedDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(time1);
			parsedDate2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(time2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long temp =service.getFailurebyTimeandIMSI(parsedDate1, parsedDate2, imsi);
		//print(parsedDate1.toString());
		//print(parsedDate2.toString());
		//print(temp.toString());
		
		return temp;
	}
	//localhost:8080/maven_Project/rest/customer/findUniqueCauseCodebyImsi/344930000000011
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
     @Path("/findUniqueCauseCodebyImsi/")
     @Produces(MediaType.APPLICATION_JSON)
     public List<Object[]> getUniqueCauseCodesForImsi(String imsi) {

		    imsi = imsi.replace("\"", "");
			
			return service.getUniqueCauseCodesForIMSI(imsi);
			
		 
     }
	
	
}	
	