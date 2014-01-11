/**
 * 
 */
package com.bus.sbud.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author chaitanyam
 * 
 */
public class JDBCConnectionManager {

	public static Connection getConnection() {
		Connection con;
		String url = "jdbc:mysql://localhost:3306/";
		String db = "MyBudget";
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String pass = "123456";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url + db, user, pass);

			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
