package com.iot.domain;
/**
 * 封装了教室信息的javabean类
 * 
 * 教师的信息比较简单
 * @author XK
 *
 */
public class Teacher {
	private String name;
	private String jno; // 教工号
	private String department; // 所在系
	
	public Teacher(){
		
	}
	
	public Teacher(String name, String jno, String department) {
		super();
		this.name = name;
		this.jno = jno;
		this.department = department;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJno() {
		return jno;
	}
	public void setJno(String jno) {
		this.jno = jno;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	
}
