package com.test.beans;

public class MoneyStatus {
	
	String member_name;
	double money_lend;
	double money_owed;
	
	public MoneyStatus() {
		
	}
	
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public double getMoney_lend() {
		return money_lend;
	}
	public void setMoney_lend(double money_lend) {
		this.money_lend = money_lend;
	}
	public double getMoney_owed() {
		return money_owed;
	}
	public void setMoney_owed(double money_owed) {
		this.money_owed = money_owed;
	}
	public MoneyStatus(String member_name, double money_lend, double money_owed) {
		super();
		this.member_name = member_name;
		this.money_lend = money_lend;
		this.money_owed = money_owed;
	}
	
	
}
