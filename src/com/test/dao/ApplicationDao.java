package com.test.dao;

import java.sql.Connection;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.graalvm.compiler.lir.LIRInstruction.Use;
import com.test.beans.*;

public class ApplicationDao {
	
	
	
public int RegisterUser(User user) {
		int rowsAffected = 0;
		
		try {
			
			Connection connection = DBConnection.getConnectionToDatabase();

			String insertQuery = "insert into users values(?,?,?,?,?)";

			java.sql.PreparedStatement statement = connection.prepareStatement(insertQuery);
			statement.setString(1,user.getUsername());
			statement.setString(2,user.getName());
			statement.setString(3,user.getEmail());
			statement.setString(4,user.getPassword());
			statement.setString(5,user.getMobileNo());
			
			rowsAffected = statement.executeUpdate();
		}
		catch(SQLException exception) {
			exception.printStackTrace();
		}
		return rowsAffected;
	}

public int createUserGroupTable(String username) {
	int ans = 0;
	try {
		Connection conn = DBConnection.getConnectionToDatabase();
		Statement stmt = conn.createStatement();
		String tableName = username+"Groups";
		String sql = "create table "+tableName+"(groupId varchar(100),groupName varchar(100),groupDate varchar(100),groupOwner varchar(100))";
		
		stmt.executeUpdate(sql);
		ans = 1;
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return ans;
}
	
	public int loginUser(String username,String password) {
		int ans = 0;
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String selectQuery = "select * from users where username='"+username+"'";
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery(selectQuery);
			
			if(!set.next()) {
				ans = 1;
			}
			else {
				String actualPassword = set.getString("password");
				if(password.equals(actualPassword)) {
					ans = 2;
				}
				else {
					ans = 3;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ans;
	}
	
	public int createGroup(Group group,String username) {
		int ans = 0;
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			 String sql = "insert into groups values(?,?,?,?,?)"; 
			java.sql.PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1,group.getGroupId());
				statement.setString(2,group.getGroupName());
				statement.setString(3,group.getGroupOwner());
				statement.setString(4,group.getGroupDescription());
				statement.setString(5,group.getGroupCreatedDate());
			String tableName = username+"Groups";
				
				ans = statement.executeUpdate();
	      
			String sql1 = "insert into "+tableName+ " values(?,?,?,?)";
			java.sql.PreparedStatement statement1 = connection.prepareStatement(sql1);
			statement1.setString(1,group.getGroupId());
			statement1.setString(2,group.getGroupName());
			statement1.setString(3,group.getGroupCreatedDate());
			statement1.setString(4,username);
			ans = statement1.executeUpdate();
			
			
			Statement statement2 = connection.createStatement();
			String tableName1 = group.getGroupId()+"members";
			String sql2 = "create table "+tableName1+" (members varchar(100))";
			statement2.executeUpdate(sql2);
			
			String sql3 = "insert into "+tableName1+" values(?)"; 
			java.sql.PreparedStatement statement3 = connection.prepareStatement(sql3);
			statement3.setString(1,group.getGroupOwner());
			statement3.executeUpdate();
			
		}
		catch(Exception e) {
			e.printStackTrace();
			ans = 0;
		}
		return ans;
	}
	
	public int joinGroup(String groupId,String joinee,Group group) {
		/**
		 * Task1: Add member to the group members table.
		 * Task2: Add group to persons groups table. 
		 */
		int ans = 0;
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String groupTable = groupId+"members";
			String sql1 = "insert into "+groupTable + " values(?)";
			java.sql.PreparedStatement statement = connection.prepareStatement(sql1);
			statement.setString(1,joinee);
			ans = statement.executeUpdate();
			
			
			String groupTable1 = joinee+"Groups";
			String sql2 = "insert into "+groupTable1 + " values(?,?,?,?)";
			java.sql.PreparedStatement statement2 = connection.prepareStatement(sql2);
			statement2.setString(1,groupId);
			statement2.setString(2,group.getGroupName());
			statement2.setString(3,group.getGroupCreatedDate());
			statement2.setString(4,group.getGroupOwner());
			ans = statement2.executeUpdate();
			ans = 1;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ans;
	}
	
	public Map<String,Map<Integer,Group>> getGroupDetails(String groupId,String username) {
		int ans = 0;
		Group group = new Group();
		try {
			String tableName  = "groups";
			Connection connection = DBConnection.getConnectionToDatabase();
			String sql = "select * from "+tableName+ " where groupId = '"+groupId+"'";
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery(sql);
			
			if(set.next()) {
					group.setGroupId(groupId);
					group.setGroupCreatedDate(set.getString("groupDate"));
					group.setGroupName(set.getString("groupName"));
					group.setGroupOwner(set.getString("groupOwner"));
				ans = 2;
			}
			else {
				System.out.println("hello123");
				ans = 1;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		Map<String, Map<Integer, Group>> map = new HashMap<String,Map<Integer,Group>>();
		Map<Integer,Group> newMap = new HashMap<Integer,Group>();
		newMap.put(ans,group);
		map.put("ans",newMap);
		return map;
	}

	public Map<String,ArrayList<Group>> getMadeAndJoinedGroups(String username){

		HashMap<String,ArrayList<Group>> map = new HashMap<>();
		String tableName = username+"Groups";
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String sql = "select * from "+tableName;
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery(sql);
			map.put("groups_made_by_user",new ArrayList<Group>());
			map.put("groups_joined_by_user",new ArrayList<Group>());
			System.out.println("hi");
			while(set.next()) {
				Group group = new Group();
				group.setGroupCreatedDate(set.getString("groupDate"));
				group.setGroupOwner(set.getString("groupOwner"));
				group.setGroupName(set.getString("groupName"));
				group.setGroupId(set.getString("groupId"));
				System.out.println(group.getGroupId()+"zolal");
				System.out.println(group.getGroupOwner());
				System.out.println(username);
				if(group.getGroupOwner().equals(username)) {
					System.out.println(group.getGroupId()+"holla");
					map.get("groups_made_by_user").add(group);
				}
				else {
					map.get("groups_joined_by_user").add(group);
					System.out.println(group.getGroupId()+"hiii");
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return map;
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}