/**
 * 
 */
package com.bus.sbud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.bus.sbud.util.JDBCConnectionManager;

/**
 * @author chaitanyam
 * 
 */
public class CategoryDAO {

	private static Connection con = JDBCConnectionManager.getConnection();
	private static PreparedStatement pstmt = null;
	private static Statement st = null;
	private static final Logger logger = Logger.getLogger("CategoryDAO");

	public Long findIdByName(String name) throws SQLException {

		Long id = null;

		try {
			st = con.createStatement();
			ResultSet rs = st
					.executeQuery("SELECT ID FROM CATEGORY WHERE NAME='" + name
							+ "';");
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

	public String findNameById(Long id) throws SQLException {

		String name = "";

		try {
			st = con.createStatement();
			ResultSet rs = st
					.executeQuery("SELECT NAME FROM CATEGORY WHERE id='" + id
							+ "';");
			boolean found = rs.first();

			if (found) {
				name = rs.getString(1);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
		return name;
	}

	public Map<String, Double> findTotalByCategory() throws SQLException {
		Map<String, Double> map = new LinkedHashMap<String, Double>();
		String query = "SELECT CATEGORY_ID, SUM(AMOUNT) from EXPENSE GROUP BY CATEGORY_ID;";
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Long id = rs.getLong(1);
				Double amount = rs.getDouble(2);
				map.put(findNameById(id), amount);
			}
			logger.info("Tag Map size" + map.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();

		}

		return map;
	}
}
