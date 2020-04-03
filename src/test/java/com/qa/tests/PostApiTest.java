package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostApiTest extends TestBase {
	
	TestBase testBase;  // Creating an object of the super class to be able to use the methods present in it.
	String hostUrl;
	String serviceUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse httpResponse;
	
	@BeforeMethod
	public void setUp() {
		testBase = new TestBase(); 
		hostUrl = prop.getProperty("hostURL"); 
		serviceUrl = prop.getProperty("serviceURL");
		
		url = hostUrl + serviceUrl;
		
	}
	
	@Test
	public void postMethodCallTest() throws JsonGenerationException, JsonMappingException, IOException{
		
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		//Jackson API: We need to create a Utility which is provided by Jackson API for Marshelling (Converting Java Object to JSON Object)
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("jennifer", "manager");  //Expected Users object.
		
		//Object to JSON File Conversion :----->>
		mapper.writeValue(new File("C:\\Users\\deupadhyay\\workspace\\restapi\\src\\main\\java\\com\\qa\\data\\users.json"), users);
		//In new File, we have to give the path of the JSON file. users is the object value of the Users class that we have to pass.
		
		//Java Object to JSON in String (Marshelling)
		String usersJsonString = mapper.writeValueAsString(users);
		System.out.println(usersJsonString);
		
		httpResponse = restClient.post(url, usersJsonString, headerMap);
		//url is the uri to send the request
		//entityString is the usersJsonString where we have stored the JSON as String
		//We have to pass the headers which is present in headerMap.
		
		//1. Check Status Code :---->>
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_201);
		
		//2. JSON String :--->> To convert it into a readable format.
		//Converting the raw json into string.
		String responseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is :--->> " + responseJson);
		
		//Now we have to validate that whatever values we have passed, it got created correctly or not.
		//Reading the response. Users.class is the value type of the response.
		//JSON to Java Object (Unmarshelling)
		Users usersResponseObj = mapper.readValue(responseString, Users.class);  //Actual Users object.
		System.out.println(usersResponseObj);
		
		Assert.assertEquals(usersResponseObj.getName(), users.getName(), "Assertion failed");
		Assert.assertEquals(usersResponseObj.getJob(), users.getJob(), "Assertion failed");
		
		System.out.println(users.getName().equals(usersResponseObj.getName()));
		System.out.println(users.getJob().equals(usersResponseObj.getJob()));
		
		
		
	}
	

}
