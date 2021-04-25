package com.sapient.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class DbUtil {

	private DbUtil() {
	}

	public static Connection createConnection() throws ClassNotFoundException, SQLException {
		ResourceBundle rb = ResourceBundle.getBundle("jdbc-info");
		
		String driverClassName = rb.getString("driverClassName");
		String url = rb.getString("url");
		String user = rb.getString("user");
		String password = rb.getString("password");

		Class.forName(driverClassName);
		return DriverManager.getConnection(url, user, password);

	}
}
