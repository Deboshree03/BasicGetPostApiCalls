package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {
	
	
	//1. GET method without Headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();  //Creates a default HTTP Connection
		HttpGet httpGet = new HttpGet(url);                            //Creates a Get Connection with the particular URL. HTTP get request
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet); //Executes the request. Hit the GET url. Similar as clicking the Send button in Postman
		// In the httpResponse, the entire response is present including headers, status response, status code and all.
		
		return httpResponse;	
		
	}
	
	//2. GET method with Headers
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();  //Creates a default HTTP Connection
		HttpGet httpGet = new HttpGet(url);                            //Creates a Get Connection with the particular URL. HTTP get request
		
		for(Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}
		
		
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet); //Executes the request. Hit the GET url. Similar as clicking the Send button in Postman
		// In the httpResponse, the entire response is present including headers, status response, status code and all.
		
		return httpResponse;
						
	}
	
	//3. POST method
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpClient = HttpClients.createDefault();  //Creates a default HTTP Connection
		HttpPost http_post = new HttpPost(url);                        //HTTP post request
		http_post.setEntity(new StringEntity(entityString));
		//setEntity() method is used for defining the payload. To pass the payload. 
		
		//For passing the Headers :--->>
		for(Map.Entry<String, String> entry : headerMap.entrySet()) {
			http_post.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse httpResponse = httpClient.execute(http_post);
		
		return httpResponse;
		
	}
		
		


}
