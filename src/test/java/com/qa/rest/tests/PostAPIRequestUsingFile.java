package com.qa.rest.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.qa.rest.utils.BaseTest;
import com.qa.rest.utils.FileNameConstants;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;

public class PostAPIRequestUsingFile extends BaseTest{
	@Test
	public void postAPIRequest() {
		
		
		/*
		 * Author - Mayanka Sao
		 * Date - 01/Sep/2024
		 * Method - postAPIRequest() 
		 * Read txt file from FileNameConstants class and create POST call
		 */
		
		try {
			String postAPIRequestBody = FileUtils.readFileToString(new File(FileNameConstants.POST_API_REQUEST_BODY), "UTF-8");
			System.out.println("Post API Request Body - "+ postAPIRequestBody);
			
			
		Response response = 	
			RestAssured
			.given()
				.contentType(ContentType.JSON)
				.body(postAPIRequestBody)
				.baseUri("https://restful-booker.herokuapp.com/booking")
			.when()
				.post()
			.then()
				.assertThat()
				.statusCode(200)
				.log().all()
				.extract()
				.response();
		
		
		/*
		 * Author - Mayanka Sao
		 * Date - 01/Sep/2024
		 * Method - postAPIRequest() 
		 * Using JSONPath Expression fetch the value using JSONArray
		 */
		
		JSONArray firstName = JsonPath.read(response.body().asString() , "$.booking..firstname");
		String firstNameValue = (String) firstName.get(0);
		Assert.assertEquals(firstNameValue, "API Testing");
				
		
		JSONArray lastName = JsonPath.read(response.body().asString() , "$.booking..lastname");
		String lastNameValue = (String) lastName.get(0);
		Assert.assertEquals(lastNameValue, "Tutorial");
		
		JSONArray checkinDate = JsonPath.read(response.body().asString(), "$.booking.bookingdates..checkin");
		String checkinDateValue = (String) checkinDate.get(0);
		Assert.assertEquals(checkinDateValue, "2018-01-01");
		
		// fetching bookingId and can be directly stored in int because the value is not stored in an array and directly can access
		int bookingId = JsonPath.read(response.body().asString(), "$.bookingid");
		
		
		/*
		 * Author - Mayanka Sao
		 * Date - 01/Sep/2024
		 * Method - postAPIRequest() 
		 * Using Get method to validate using bookingId received from POSt response
		 */
		
		
		RestAssured
			.given()
				.contentType(ContentType.JSON)
				.baseUri("https://restful-booker.herokuapp.com/booking/")
			.when()
				.get("/{bookingId}", bookingId)
			.then()
				.assertThat()
				.statusCode(200);
				
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
