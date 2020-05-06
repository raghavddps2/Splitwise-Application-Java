package com.test.beans;

public class Group {
		
	String groupName;
	String groupId;
	String groupDescription;
	String groupCreatedDate;
	String groupOwner;
	
	public Group() {
		
	}
	
	public Group(String groupName, String groupId, String groupDescription, String groupCreatedDate,
			String groupOwner) {
		super();
		this.groupName = groupName;
		this.groupId = groupId;
		this.groupDescription = groupDescription;
		this.groupCreatedDate = groupCreatedDate;
		this.groupOwner = groupOwner;
	}
	
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public String getGroupCreatedDate() {
		return groupCreatedDate;
	}

	public void setGroupCreatedDate(String groupCreatedDate) {
		this.groupCreatedDate = groupCreatedDate;
	}

	public String getGroupOwner() {
		return groupOwner;
	}

	public void setGroupOwner(String groupOwner) {
		this.groupOwner = groupOwner;
	}

	
	
}
