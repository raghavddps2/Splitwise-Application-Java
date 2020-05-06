package com.test.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
	
	public static Connection getConnectionToDatabase() {
		Connection connection = null;
		try {
			String username = "root";
			String password = "";
			String url = "jdbc:mysql://localhost:3306/project3";
			Class.forName("com.mysql.jdbc.Driver");
			System.out.print("MySQL JDBC driver registered");
			connection = DriverManager.getConnection(url,username,password);
		}
		catch(ClassNotFoundException e) {
			System.out.print("Where is your mysql jdbc driver");
			e.printStackTrace();
		}
		catch(SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		if(connection != null) {
			System.out.println("Connection made to DB!");
		}
		return connection;
	}
	


}