/**
 * 
 */
package com.bus.sbud.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bus.sbud.dao.jdbc.JDBCExpenseDAO;
import com.bus.sbud.model.Expense;

/**
 * @author chaitanyam
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/test-context.xml" })
public class ExpenseDAOTest {

	private static final Logger logger = Logger.getLogger("ExpenseDAOTest");

	@Autowired
	private JDBCExpenseDAO jdbcExpenseDAO;

	@Test
	public void testInsert() {
		Expense oldExpense = new Expense(9999, null, new Date(), 1L);
		Assert.assertNull(oldExpense.getId());
		Expense newExpense = jdbcExpenseDAO.insert(oldExpense);
		Assert.assertEquals(oldExpense.getAmount(), newExpense.getAmount(), 0.5);
	}

	@Test
	public void testUpdate() {
		Expense expense = new Expense(99.00, null, new Date(), 1L);
		expense.setId(1L);
		int rowCount = jdbcExpenseDAO.update(expense);
		Assert.assertEquals(1, rowCount);
	}

	@Test
	public void testFindById() {
		Expense expense = jdbcExpenseDAO.findById(1L);
		Assert.assertEquals("Amount can differ by 0.5", 8888,
				expense.getAmount(), 0.5);
	}

	@Test
	public void testFindAllExpensesByDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 0, 01);
		Date onDate = new Date(cal.getTimeInMillis());
		logger.info("Date" + onDate.toString());
		List<Expense> expenses = jdbcExpenseDAO.findAllExpensesByDate(onDate);
		logger.info("#######################################################");
		for (Expense expense : expenses) {
			logger.info("Expense ID" + expense.getId());
			logger.info("Expense Category" + expense.getCategoryId());
		}
		logger.info("#######################################################");
		Assert.assertEquals(2, expenses.size());
	}

	@Test
	public void testFindTotalByDayInAMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 0, 01);
		Date onDate = new Date(cal.getTimeInMillis());
		List<Map<String, Object>> list = jdbcExpenseDAO
				.findTotalByDayInAMonth(onDate);
		Assert.assertEquals(2, list.size());

	}

}
