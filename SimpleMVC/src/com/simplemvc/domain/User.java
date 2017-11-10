package com.simplemvc.domain;

public class User {
	
	private Integer id;
	private String address;
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public User(String address, int age, String userName) {
		super();
		this.address = address;
		this.age = age;
		this.userName = userName;
	}
	
	public User(String userName){
		this.userName = userName;
	}
	private int age;
	
	private String userName;
	
	public User(){
		
	}
	
	
	public User(Integer id, String userName, int age, String address) {
		super();
		this.id = id;
		this.address = address;
		this.age = age;
		this.userName = userName;
	}


	
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String toString(){
		return "Name = " + this.userName;
	}
}
