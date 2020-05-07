package com.test.beans;

import java.util.ArrayList;

public class Transaction {
	
	String transactionFor;
	String transactionDescription;
	String groupId;
	String transactionId;
	String time;
	ArrayList<String> membersInvolved;
	


	public Transaction(String transactionFor, String transactionDescription, String groupId, String transactionId,
			String time, ArrayList<String> membersInvolved, double money, String doneBy) {
		super();
		this.transactionFor = transactionFor;
		this.transactionDescription = transactionDescription;
		this.groupId = groupId;
		this.transactionId = transactionId;
		this.time = time;
		this.membersInvolved = membersInvolved;
		this.money = money;
		this.doneBy = doneBy;
	}

	public ArrayList<String> getMembersInvolved() {
		return membersInvolved;
	}

	public void setMembersInvolved(ArrayList<String> membersInvolved) {
		this.membersInvolved = membersInvolved;
	}
	double money;
	String doneBy;
	public String getTransactionFor() {
		return transactionFor;
	}

	public void setTransactionFor(String transactionFor) {
		this.transactionFor = transactionFor;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	
	
	public Transaction() {
		
	}
	
	

	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getDoneBy() {
		return doneBy;
	}
	public void setDoneBy(String doneBy) {
		this.doneBy = doneBy;
	}
	
	
}
