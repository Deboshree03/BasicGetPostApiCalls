package com.qa.data;


public class Users {
	
	//This class is for the JSON PayLoad that we will be sending along with the POST Request to the server. 
	//It is a POJO class - Plain Old Java Object.
	
	String name;
	String job; 
	String id;
	String createdAt;
	
	public Users(){
		
		
		
	}
	
	public Users(String name, String job){
		this.name = name;
		this.job = job;
	}

	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	

}
