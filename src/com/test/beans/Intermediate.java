package com.test.beans;

public class Intermediate {
	
	double money = 0;
	String member  = "";
	public Intermediate(double money, String member) {
		super();
		this.money = money;
		this.member = member;
	}
	public Intermediate() {
		// TODO Auto-generated constructor stub
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
}
