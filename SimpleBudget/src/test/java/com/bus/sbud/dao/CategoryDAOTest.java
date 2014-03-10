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

import com.bus.sbud.dao.jdbc.JDBCCategoryDAO;
import com.bus.sbud.model.Category;

/**
 * @author chaitanyam
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/test-context.xml" })
public class CategoryDAOTest {

	private static final Logger logger = Logger.getLogger("ExpenseDAOTest");

	@Autowired
	private JDBCCategoryDAO jdbcCategoryDAO;

	@Test
	public void testInsert() {
		Category oldCategory = new Category("dummyCategory");
		Category newCategory = jdbcCategoryDAO.insert(oldCategory);
		Assert.assertEquals(oldCategory.getName(), newCategory.getName());
	}

	@Test
	public void testUpdate() {
		Category category = new Category("updateCategoryName");
		category.setId(1L);
		int rowCount = jdbcCategoryDAO.update(category);
		Assert.assertEquals(1, rowCount);
	}

	@Test
	public void testFindIdByName() {
		Assert.assertEquals(new Long(1), jdbcCategoryDAO.findIdByName("other"));
	}
	
	@Test
	public void testFindNameById(){
		Assert.assertEquals("other", jdbcCategoryDAO.findNameById(1L));
	}
	
	@Test
	public void testFindTotalByCategoryForMonth(){
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 1, 01);
		Date onDate = new Date(cal.getTimeInMillis());
		List<Map<String,Object>> list = jdbcCategoryDAO.findTotalByCategoryForMonth(onDate);
		Assert.assertEquals(3, list.size());
	}

}
