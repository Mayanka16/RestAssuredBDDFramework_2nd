package com.qa.rest.tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.qa.rest.utils.FileNameConstants;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;

public class PutAPIRequest {
	
@Test

public void postAPIRequest() {
		
		
		try {
			 String postAPIRequestBody = FileUtils.readFileToString(new File(FileNameConstants.POST_API_REQUEST_BODY),  StandardCharsets.UTF_8);
			 			System.out.println("Post API Request Body - "+ postAPIRequestBody);
			 String tokenAPIRequestBody = FileUtils.readFileToString(new File(FileNameConstants.TOKEN_REQUEST_BODY),  StandardCharsets.UTF_8);
			 String putAPIRequestBody = FileUtils.readFileToString(new File(FileNameConstants.PUT_REQUEST_BODY),  StandardCharsets.UTF_8);
				

				/*
				 * Author - Mayanka Sao
				 * Date - 02/Sep/2024
				 * Method - postAPIRequest() 
				 * Read txt file from FileNameConstants class and create POST call
				 */
					
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
				
		
		// fetching bookingId and can be directly stored in int because the value is not stored in an array and directly can access
		int bookingId = JsonPath.read(response.body().asString(), "$.bookingid");
		
		
		/*
		 * Author - Mayanka Sao
		 * Date - 02/Sep/2024
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
		
		/*
		 * Author - Mayanka Sao
		 * Date - 02/Sep/2024
		 * Method - postAPIRequest() 
		 * token Generation POST call
		 */
		
		Response tokenAPIResponse = 
		RestAssured
			.given()
				.contentType(ContentType.JSON)
				.body(tokenAPIRequestBody)
				.baseUri("https://restful-booker.herokuapp.com/auth")
			.when()
				.post()
			.then()
				.assertThat()
				.statusCode(200)
				.extract()
				.response();
		
		
		String token  = JsonPath.read(tokenAPIResponse.body().asString(), "$.token");
		
		
		RestAssured 
			.given()
				.contentType(ContentType.JSON)
				.body(putAPIRequestBody)
				.headers("Cookie","token="+ token )
				.baseUri("https://restful-booker.herokuapp.com/booking")
			.when()
				.put("/{bookingID}", bookingId)
			.then()
				.assertThat()
				.statusCode(200)		
				.body("firstname", Matchers.equalTo("Specflow"))
				.body("lastname", Matchers.equalTo("Selenium C#"));
				
				
				
				
				
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}

}