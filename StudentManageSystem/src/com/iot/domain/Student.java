package com.iot.domain;

public class Student {
	
	/**
	 * 关于学生的JAVABEAN类
	 */
	private String name; // 姓名
	private String id; // 学号
	private Integer age;
	private String phone;
	

	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Student(String name, String id, Integer age, String phone, String email, Integer sex, String grade) {
		super();
		this.name = name;
		this.id = id;
		this.age = age;
		this.phone = phone;
		this.email = email;
		this.sex = sex;
		this.grade = grade;
	}
	private String email;
	private Integer sex;
	private String grade; // 年级
	
	public Student(){
		
	}
	public Student(String name, String id, Integer age) {
		super();
		this.name = name;
		this.id = id;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  "["+ name + "---" + id + "---" + age + "]";
	}
	
}
