package com.mstore.dbutill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

//Static Class to connect to my sql using jdbc
public class DBConnectStatic {
	private static String url = "jdbc:mysql://localhost:3306/mstore";
	private static String username = "root";
	private static String password ="Abdullah@450";
	private static String driver = "com.mysql.jdbc.Driver";
	private static Connection connection = null;
	
	
	public static void Connect() {
		try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database");
        } catch (Exception e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }
    }
	
	public static String Add2DB(String TableName, String[] columns, String values[]) {
		Connect();
		String query = "INSERT INTO " + TableName + " (";
		for (int i = 0; i < columns.length; i++) {
			query += columns[i];
			if (i != columns.length - 1) {
				query += ", ";
			}
		}
		query += ") VALUES (";
		for (int i = 0; i < values.length; i++) {
			query += "'" + values[i] + "'";
			if (i != values.length - 1) {
				query += ", ";
			}
		}
		query += ")";
		System.out.println(query);
		
		try {
			return String.valueOf(connection.createStatement().executeUpdate(query));
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}
	
	public static String Add2DBWithoutPK(String TableName, String[] columns, String values[]) {
		Connect();
		String query = "INSERT INTO " + TableName + " (";
		for (int i = 1; i < columns.length; i++) {
			query += columns[i];
			if (i != columns.length - 1) {
				query += ", ";
			}
		}
		query += ") VALUES (";
		for (int i = 1; i < values.length; i++) {
			query += "'" + values[i] + "'";
			if (i != values.length - 1) {
				query += ", ";
			}
		}
		query += ")";
		System.out.println(query);
		
		try {
			return String.valueOf(connection.createStatement().executeUpdate(query));
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}
	
	public static String UpdateDB(String TableName, String[] columns, String values[]) {
		Connect();
		String query = "UPDATE " + TableName + " SET ";
		for (int i = 1; i < columns.length; i++) {
			query += columns[i] + " = '" + values[i] + "'";
			if (i != columns.length - 1) {
				query += ", ";
			}
		}
		query += " WHERE " + columns[0] + "=\"" + values[0] + "\"";
		System.out.println(query);

		try {
			return String.valueOf(connection.createStatement().executeUpdate(query));
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}
	
	public static String DeleteFromDB(String TableName, String column, String value) {
		Connect();
		String query = "DELETE FROM " + TableName + " WHERE "+ column +"='" + value + "'";
		System.out.println(query);

		try {
			return String.valueOf(connection.createStatement().executeUpdate(query));
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}
	
	public static ResultSet ReadFromDB(String TableName) {
		Connect();
		String query = "SELECT * FROM " + TableName;
		System.out.println(query);

		try {
			return connection.createStatement().executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ResultSet ReadFromDB(String TableName, String column, String value) {
		Connect();
		String query = "SELECT * FROM " + TableName + " Where " + column + " = '" + value + "'";
		System.out.println(query);

		try {
			return connection.createStatement().executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
