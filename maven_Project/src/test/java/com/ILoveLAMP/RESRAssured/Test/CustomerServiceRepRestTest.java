package com.ILoveLAMP.RESRAssured.Test;

import static com.jayway.restassured.RestAssured.*;
import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class CustomerServiceRepRestTest {

	@Test
	public void getEventandCausecodeByIMSITest() {

		// String inputParameter = "344930000000011";
		// RestAssured.baseURI =
		// "http://localhost:8080/maven_Project/rest/customer/findEventandCausecodeByIMSI/";
		//
		// Response response = given()
		// .contentType("application/json").
		// body(inputParameter).
		// when().
		// post("");
		//
		// assertEquals(200,response.getStatusCode());
		//
		// response.getBody().asString()
		//
		//
		// String returnedContent = response.getBody().asString();
		// System.out.println(returnedContent);

		// Initializing Rest API's URL
		String apiUrl = "http://localhost:8080/maven_Project/rest/customer/findEventandCausecodeByIMSI/";
		// Initializing payload or API body
		String apiInput = "344930000000011";
		// Building request using requestSpecBuilder
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(apiInput);
		builder.setContentType("application/json; charset= UTF-8");
		RequestSpecification requestSpec = builder.build();
		// Making post request with authentication
		Response response = given().spec(requestSpec).when().post(apiUrl);
		// Fetching the desired value of a parameter
		System.out.println(response.body().asString());
		JSONObject JSONResponseBody = new JSONObject(response.body().asString());
		JSONArray result = JSONResponseBody.getJSONArray("baseDataCollection");
		System.out.println(result);
		System.out.println("The size of returned data is " + result.length()
				+ " !!!");
		System.out.println(result.get(0));
		int eventId = result.getJSONObject(0).getInt("eventId");
		int causeCode = result.getJSONObject(0).getInt("causeCode");
		System.out.println(eventId);
		System.out.println(causeCode);

		// Automatically check the resulte with assert methods
		assertEquals(200, response.getStatusCode());
		assertEquals(13, result.length());
		assertEquals(4098, eventId);
		assertEquals(0, causeCode);
	}

	@Test
	public void getFailurebyTimeandIMSITest() {

		// Initializing Rest API's URL
		String apiUrl = "http://localhost:8080/maven_Project/rest/customer/findFailurebyTimeandIMSI/";
		// Initializing pay load or API body
		String time1 = "01/11/2013 00:00";
		String time2 = "30/11/2013 00:00";
		String imsi = "344930000000011";
		String[] apiInput = { time1, time2, imsi };

		// Building request using requestSpecBuilder
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(apiInput);
		builder.setContentType("application/json; charset= UTF-8");
		RequestSpecification requestSpec = builder.build();
		// Making post request with authentication
		Response response = given().spec(requestSpec).when().post(apiUrl);
		// Fetching the desired value of a parameter
		Long returnedNoOfFailures = Long.parseLong(response.body().asString());
		// Automatically check the result with assert methods

		Long expectedNoOfFailures = (long) 256;
		assertEquals(200, response.getStatusCode());
		assertEquals(expectedNoOfFailures, returnedNoOfFailures);
	}

	@Test
	public void getUniqueCauseCodesForImsiTest() {

		// Initializing Rest API's URL
		String apiUrl = "http://localhost:8080/maven_Project/rest/customer/findUniqueCauseCodebyImsi/";
		// Initializing pay load or API body
		String apiInput = "344930000000011";
		// Building request using requestSpecBuilder
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(apiInput);
		builder.setContentType("application/json; charset= UTF-8");
		RequestSpecification requestSpec = builder.build();
		// Making post request with authentication
		Response response = given().spec(requestSpec).when().post(apiUrl);
		// Fetching the desired value of a parameter
		String expectedResult = "[[1,0],[1,13],[1,11],[0,23],[1,12],[1,9],[1,10],[0,24],[1,1],[0,25]]";
		String returnedResult = response.body().asString();
		// Automatically check the resulte with assert methods
		assertEquals(200, response.getStatusCode());
		assertEquals(expectedResult, returnedResult);
	}

}
