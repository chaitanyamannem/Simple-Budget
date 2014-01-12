/**
 * 
 */
package com.bus.sbud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bus.sbud.util.JDBCConnectionManager;

/**
 * @author chaitanyam
 *
 */
public class CategoryDAO {

	private static Connection con = JDBCConnectionManager.getConnection();
	private static PreparedStatement pstmt = null;
	private static Statement st = null;
	
	public Long findIdByName(String name) throws SQLException {
		Long id = null;

		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT ID FROM CATEGORY WHERE NAME='"
					+ name + "';");
			boolean found = rs.first();

			if (found) {
				id = rs.getLong(1);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
		return id;
	}
}
