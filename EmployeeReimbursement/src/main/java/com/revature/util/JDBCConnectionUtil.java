package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// Establish a connection between app and SQL database
public class JDBCConnectionUtil {

	public static Logger logger = LoggerFactory.getLogger(JDBCConnectionUtil.class);
	
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			logger.info(String.format("Making a DB connection with creds: \nURL: %s \nUsername: %s \nPassword: %s", System.getenv("DB_URL"),
					System.getenv("DB_USERNAME"),
					System.getenv("password")));
			
			conn = DriverManager.getConnection(System.getenv("DB_URL"),
					System.getenv("DB_USERNAME"),
					System.getenv("DB_PASSWORD"));
			
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return conn;
	}
	
}
