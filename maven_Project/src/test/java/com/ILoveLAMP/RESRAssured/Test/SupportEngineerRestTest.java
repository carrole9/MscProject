package com.ILoveLAMP.RESRAssured.Test;

import static com.jayway.restassured.RestAssured.*;
import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class SupportEngineerRestTest {

	@Test
	public void getIMSIandFailurebyTimeTest() {

		// Initializing Rest API's URL
		String apiUrl = "http://localhost:8080/maven_Project/rest/supportEngineer/findIMSIandFailurebyTime/";
		// Initializing pay load or API body
		String time1 = "2006-01-11 17:15:00";
		String time2 = "2016-01-11 17:34:00";
		String[] apiInput = { time1, time2 };

		// Building request using requestSpecBuilder
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(apiInput);
		builder.setContentType("application/json; charset= UTF-8");
		RequestSpecification requestSpec = builder.build();
		// Making post request with authentication
		Response response = given().spec(requestSpec).when().post(apiUrl);

		String returnedResult = response.body().asString();
		System.out.println(returnedResult);

		JSONObject JSONResponseBody = new JSONObject(response.body().asString());
		JSONArray result = JSONResponseBody.getJSONArray("baseDataCollection");

		System.out.println(result);
		System.out.println("The size of returned data is " + result.length()
				+ " !!!");

//		System.out.println(result.get(0));
//		String expectedFirstImsi = "344930000000011";
//		String returnedFirstImsi = result.get(0).toString();
		
//		System.out.println(returnedFirstImsi);
		// Automatically check the resulte with assert methods
		assertEquals(200, response.getStatusCode());
//		assertEquals(640, result.length());
//		assertEquals(expectedFirstImsi, returnedFirstImsi);
	}

	@Test
	public void getNoOfFailuresByPeriodAndModelTest() {

		// Initializing Rest API's URL
		String apiUrl = "http://localhost:8080/maven_Project/rest/supportEngineer/findNoOfFailuresByPeriodAndModel/";
		// Initializing pay load or API body
		String time1 = "2006-01-11 17:15:00";
		String time2 = "2016-01-11 17:34:00";
		String model = "Dirland Miniphone";
		String[] apiInput = { time1, time2, model };

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
		System.out.println(returnedNoOfFailures);

//		Long expectedNoOfFailures = (long) 192;
		assertEquals(200, response.getStatusCode());
//		assertEquals(expectedNoOfFailures, returnedNoOfFailures);
	}

	@Test
	public void getIMSIbyFailId() {
		// Initializing Rest API's URL
		String apiUrl = "http://localhost:8080/maven_Project/rest/supportEngineer/findIMSIbyFailureId/";
		// Initializing pay load or API body
		String apiInput = "1";

		// Building request using requestSpecBuilder
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(apiInput);
		builder.setContentType("application/json; charset= UTF-8");
		RequestSpecification requestSpec = builder.build();
		// Making post request with authentication
		Response response = given().spec(requestSpec).when().post(apiUrl);

//		String expectedResult = "[\"240210000000003\",\"240210000000013\",\"310560000000002\","
//				+ "\"310560000000012\",\"344930000000001\",\"344930000000011\"]";
		String returnedResult = response.body().asString();
		 System.out.println(returnedResult);

		// Automatically check the resulte with assert methods
		assertEquals(200, response.getStatusCode());
//		assertEquals(expectedResult, returnedResult);
	}
}
