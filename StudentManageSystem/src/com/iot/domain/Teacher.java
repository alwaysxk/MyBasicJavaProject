package com.iot.domain;
/**
 * ��װ�˽�����Ϣ��javabean��
 * 
 * ��ʦ����Ϣ�Ƚϼ�
 * @author XK
 *
 */
public class Teacher {
	private String name;
	private String jno; // �̹���
	private String department; // ����ϵ
	
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
