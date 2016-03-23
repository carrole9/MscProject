package com.ILoveLAMP.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

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

import com.ILoveLAMP.entities.Base_Data;
import com.ILoveLAMP.entities.Base_DataList;

@Path("/basedata")
@Stateless
public class Rest {

	@Inject
	private Base_DataService service;

	@PersistenceContext
	EntityManager em;

	// private String path = "/home/user1/Desktop/dataset.xls";
	int composetkeyEventClause;
	int composetkeyOperator;

	public Rest() {}

	// http://localhost:8080/maven_Project/rest/basedata/getAllBaseData
	@GET
	@Path("/getAllBaseData")
	@Produces(MediaType.APPLICATION_JSON)
	public Base_DataList getBase_Datas() {
		Base_DataList datas = new Base_DataList();
		datas.setBaseDataCollection(service.getAllBaseDatas());
		return datas;
	}
	
	@GET
	@Path("/getAllIMSI")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Base_Data> getIMSI() {
		return service.getAllIMSI();
		
	}
	
	@GET
	@Path("/getAllFailureID")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Base_Data> getAllFailureID() {
		return service.getAllFailureID();
		
	}
	
	@GET
	@Path("/getAllManufacturers")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Base_Data> getAllManufacturers() {
		return service.getAllManufacturers();
		
	}
	@POST
	@Path("/getAllModels/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<Base_Data> getAllModels(String model) {
		model = model.replace("\"", "");
		return service.getAllModels(model);
		
	}
	

	// http://localhost:8080/maven_Project/rest/basedata/findbyID/1
	@GET
	@Path("/findbyID/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Base_Data getDatabyID(@PathParam("id") int id) {
		return service.getDatabyID(id);
	}

	// http://localhost:8080/maven_Project/rest/basedata/findbyTime/2013-01-11
	// 17:16:00
	@GET
	@Path("/findbyTime/{time}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Base_Data> getDatabyTime(@PathParam("time") String time) {
		Date parsedDate = null;
		try {
			parsedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return service.getDatabyTime(parsedDate);
	}

	/*
	 * //
	 * http://localhost:8080/maven_Project/rest/basedata/findIMSIandFailurebyTime
	 * /2013-01-11 17:15:00/2013-01-11 17:34:00
	 * 
	 * @GET
	 * 
	 * @Path("/findIMSIandFailurebyTime/{time1}/{time2}")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Base_DataList
	 * getIMSIandFailurebyTime(@PathParam("time1") String time1,
	 * @PathParam("time2") String time2) throws IOException {
	 * 
	 * Date parsedDate1 = null; Date parsedDate2 = null; try { parsedDate1 = new
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss") .parse(time1); parsedDate2 = new
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss") .parse(time2); } catch
	 * (ParseException e) { e.printStackTrace(); } Base_DataList datas = new
	 * Base_DataList();
	 * datas.setBaseDataCollection(service.getIMSIandCallFailures(parsedDate1,
	 * parsedDate2)); return datas; }
	 * 
	 * @GET
	 * 
	 * @Path("/findEventandCausecodeByIMSI/{imsi}")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Base_DataList
	 * getEventandCausecodeByIMSI(@PathParam("imsi") String imsi) {
	 * Base_DataList datas = new Base_DataList();
	 * datas.setBaseDataCollection(service.getEventandCausecodeByIMSI(imsi));
	 * return datas; }
	 */

}
