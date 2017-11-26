package com.iot.domain;

public class Score {
	
	private String name;
	private int os; // 操作系统
	private int ds; // 数据结构
	private int cn; // 计算机网络
	private int co; // 计算机组成原理
	
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
