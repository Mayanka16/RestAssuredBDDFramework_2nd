package com.qa.rest.utils;

import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;

public class BaseTest {
	
	
	@BeforeMethod
	public void beforeMethod() {
	
		
		//can be used for a single line request and response logs instead of giving in each line log().all()
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}

}
