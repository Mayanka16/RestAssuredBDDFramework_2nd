package com.qa.rest.utils;

import java.io.File;

public class FileNameConstants {

	// Method 1 using File for System.getProperty("user.dir") for default path(//Users//mayanka//eclipse-workspace//RestAssuredBDDFramework_2nd)

	// static File BASE_PATH = new File(System.getProperty("user.dir") + "//src//test//resources//");

	// Method 2 by giving full path and making static final String
	public static final String BASE_PATH = "//Users//mayanka//eclipse-workspace//RestAssuredBDDFramework_2nd//src//test//resources//";// ".src//test//resources//";

	public static final String POST_API_REQUEST_BODY = BASE_PATH + "/PostAPIRequestBody.txt";
	
	public static final String JSON_SCHEMA = BASE_PATH + "/expectedJSONSchema.txt";
	
	public static final String TOKEN_REQUEST_BODY = BASE_PATH + "/tokenRequestAPI.txt";
	
	public static final String PUT_REQUEST_BODY = BASE_PATH + "/putRequestAPI.txt";
	
	public static final String PATCH_API_REQUEST_BODY = BASE_PATH + "/patchAPIRequestBody.txt";
	
	
	

}
