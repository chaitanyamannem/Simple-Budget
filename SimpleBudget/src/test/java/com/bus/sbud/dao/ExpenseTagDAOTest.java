/**
 * 
 */
package com.bus.sbud.dao;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bus.sbud.dao.jdbc.JDBCExpenseTagDAO;

/**
 * @author chaitanyam
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/test-context.xml" })
public class ExpenseTagDAOTest {

	@Autowired
	JDBCExpenseTagDAO jdbcExpenseTagDAO;

	@Test
	public void testInsert() {
		Assert.assertEquals(1, jdbcExpenseTagDAO.insert(1L, 1L));
	}

}
