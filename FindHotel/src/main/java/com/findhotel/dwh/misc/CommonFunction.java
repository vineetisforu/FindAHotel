package com.findhotel.dwh.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class CommonFunction {

	public static Properties prop ; 
	
	public CommonFunction() {

		prop = PropertiesLoader.getProperties();
		
	}
	
	/**
	 * Create JDBC Connection given the parameter for connection
	 * @param url Database connection String in the format jdbc:mysql://host:port/db
	 * @param user Username
	 * @param password Password
	 * @param driverClass Driver Class for the connection. Default: Mysql
	 * @return Connection Object
	 */
	public static Connection getDBConnection(String url, String user, String password, String driverClass) {
		
		Connection conn =null;
		
		if(driverClass==null || driverClass.trim().equals(""))
			driverClass = "com.mysql.jdbc.Driver";
		
		try {
			
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		
		return conn;
	}
	
}
