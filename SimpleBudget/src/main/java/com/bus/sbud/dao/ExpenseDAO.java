package com.bus.sbud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.bus.sbud.model.Expense;
import com.bus.sbud.util.JDBCConnectionManager;

/**
 * @author chaitanyam
 * 
 */
public class ExpenseDAO {
	private static final Logger logger = Logger.getLogger("ExpenseDAO");
	private static Connection con = JDBCConnectionManager.getConnection();
	private static PreparedStatement pstmt = null;

	/**
	 * 
	 * 
	 * @throws SQLException
	 */
	public Long save(Expense expense) throws SQLException {

		String insertExpense = "INSERT INTO Expense(AMOUNT,WHEN_CREATED,TLM,CATEGORY_ID) VALUES (?,?,?,?);";
		// Always set the category id to default option other(id=1) if category
		// is not known
		Long categoryId = expense.getCategoryId() == null ? 1L : expense
				.getCategoryId();
		logger.info(categoryId + "========");
		try {
			pstmt = con.prepareStatement(insertExpense,
					java.sql.Statement.RETURN_GENERATED_KEYS);

			pstmt.setDouble(1, expense.getAmount());
			pstmt.setDate(2, new java.sql.Date(expense.getWhenCreated()
					.getTime()));
			pstmt.setDate(3, new java.sql.Date(expense.getTlm().getTime()));
			pstmt.setLong(4, categoryId);
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

	public void update(Expense expense) throws SQLException {

		String updateExpense = "UPDATE EXPENSE SET AMOUNT=?,TLM=?,CATEGORY_ID = ? WHERE ID = ?;";
		// Always set the category id to default option other(id=1) if category
		// is not known
		Long categoryId = expense.getCategoryId() == null ? 1L : expense
				.getCategoryId();
		logger.info(categoryId + "========");
		try {
			pstmt = con.prepareStatement(updateExpense);

			pstmt.setDouble(1, expense.getAmount());

			pstmt.setDate(2, new java.sql.Date(expense.getTlm().getTime()));
			pstmt.setLong(3, categoryId);
			pstmt.setLong(4, expense.getId());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}

	}

	public Expense findById(long id) throws SQLException {

		String getExpenseId = "SELECT * FROM EXPENSE WHERE ID= ?;";

		try {
			pstmt = con.prepareStatement(getExpenseId);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			Expense expense = new Expense();
			while (rs.next()) {
				expense.setId(rs.getLong(1));
				expense.setAmount(rs.getDouble(2));
				expense.setCategoryId(rs.getLong("CATEGORY_ID"));
				Date whenCreated =  new Date(rs.getDate("WHEN_CREATED").getTime());
				expense.setWhenCreated(whenCreated);
			}
			return expense;
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}

		return null;
	}

	public List<Long> findExpensesByDate(Date onDate) throws SQLException {
		List<Long> expenseids = new ArrayList<Long>();
		String getExpensesByDate = "SELECT ID FROM EXPENSE WHERE WHEN_CREATED = ?;";
		try {
			pstmt = con.prepareStatement(getExpensesByDate);
			pstmt.setDate(1, new java.sql.Date(onDate.getTime()));
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				expenseids.add(rs.getLong(1));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
		return expenseids;
	}

}
