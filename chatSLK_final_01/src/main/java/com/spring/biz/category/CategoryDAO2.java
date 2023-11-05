package com.spring.biz.category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("CategoryDAO")
public class CategoryDAO2 {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
//	static final private String SQL_INSERT = "INSERT INTO CATEGORY (CATEGORYNUM,CATEGORYNAME) VALUES(CATEGORY_SEQ.NEXTVAL,?)";
	static final private String SQL_INSERT = "INSERT INTO CATEGORY (CATEGORYNUM,CATEGORYNAME) VALUES((SELECT COALESCE(MAX(CATEGORYNUM), 0) + 1 FROM CATEGORY),?)";
	static final private String SQL_UPDATE = "UPDATE CATEGORY SET CATEGORYNAME = ? WHERE CATEGORYNUM = ?";
	static final private String SQL_DELETE = "DELETE FROM CATEGORY WHERE CATEGORYNUM = ?";
	static final private String SQL_SELECTALL = "SELECT CATEGORYNUM,CATEGORYNAME FROM CATEGORY";
	//	static final private String SQL_SELECTALL_LIMIT="SELECT CATEGORYNUM,CATEGORYNAME FROM CATEGORY LIMIT 0,?";
	static final private String SQL_SELECTONE = "SELECT CATEGORYNUM,CATEGORYNAME FROM CATEGORY WHERE CATEGORYNUM=?";
	static final private String SQL_SELECTONE_ADMIN = "SELECT MAX(CATEGORYNUM) AS CATEGORYNUM FROM CATEGORY";

	

	public boolean insert(CategoryVO ctVO) {
		System.out.println("CategoryDAO2 로그 insert() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_INSERT, ctVO.getCategoryName());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;

	}


	public boolean update(CategoryVO ctVO) {
		System.out.println("CategoryDAO2 로그 update() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_UPDATE, ctVO.getCategoryName(), ctVO.getCategoryNum());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;

	}
	public boolean delete(CategoryVO ctVO) {
		System.out.println("CategoryDAO2 로그 delete() 메서드");
		try {
			
			int rs = jdbcTemplate.update(SQL_DELETE, ctVO.getCategoryNum());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;
		
	}
	public List<CategoryVO> selectAll(CategoryVO ctVO) {
		System.out.println("CategoryDAO2 로그 selectAll() 메서드");
		try {
			return jdbcTemplate.query(SQL_SELECTALL, new CategoryRowMapper());
		}catch(Exception e) {
			return null;
		}
	}

	public CategoryVO selectOne(CategoryVO ctVO) {
		System.out.println("CategoryDAO2 로그 selectOne() 메서드");
		try {
			if (ctVO.getSearchCondition() == null || ctVO.getSearchCondition().equals("SELECTONE")) {
				Object[] args = { ctVO.getCategoryNum() };
				return jdbcTemplate.queryForObject(SQL_SELECTONE, args, new CategoryRowMapper());
			} else if (ctVO.getSearchCondition().equals("ADMIN")) {
				return jdbcTemplate.queryForObject(SQL_SELECTONE_ADMIN, new CategoryRowMapper_ADMIN());
			}
		}catch(Exception e) {
			return null;
		}
		return null;
	}
}

class CategoryRowMapper implements RowMapper<CategoryVO> {

	@Override
	public CategoryVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CategoryVO data = new CategoryVO();
		data.setCategoryNum(rs.getInt("CATEGORYNUM"));
		data.setCategoryName(rs.getString("CATEGORYNAME"));
		return data;
	}
}

class CategoryRowMapper_ADMIN implements RowMapper<CategoryVO> {
	
	@Override
	public CategoryVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CategoryVO data = new CategoryVO();
		data.setCategoryNum(rs.getInt("CATEGORYNUM"));
		return data;
	}
}
