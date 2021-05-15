package com.herokuapp.resfullbooker;

import org.testng.annotations.Test;

import com.herokuapp.restfulbooker.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class HealthCheckTest extends BaseTest{

	@Test
	public void healthCheckTest() {
		given().
		when().
			get("https://restful-booker.herokuapp.com/ping").
		then().
			assertThat().
			statusCode(201);
	}
	
	@Test
	public void headersAndCookiesTest() {
		// Create a response
		Response response = RestAssured.given(spec).get("/ping");
		// Extract headers
		Headers headers = response.getHeaders();
		System.out.println("Headers: "+headers);
		// Extract cookies
		Cookies cookies = response.detailedCookies();
		System.out.println("Cookies: "+cookies);
		
	}
}
