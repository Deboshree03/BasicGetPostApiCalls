package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetApiTest extends TestBase {
	
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
	
	@Test(priority=1)
	public void getMethodCallTest() throws ClientProtocolException, IOException{
		
		restClient = new RestClient(); // Creating an object of the RestClient class to be able to call the methods in it.
		httpResponse = restClient.get(url);
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();  //Gives the status code of the request
		System.out.println("Status Code :--->" + statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status Code is not 200"); //The message is displayed only when the assertion fails. 
		//RESPONSE_STATUS_CODE_200 is the expected value defined in the TestBase class to avoid hard coding of values in the test case.
		
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");  
		//After the above line of code, the complete JSON Response will be converted into String and stored in the variable responseString
		
		JSONObject responseJson = new JSONObject(responseString);
		//We need the response in JSON so the above code will convert the response from String to JSONObject and it will be stored in
		//responseJson reference variable
		
		System.out.println("Response JSON from API :----> " + responseJson); //Printing the Response
		
		
		//Single Value Assertions : ------->>
		String parsedPerPageValue = TestUtil.getValueByJsonPath(responseJson,"/per_page");  //Getting value of a particular key from the JSON Response. 
		//To get the value first argument we are passing is the entire response from the request.
		System.out.println("The value of Per Page is :----> " + parsedPerPageValue);   //Printing the value of the key to check.
		Assert.assertEquals(Integer.parseInt(parsedPerPageValue), 6);  
		 //The actual value is an integer so we convert the String into an integer. Integer is a Wrapper Class.
		
		String parsedTotalValue = TestUtil.getValueByJsonPath(responseJson,"/total");  //Getting value of a particular key from the JSON Response. 
		//To get the value first argument we are passing is the entire response from the request.
		System.out.println("The value of Total is :----> " + parsedTotalValue);   //Printing the value of the key to check.
		Assert.assertEquals(Integer.parseInt(parsedTotalValue), 12);
		
		
		//Assertions after getting value from JSONArray : -------->>
		String dataLastName0 = TestUtil.getValueByJsonPath(responseJson, "/data[0]/last_name");
		String dataId0 = TestUtil.getValueByJsonPath(responseJson, "/data[0]/id");
		String dataAvatar0 = TestUtil.getValueByJsonPath(responseJson, "/data[0]/avatar");
		String dataFirstName0 = TestUtil.getValueByJsonPath(responseJson, "/data[0]/first_name");
		String dataEmail0 = TestUtil.getValueByJsonPath(responseJson, "/data[0]/email");
		//Fetching value from Array data[], first index(in case of arrays, index starts with 0), 
		//followed by the attribute or key name whose value we want to fetch.
		
		System.out.println("Last Name from first index of Array data[] is :----> " + dataLastName0);
		System.out.println("Id from first index of Array data[] is :----> " + dataId0);
		System.out.println("Avatar Name from first index of Array data[] is :----> " + dataAvatar0);
		System.out.println("First Name from first index of Array data[] is :----> " + dataFirstName0);
		System.out.println("Email from first index of Array data[] is :----> " + dataEmail0);
		
		Assert.assertEquals(dataLastName0, "Bluth");
		Assert.assertEquals(Integer.parseInt(dataId0), 1);
		Assert.assertEquals(dataAvatar0, "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg");
		Assert.assertEquals(dataFirstName0, "George");
		Assert.assertEquals(dataEmail0, "george.bluth@reqres.in");
		
		
		
		Header[] headerArray = httpResponse.getAllHeaders(); 
		//The above line of code is to get the header of the response. The method getAllHeaders() returns an array of Header type. 
		
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		//The above code will store the headers in key value pairs for ease in accessing and iteration.
		
		for(Header header :headerArray){
			allHeaders.put(header.getName(), header.getValue());
		}
		
		System.out.println("Headers Array : -----> " + allHeaders);
		//After storing the headers key value pairs in HashMap, we are iterating it and printing all the Headers
		
		
	}
	
	
	@Test(priority=2)
	public void getMethodCallTestWithHeaders() throws ClientProtocolException, IOException{
		
		restClient = new RestClient(); // Creating an object of the RestClient class to be able to call the methods in it.
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		httpResponse = restClient.get(url, headerMap);
		
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();  //Gives the status code of the request
		System.out.println("Status Code :--->" + statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status Code is not 200"); //The message is displayed only when the assertion fails. 
		//RESPONSE_STATUS_CODE_200 is the expected value defined in the TestBase class to avoid hard coding of values in the test case.
		
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");  
		//After the above line of code, the complete JSON Response will be converted into String and stored in the variable responseString
		
		JSONObject responseJson = new JSONObject(responseString);
		//We need the response in JSON so the above code will convert the response from String to JSONObject and it will be stored in
		//responseJson reference variable
		
		System.out.println("Response JSON from API :----> " + responseJson); //Printing the Response
		
		
		//Single Value Assertions : ------->>
		String parsedPerPageValue = TestUtil.getValueByJsonPath(responseJson,"/per_page");  //Getting value of a particular key from the JSON Response. 
		//To get the value first argument we are passing is the entire response from the request.
		System.out.println("The value of Per Page is :----> " + parsedPerPageValue);   //Printing the value of the key to check.
		Assert.assertEquals(Integer.parseInt(parsedPerPageValue), 6);  
		 //The actual value is an integer so we convert the String into an integer. Integer is a Wrapper Class.
		
		String parsedTotalValue = TestUtil.getValueByJsonPath(responseJson,"/total");  //Getting value of a particular key from the JSON Response. 
		//To get the value first argument we are passing is the entire response from the request.
		System.out.println("The value of Total is :----> " + parsedTotalValue);   //Printing the value of the key to check.
		Assert.assertEquals(Integer.parseInt(parsedTotalValue), 12);
		
		
		//Assertions after getting value from JSONArray : -------->>
		String dataLastName0 = TestUtil.getValueByJsonPath(responseJson, "/data[0]/last_name");
		String dataId0 = TestUtil.getValueByJsonPath(responseJson, "/data[0]/id");
		String dataAvatar0 = TestUtil.getValueByJsonPath(responseJson, "/data[0]/avatar");
		String dataFirstName0 = TestUtil.getValueByJsonPath(responseJson, "/data[0]/first_name");
		String dataEmail0 = TestUtil.getValueByJsonPath(responseJson, "/data[0]/email");
		//Fetching value from Array data[], first index(in case of arrays, index starts with 0), 
		//followed by the attribute or key name whose value we want to fetch.
		
		System.out.println("Last Name from first index of Array data[] is :----> " + dataLastName0);
		System.out.println("Id from first index of Array data[] is :----> " + dataId0);
		System.out.println("Avatar Name from first index of Array data[] is :----> " + dataAvatar0);
		System.out.println("First Name from first index of Array data[] is :----> " + dataFirstName0);
		System.out.println("Email from first index of Array data[] is :----> " + dataEmail0);
		
		Assert.assertEquals(dataLastName0, "Bluth");
		Assert.assertEquals(Integer.parseInt(dataId0), 1);
		Assert.assertEquals(dataAvatar0, "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg");
		Assert.assertEquals(dataFirstName0, "George");
		Assert.assertEquals(dataEmail0, "george.bluth@reqres.in");
		
		
		
		Header[] headerArray = httpResponse.getAllHeaders(); 
		//The above line of code is to get the header of the response. The method getAllHeaders() returns an array of Header type. 
		
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		//The above code will store the headers in key value pairs for ease in accessing and iteration.
		
		for(Header header :headerArray){
			allHeaders.put(header.getName(), header.getValue());
		}
		
		System.out.println("Headers Array : -----> " + allHeaders);
		//After storing the headers key value pairs in HashMap, we are iterating it and printing all the Headers
		
		
	}
	

}
