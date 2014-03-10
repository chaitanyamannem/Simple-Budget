/**
 * 
 */
package com.bus.sbud.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.bus.sbud.dao.ExpenseTagDAO;
import com.bus.sbud.model.Tag;
import com.bus.sbud.util.SBUDConstants;

/**
 * @author chaitanyam
 * 
 */
@Repository
public class JDBCExpenseTagDAO implements ExpenseTagDAO {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insertExpenseTag;

	@Autowired
	public void init(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.insertExpenseTag = new SimpleJdbcInsert(dataSource)
				.withTableName(SBUDConstants.TABLE_EXPENSE_TAG);

	}

	public int insert(Long expenseId, Long tagId) {
		Map<String, Object> parameters = new HashMap<String, Object>(4);
		parameters.put(SBUDConstants.TABLE_COMMON_COLUMN_EXPENSE_ID, expenseId);
		parameters.put(SBUDConstants.TABLE_COMMON_COLUMN_TAG_ID, tagId);
		return insertExpenseTag.execute(parameters);
	}
	
	//TODO - unit test
	public int delete(Long expenseId, Long tagId) {
		String deleteTagFromExpenseQuery = "DELETE FROM EXPENSE_TAG WHERE EXPENSE_ID=? AND TAG_ID=?";
		return this.jdbcTemplate.update(deleteTagFromExpenseQuery, expenseId, tagId);
	}

	public List<Tag> findsAllTagsByExpenseId(Long expenseId) {
		String getTagIdsByExpenseQuery = "SELECT * FROM EXPENSE_TAG WHERE EXPENSE_ID = ?;";
		List<Tag> tags = this.jdbcTemplate.query(getTagIdsByExpenseQuery,
				new Object[] { expenseId }, new RowMapper<Tag>() {
					public Tag mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Tag tag = new Tag();
						tag.setId(rs
								.getLong(SBUDConstants.TABLE_COMMON_COLUMN_TAG_ID));
						return tag;
					}
				});
		return tags;
	}

}
