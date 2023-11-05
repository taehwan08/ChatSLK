package com.spring.biz.categorydetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("CategorydetailDAO")
public class CategorydetailDAO2 {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	//	static final private String SQL_INSERT = "INSERT INTO CATEGORYDETAIL (CATEGORYDETAILNUM,CATEGORYNUM, CATEGORYDETAILNAME) VALUES(CATEGORYDETAIL_SEQ.NEXTVAL,?,?)";
	static final private String SQL_INSERT = "INSERT INTO CATEGORYDETAIL (CATEGORYDETAILNUM,CATEGORYNUM, CATEGORYDETAILNAME) VALUES((SELECT COALESCE(MAX(CATEGORYDETAILNUM), 0) + 1 FROM CATEGORYDETAIL),?,?)";
	static final private String SQL_UPDATE = "UPDATE CATEGORYDETAIL SET CATEGORYDETAILNAME = ? WHERE CATEGORYDETAILNUM = ?";
	static final private String SQL_DELETE_CDNUM = "DELETE FROM CATEGORYDETAIL WHERE CATEGORYDETAILNUM = ?";
	static final private String SQL_DELETE_CTNUM = "DELETE FROM CATEGORYDETAIL WHERE CATEGORYNUM = ?";

	static final private String SQL_SELECTALL="SELECT C.CATEGORYNUM,C.CATEGORYNAME,CD.CATEGORYDETAILNUM,CD.CATEGORYDETAILNAME FROM CATEGORYDETAIL CD INNER JOIN CATEGORY C ON CD.CATEGORYNUM = C.CATEGORYNUM";
	static final private String SQL_SELECTALL_RECCATEGORY="SELECT CATEGORYDETAILNUM,CATEGORYDETAIL.CATEGORYNUM,CATEGORYDETAILNAME FROM CATEGORYDETAIL INNER JOIN CATEGORY ON CATEGORYDETAIL.CATEGORYNUM = CATEGORY.CATEGORYNUM LIMIT 0,?";
	static final private String SQL_SELECTALL_ADMIN="SELECT CD.CATEGORYNUM,C.CATEGORYNAME,SUM(CASE WHEN P.CATEGORYNUM = CD.CATEGORYNUM THEN 1 ELSE 0 END)AS CATEGORYCNT,CD.CATEGORYDETAILNUM,CD.CATEGORYDETAILNAME,SUM(CASE WHEN P.CATEGORYDETAILNUM = CD.CATEGORYDETAILNUM THEN 1 ELSE 0 END) AS CATEGORYDETAILCNT FROM CATEGORYDETAIL CD LEFT JOIN CATEGORY C ON C.CATEGORYNUM = CD.CATEGORYNUM LEFT JOIN PRODUCT P ON CD.CATEGORYDETAILNUM = P.CATEGORYDETAILNUM OR CD.CATEGORYNUM = P.CATEGORYNUM GROUP BY CD.CATEGORYNUM, C.CATEGORYNAME, CD.CATEGORYDETAILNUM, CD.CATEGORYDETAILNAME";
	static final private String SQL_SELECTONE="SELECT CATEGORYDETAILNUM,CATEGORYDETAILNAME FROM CATEGORYDETAIL WHERE CATEGORYDETAILNUM=?";
	static final private String SQL_SELECTONE_ADMIN="SELECT C.CATEGORYNUM, C.CATEGORYNAME, CD.CATEGORYDETAILNUM, CD.CATEGORYDETAILNAME FROM CATEGORYDETAIL CD INNER JOIN CATEGORY C ON CD.CATEGORYNUM = C.CATEGORYNUM WHERE C.CATEGORYNUM = ?";
	static final private String SQL_SELECTONE_CTNUM="SELECT C.CATEGORYNUM, C.CATEGORYNAME, CD.CATEGORYDETAILNUM, CD.CATEGORYDETAILNAME FROM CATEGORYDETAIL CD INNER JOIN CATEGORY C ON CD.CATEGORYNUM = C.CATEGORYNUM WHERE CD.CATEGORYDETAILNUM = ?";

