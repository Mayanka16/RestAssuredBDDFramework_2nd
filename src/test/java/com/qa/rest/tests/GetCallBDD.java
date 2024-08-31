package com.qa.rest.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetCallBDD {

	@Test
	public void test_numberOffCircuitsFor2023Season() {
//		given().
//		when().
//		then().
//		assert()
		
		given().
		when().
		get("http://ergast.com/api/f1/2023/circuits.json").
		then().
		assertThat().
		body("MRData.CircuitTable.Circuits.circuitId", hasSize(22));
		
		
		
		
		
		
	}

}
