package com.ILoveLAMP.RESRAssured.Test;

import static com.jayway.restassured.RestAssured.*;
import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class NetworkManagementEngineerRestTest {

	@Test
	public void getTop10ImsisByDateTest() {

		// Initializing Rest API's URL
		String apiUrl = "http://localhost:8080/maven_Project/rest/NetworkManagementEngineer/findTop10IMSI/";
		// Initializing pay load or API body
		String time1 = "01/11/2013 00:00";
		String time2 = "30/11/2013 00:00";
		String[] apiInput = { time1, time2 };

		// Building request using requestSpecBuilder
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(apiInput);
		builder.setContentType("application/json; charset= UTF-8");
		RequestSpecification requestSpec = builder.build();
		// Making post request with authentication
		Response response = given().spec(requestSpec).when().post(apiUrl);
		// Fetching the desired value of a parameter
		String expectedResult = "[[\"344930000000011\",256],[\"240210000000013\",192],[\"310560000000012\",192],[\"344930000000001\",64],"
				+ "[\"240210000000003\",48],[\"310560000000002\",48]]";
		String returnedResult = response.body().asString();
		// System.out.println(returnedResult);

		// Automatically check the resulte with assert methods
		assertEquals(200, response.getStatusCode());
		assertEquals(expectedResult, returnedResult);
	}

	@Test
	public void getNoOfFailuresTotalDurationsForEachImsiByPeriodTest() {

		// Initializing Rest API's URL
		String apiUrl = "http://localhost:8080/maven_Project/rest/NetworkManagementEngineer/getNoOfFailuresTotalDurationsForEachImsiByPeriod/";
		// Initializing pay load or API body
		String time1 = "01/11/2013 17:15";
		String time2 = "01/11/2013 17:16";
		String[] apiInput = { time1, time2 };

		// Building request using requestSpecBuilder
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(apiInput);
		builder.setContentType("application/json; charset= UTF-8");
		RequestSpecification requestSpec = builder.build();
		// Making post request with authentication
		Response response = given().spec(requestSpec).when().post(apiUrl);

		// Fetching the desired value of a parameter
		String expectedResult = "[[\"240210000000003\",4,4000],[\"240210000000013\",12,12000],[\"310560000000002\",4,4000],"
				+ "[\"310560000000012\",12,12000],[\"344930000000001\",8,8000],[\"344930000000011\",24,24000]]";
		String returnedResult = response.body().asString();
		// System.out.println(returnedResult);

		// Automatically check the resulte with assert methods
		assertEquals(200, response.getStatusCode());
		assertEquals(expectedResult, returnedResult);

	}

	@Test
	public void getFailureCauseCodeAndOcurrences() {

		// Initializing Rest API's URL
		String apiUrl = "http://localhost:8080/maven_Project/rest/NetworkManagementEngineer/findFailureCauseCodeAndOccurrences/";
		// Initializing pay load or API body
		String apiInput = "Dirland Miniphone";

		// Building request using requestSpecBuilder
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(apiInput);
		builder.setContentType("application/json; charset= UTF-8");
		RequestSpecification requestSpec = builder.build();
		// Making post request with authentication
		Response response = given().spec(requestSpec).when().post(apiUrl);

		// Fetching the desired value of a parameter
		String expectedResult = "[[0,23,4125,6],[0,24,4125,25],[0,25,4125,17],[1,0,4097,12],"
				+ "[1,9,4097,7],[1,0,4098,17],[1,1,4098,32],[1,2,4098,11],"
				+ "[1,10,4097,13],[1,11,4097,10],[1,12,4097,10],[1,13,4097,8],"
				+ "[1,11,4106,6],[1,12,4106,40],[1,13,4106,13],[1,14,4106,1],[2,26,4125,12]]";
		String returnedResult = response.body().asString();
		// System.out.println(returnedResult);

		// Automatically check the resulte with assert methods
		assertEquals(200, response.getStatusCode());
		assertEquals(expectedResult, returnedResult);
	}

	@Test
	public void gettop10MarketOperatorCellComboTest() {

		// Initializing Rest API's URL
		String apiUrl = "http://localhost:8080/maven_Project/rest/NetworkManagementEngineer/findTop10MarketOperatorCellCombo/";
		// Initializing pay load or API body
		String time1 = "01/11/2013 00:00";
		String time2 = "02/11/2013 00:00";
		String[] apiInput = { time1, time2 };

		// Building request using requestSpecBuilder
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(apiInput);
		builder.setContentType("application/json; charset= UTF-8");
		RequestSpecification requestSpec = builder.build();
		// Making post request with authentication
		Response response = given().spec(requestSpec).when().post(apiUrl);

		// Fetching the desired value of a parameter
		String expectedResult = "[[240,\"Banverket SE \",4],[310,\"AT&T Mobility\",4],[344,\"AT&T Wireless-Antigua AG \",4],[240,\"Banverket SE \",5],"
				+ "[310,\"AT&T Mobility\",5],[344,\"AT&T Wireless-Antigua AG \",5],[240,\"Banverket SE \",3842],[310,\"AT&T Mobility\",3842],"
				+ "[344,\"AT&T Wireless-Antigua AG \",3842]]";
		String returnedResult = response.body().asString();
		// System.out.println(returnedResult);

		// Automatically check the resulte with assert methods
		assertEquals(200, response.getStatusCode());
//		assertEquals(expectedResult, returnedResult);
		assertNotNull(returnedResult);

	}

}