	public boolean insert(CategorydetailVO cdVO) {
		System.out.println("CategorydetailDAO2 로그 insert() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_INSERT, cdVO.getCategoryNum(),cdVO.getCategoryDetailName());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	public boolean update(CategorydetailVO cdVO) {
		System.out.println("CategorydetailDAO2 로그 update() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_UPDATE, cdVO.getCategoryDetailName(),cdVO.getCategoryDetailNum());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	public boolean delete(CategorydetailVO cdVO) {
		System.out.println("CategorydetailDAO2 로그 delete() 메서드");
		try {
			int rs = 0;
			if(cdVO.getSearchCondition().equals("CDNUM")) {
				rs = jdbcTemplate.update(SQL_DELETE_CDNUM, cdVO.getCategoryDetailNum());
			}
			else if (cdVO.getSearchCondition().equals("CTNUM")) {
				rs = jdbcTemplate.update(SQL_DELETE_CTNUM, cdVO.getCategoryNum());
			}
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}



	public List<CategorydetailVO> selectAll(CategorydetailVO cdVO) {
		try {
			if (cdVO.getSearchCondition()==(null)||cdVO.getSearchCondition().equals("SELECTALL")) {
				return jdbcTemplate.query(SQL_SELECTALL,new CategorydetailRowMapper_SELECTALL());
			} else if(cdVO.getSearchCondition().equals("RECCATEGORY")){
				Object[] args = { cdVO.getCategoryDetailNum() };
				return jdbcTemplate.query(SQL_SELECTALL_RECCATEGORY, args, new CategorydetailRowMapper_SELECTALL());
			} else if(cdVO.getSearchCondition().equals("ADMIN")) {
				return jdbcTemplate.query(SQL_SELECTALL_ADMIN,new CategorydetailRowMapper_ADMIN());
			}
		}catch(Exception e) {
			return null;
		}
		return null;
	}

	public CategorydetailVO selectOne(CategorydetailVO cdVO) {
		System.out.println("CategorydetailDAO2 로그 selectOne() 메서드");
		try {
			if(cdVO.getSearchCondition() == null || cdVO.getSearchCondition().equals("SELECTONE")) {
				Object[] args = { cdVO.getCategoryDetailNum() };
				return jdbcTemplate.queryForObject(SQL_SELECTONE, args, new CategorydetailRowMapper());
			}else if (cdVO.getSearchCondition().equals("CTNUM")) {
				Object[] args = { cdVO.getCategoryDetailNum() };
				return jdbcTemplate.queryForObject(SQL_SELECTONE_CTNUM, args, new CategorydetailRowMapper_SELECTALL());
			}else if (cdVO.getSearchCondition().equals("ADMIN")) {
				Object[] args = { cdVO.getCategoryNum() };
				return jdbcTemplate.queryForObject(SQL_SELECTONE_ADMIN, args, new CategorydetailRowMapper_SELECTALL());
			}
		}catch(Exception e) {
			return null;
		}
		return null;
	}


}

class CategorydetailRowMapper_SELECTALL implements RowMapper<CategorydetailVO> {

	@Override
	public CategorydetailVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CategorydetailVO data = new CategorydetailVO();
		data.setCategoryNum(rs.getInt("CATEGORYNUM"));
		data.setCategoryName(rs.getString("CATEGORYNAME"));
		data.setCategoryDetailNum(rs.getInt("CATEGORYDETAILNUM"));
		data.setCategoryDetailName(rs.getString("CATEGORYDETAILNAME"));
		return data;
	}
}

class CategorydetailRowMapper implements RowMapper<CategorydetailVO> {

	@Override
	public CategorydetailVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CategorydetailVO data = new CategorydetailVO();
		data.setCategoryDetailNum(rs.getInt("CATEGORYDETAILNUM"));
		data.setCategoryDetailName(rs.getString("CATEGORYDETAILNAME"));
		return data;
	}
}

class CategorydetailRowMapper_ADMIN implements RowMapper<CategorydetailVO> {

	@Override
	public CategorydetailVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CategorydetailVO data = new CategorydetailVO();
		data.setCategoryNum(rs.getInt("CATEGORYNUM"));
		data.setCategoryName(rs.getString("CATEGORYNAME"));
		data.setCategoryCnt(rs.getInt("CATEGORYCNT"));
		data.setCategoryDetailNum(rs.getInt("CATEGORYDETAILNUM"));
		data.setCategoryDetailName(rs.getString("CATEGORYDETAILNAME"));
		data.setCategoryDetailCnt(rs.getInt("CATEGORYDETAILCNT"));
		return data;
	}
}