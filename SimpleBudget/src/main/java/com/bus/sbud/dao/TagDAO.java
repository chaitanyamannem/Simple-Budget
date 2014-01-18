/**
 * 
 */
package com.bus.sbud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.bus.sbud.model.Tag;
import com.bus.sbud.util.JDBCConnectionManager;

/**
 * @author chaitanyam
 * 
 */
public class TagDAO {

	private static Connection con = JDBCConnectionManager.getConnection();
	private static PreparedStatement pstmt = null;
	private static Statement st = null;
	private static final Logger logger = Logger.getLogger("TagDAO");

	public Long findIdByName(String name) throws SQLException {
		long id = -1;

		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT ID FROM TAG WHERE NAME='"
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

	public String findById(long id) throws SQLException {
		Tag tag = new Tag();
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM TAG WHERE ID='" + id
					+ "';");

			while (rs.next()) {
				tag.setName(rs.getString(2));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
		return tag.getName();
	}

	public List<String> findTagNamesByIds(List<Long> tagids) {

		List<String> tagNamesList = new ArrayList<String>();

		for (Long id : tagids) {
			try {
				tagNamesList.add(findById(id));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return tagNamesList;

	}

	public List<Long> findTagsByExpense(long expenseId) throws SQLException {
		List<Long> tagids = new ArrayList<Long>();
		try {
			st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select TAG_ID from EXPENSE_TAG WHERE EXPENSE_ID = "
							+ expenseId + ";");

			while (rs.next()) {
				tagids.add(rs.getLong(1));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
		return tagids;
	}

	public Boolean isTagLinkedToExpense(Long tagId) throws SQLException {

		try {
			st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select EXPENSE_ID from EXPENSE_TAG WHERE TAG_ID = "
							+ tagId + ";");
			boolean found = rs.first();

			return found;
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
		return null;
	}

	public Long save(Tag tag) throws SQLException {

		String insertTag = "INSERT INTO TAG(NAME) VALUES (?);";
		try {
			pstmt = con.prepareStatement(insertTag,
					java.sql.Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, tag.getName());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.first();
			long id = rs.getLong(1);
			return id;
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
		return null;
	}

	public void linkTagNExpense(long expenseId, long tagId) throws SQLException {

		try {

			String linkExpenseNTag = "INSERT INTO EXPENSE_TAG(EXPENSE_ID,TAG_ID) VALUES (?,?);";

			pstmt = con.prepareStatement(linkExpenseNTag);

			pstmt.setLong(1, expenseId);
			pstmt.setLong(2, tagId);

			pstmt.execute();

			System.out.println("query executed");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pstmt.close();

		}
	}

	public Map<String, Long> findTagNameAndIDsBySearchString(
			String searchParameter) throws SQLException {
		String getTags = "SELECT * FROM TAG WHERE NAME LIKE '"
				+ searchParameter + "%';";
		Map<String, Long> tagMap = new LinkedHashMap<String, Long>();
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(getTags);
			while (rs.next()) {
				Long id = rs.getLong(1);
				String tagName = rs.getString(2);
				tagMap.put(tagName, id);
			}
			logger.info("Tag Map size" + tagMap.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();

		}
		return tagMap;
	}
}
