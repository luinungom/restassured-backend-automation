package com.herokuapp.resfullbooker;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class CreateBookingTests {
	
	@Test
	public void createBookingTest() {
		// Create JSON body
		JSONObject body = new JSONObject();
		body.put("firstname", "Luis");
		body.put("lastname", "Nunies");
		body.put("totalprice", 777);
		body.put("depositpaid", true);
		
		JSONObject bodyDates = new JSONObject();
		
		 
		// Get response
		
		// Test response
	}

}
