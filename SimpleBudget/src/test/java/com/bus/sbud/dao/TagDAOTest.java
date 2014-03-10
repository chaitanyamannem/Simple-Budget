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

import com.bus.sbud.dao.jdbc.JDBCTagDAO;
import com.bus.sbud.model.Tag;

/**
 * @author chaitanyam
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/test-context.xml" })
public class TagDAOTest {

	@Autowired
	private JDBCTagDAO jdbcTagDAO;

	@Test
	public void testInsert() {
		Tag oldTag = new Tag("dummyTag");
		Tag newTag = jdbcTagDAO.insert(oldTag);
		Assert.assertEquals(oldTag.getName(), newTag.getName());
	}

	@Test
	public void testUpdate() {
		Tag tag = new Tag("updateTagName");
		tag.setId(1L);
		int rowCount = jdbcTagDAO.update(tag);
		Assert.assertEquals(1, rowCount);
	}
	
	@Test
	public void testFindIdByName() {
		Assert.assertEquals(new Long(1), jdbcTagDAO.findIdByName("firstTag"));
	}
	
	@Test
	public void testFindNameById(){
		Assert.assertEquals("firstTag", jdbcTagDAO.findNameById(1L));
	}

}
