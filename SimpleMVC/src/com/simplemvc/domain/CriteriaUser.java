package com.simplemvc.domain;

/**
 * 
 * @author XK
 * ��User����֧��ģ����ѯ�����Է�װ���࣬���ں����Ĳ���
 */
public class CriteriaUser {
	public String getUserName() {
		if(userName == null) return "%%";
		return "%" + userName+ "%";
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		if(address == null) return "";
		return "%" + address + "";
	}
	public void setAddress(String address) {
		this.address = address;
	}
	private String userName;
	private Integer age;
	private String address;
	public CriteriaUser(String userName, Integer age, String address) {
		super();
		this.userName = userName;
		this.age = age;
		this.address = address;
	}
	public CriteriaUser() {
		super();
	}
	
	
}
