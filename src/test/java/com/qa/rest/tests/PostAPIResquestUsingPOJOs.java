package com.qa.rest.tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.rest.pojo.Booking;
import com.qa.rest.pojo.BookingDates;
import com.qa.rest.utils.FileNameConstants;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class PostAPIResquestUsingPOJOs {
	
	@Test

	public void postAPIRequest() {
		try {

			String jsonSchema = FileUtils.readFileToString(new File(FileNameConstants.JSON_SCHEMA),
					StandardCharsets.UTF_8);

			// Serialization
			BookingDates bookingDates = new BookingDates("2024-10-10", "2024-11-11");
			try {
				Booking booking = new Booking("api testing", "tutorial", "breakfast", 1000, true, bookingDates);

				ObjectMapper objectMapper = new ObjectMapper();

				String requestBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);

				System.out.println("Request Body is : " + requestBody);
		
		//De-Serialization
			Booking bookingDetails = objectMapper.readValue(requestBody, Booking.class);
			System.out.println("First name is : "+ bookingDetails.getFirstname());
			System.out.println("Last name is : "+bookingDetails.getLastname());
			
			
			System.out.println("Checkin Date is : "+ bookingDetails.getBookingdates().getCheckin());
			System.out.println("CheckOut Date is : "+ bookingDetails.getBookingdates().getCheckout());
			
			
		//POST API request using POJO (bookingDetails)
			
			Response response = 
			RestAssured
				.given()	
					.contentType(ContentType.JSON)
					.body(bookingDetails) //POJO
					.baseUri("https://restful-booker.herokuapp.com/booking")
				.when()
					.post()
				.then()
					.assertThat()
					.statusCode(200)
					.extract()
					.response();
				
			
			//System.out.println("JSON schema : "+ jsonSchema);
			
			//fetching bookingId from response
			int bookingID = response.path("bookingid");
			
			//GET call using bookingID
			RestAssured
				.given()
					.contentType(ContentType.JSON)
					.baseUri("https://restful-booker.herokuapp.com/booking/")
				.when()
					.get("{bookingID}", bookingID)
				.then()
					.assertThat()
					.statusCode(200)
					// validate the actual schema and expected schema data types					
					.body(JsonSchemaValidator.matchesJsonSchema(jsonSchema)); 
			
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

	} catch (IOException e) {

		e.printStackTrace();
	}
		
		
		System.out.println("*************************************************************************");
		
		
		
		
		
	}

}
