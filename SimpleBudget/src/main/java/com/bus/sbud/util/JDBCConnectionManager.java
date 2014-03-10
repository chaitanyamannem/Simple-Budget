/**
 * 
 */
package com.bus.sbud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

/**
 * @author chaitanyam
 * 
 */
public class JDBCConnectionManager {
	private static final Logger logger = Logger.getLogger("JDBCConnectionManager");

	public static Connection getConnection() {
		Connection con;
		String url = "jdbc:mysql://localhost:3306/";
		String db = "SimpleBudget_03";
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String pass = "123456";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url + db, user, pass);
			logger.info("Got JDBC Connection" + con.hashCode());
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
