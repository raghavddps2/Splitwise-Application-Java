package com.test.beans;

public class CashFlowDisplay {
	
	
	String pay_by = "";
	String pay_to  = "";
	double money = 0;
	public String getPay_by() {
		return pay_by;
	}
	public void setPay_by(String pay_by) {
		this.pay_by = pay_by;
	}
	public String getPay_to() {
		return pay_to;
	}
	public void setPay_to(String pay_to) {
		this.pay_to = pay_to;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public CashFlowDisplay(String pay_by, String pay_to, double money) {
		super();
		this.pay_by = pay_by;
		this.pay_to = pay_to;
		this.money = money;
	}
	public CashFlowDisplay() {
		// TODO Auto-generated constructor stub
	}
	
	
}
