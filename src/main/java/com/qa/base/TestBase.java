package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_201 = 201;
	public int RESPONSE_STATUS_CODE_400 = 400;
	public int RESPONSE_STATUS_CODE_404 = 404;
	public int RESPONSE_STATUS_CODE_204 = 204;
	public int RESPONSE_STATUS_CODE_401 = 401;
	public int RESPONSE_STATUS_CODE_500 = 500;
	
	public Properties prop;
	
	public TestBase(){
		
		try{	
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\deupadhyay\\workspace\\restapi\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(ip);
			}catch(FileNotFoundException e){
				e.printStackTrace();	
			}catch(IOException e){
				e.printStackTrace();
			}
		}

}
