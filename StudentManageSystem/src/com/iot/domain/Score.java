package com.iot.domain;

public class Score {
	
	private String name;
	private int os; // ����ϵͳ
	private int ds; // ���ݽṹ
	private int cn; // ���������
	private int co; // ��������ԭ��
	
	public Score(){
		
	}
	
	public Score(String name, int os, int ds, int cn, int co) {
		super();
		this.name = name;
		this.os = os;
		this.ds = ds;
		this.cn = cn;
		this.co = co;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOs() {
		return os;
	}
	public void setOs(int os) {
		this.os = os;
	}
	public int getDs() {
		return ds;
	}
	public void setDs(int ds) {
		this.ds = ds;
	}
	public int getCn() {
		return cn;
	}
	public void setCn(int cn) {
		this.cn = cn;
	}
	public int getCo() {
		return co;
	}
	public void setCo(int co) {
		this.co = co;
	}
	
	
}
