package com.qa.rest.tests;

import org.hamcrest.Matchers;
import org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.qa.rest.utils.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import static io.restassured.RestAssured.*;
//import org.json.JSONObject;

public class PostAPIRequest extends BaseTest{
	
	@Test	
	public void createBooking() {		
		//prepare request body
		JSONObject booking = new JSONObject();
		JSONObject bookingDates = new JSONObject();
		
		booking.put("firstname", "api testing");
		booking.put("lastname", "tutorial");
		booking.put("totalprice", 2000);
		booking.put("depositpaid", true);
		bookingDates.put("checkin", "2024-09-27");
		bookingDates.put("checkout", "2025-08-27");
		booking.put("additionalneeds", "breakfast");
		booking.put("bookingdates", bookingDates);		
		
		/*
		 * Author - Mayanka Sao
		 * Date - 31/Aug/2024
		 * Method - createBooking()
		 * First Method 
		 */
	 	
		
	 Response response = 
				RestAssured
				.given()
				//.log().body()
				.contentType(ContentType.JSON)
				.body(booking.toString())
				.baseUri("https://restful-booker.herokuapp.com/booking") 
				.when()
				.post()
				.then()
				.assertThat()
				//.log().all()
				.statusCode(200)
				.body("booking.firstname", Matchers.equalTo("api testing"))		
				.extract()			
				.response();

	 int bookingId = response.path("bookingid");
	 
	 

	 	/*
		 * Author - Mayanka Sao
		 * Date - 31/Aug/2024
		 * Method - createBooking() using String JsonPath
		 * Second Method 
		 * commented.log().all() as inherit BaseTest RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		 */
	 
		
 String request = 
	RestAssured
		.given()
			//.log().body()
			.contentType(ContentType.JSON)
			.body(booking.toString())
			.baseUri("https://restful-booker.herokuapp.com/booking") 
		.when()
			.post()
		.then()
			.assertThat()
			//.log().all()
			.statusCode(200)
			.body("booking.firstname", Matchers.equalTo("api testing"))		
			.extract()			
			.response()	
			.asString();
			
			JsonPath js= new JsonPath(request);
			Integer booking_Id = js.get("bookingid");
			System.out.println("BookingID is : "+ booking_Id);
			
	 
	 // Get Method 
 RestAssured
	 .given()
	 	.contentType(ContentType.JSON)
	 	.pathParam("Booking ID", bookingId)
	 	.baseUri("https://restful-booker.herokuapp.com/booking")
	 .when()
	 	.get("{Booking ID}")
	 .then()
	 	.assertThat()
	 	.statusCode(200)
	 	.body("totalprice", Matchers.equalTo(2000))
	 	.body("additionalneeds", Matchers.equalTo("breakfast"));
		
	 	
	 	

		
		
		
				
				
		
		
		
		
		
		
		
		
	}

}
