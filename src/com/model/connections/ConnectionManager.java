package com.model.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 * Connection manager for managing my connection object.
 * @author Nwoke Fortune Chiemeziem
 * @Version 1.0
 */
public class ConnectionManager {
	private static Connection conn;
	private static final String URL = "jdbc:mysql://localhost:3306/newsreader";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "fortune1566";

	private static final Properties connectionProps = new Properties();
	//Singleton design pattern
	private ConnectionManager() {
	}// singleton
	
	/**
	 * Returns an instance of the connection.
	 * @return the connection if it exist else it creates one and returns it
	 */
	public static Connection getInstance() {

		connectionProps.put("user", USER_NAME);
		connectionProps.put("password", PASSWORD);

		try {
			conn = DriverManager.getConnection(URL, connectionProps);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Connected to database");
		return conn;
	}
}
