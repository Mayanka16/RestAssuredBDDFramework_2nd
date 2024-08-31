package com.qa.rest.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.qa.rest.utils.FileNameConstants;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;

public class PostAPIRequestUsingFile {
	@Test
	public void postAPIRequest() {
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
				.extract()
				.response();
		
		
		JSONArray jsonArray = JsonPath.read(response.body().asString() , "$.booking..firstname");
		System.out.println(jsonArray.get(0));
				
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
