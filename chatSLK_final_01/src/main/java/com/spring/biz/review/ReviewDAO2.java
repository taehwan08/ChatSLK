package com.spring.biz.review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("ReviewDAO")
public class ReviewDAO2 {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
//	private static final String SQL_INSERT_USER="INSERT INTO REVIEW (REVIEWNUM,MEMBERID,TITLE,CONTENT,STAR,PRODUCTNUM) VALUES(REVIEW_SEQ.NEXTVAL,?,?,?,?,?)";
//	private static final String SQL_INSERT_ADMIN="INSERT INTO REVIEW (REVIEWNUM,MEMBERID,TITLE,CONTENT,STAR,PRODUCTNUM,REVIEWTIME) VALUES(REVIEW_SEQ.NEXTVAL,?,?,?,?,?,?)";
	private static final String SQL_INSERT_USER="INSERT INTO REVIEW (REVIEWNUM,MEMBERID,TITLE,CONTENT,STAR,PRODUCTNUM) VALUES((SELECT COALESCE(MAX(REVIEWNUM), 0) + 1 FROM REVIEW),?,?,?,?,?)";
	private static final String SQL_INSERT_ADMIN="INSERT INTO REVIEW (REVIEWNUM,MEMBERID,TITLE,CONTENT,STAR,PRODUCTNUM,REVIEWTIME) VALUES((SELECT COALESCE(MAX(REVIEWNUM), 0) + 1 FROM REVIEW),?,?,?,?,?,?)";
	private static final String SQL_UPDATE_REVIEW="UPDATE REVIEW SET TITLE =?, CONTENT=?, STAR=?, REVIEWTIME=DEFAULT WHERE REVIEWNUM=?";
	private static final String SQL_UPDATE_REPLY="UPDATE REVIEW SET REPLY=? WHERE REVIEWNUM=?";
	private static final String SQL_DELETE="DELETE FROM REVIEW WHERE REVIEWNUM = ? AND MEMBERID = ?";
	private static final String SQL_SELECTALL="SELECT R.REVIEWNUM,R.MEMBERID,R.TITLE,R.CONTENT,R.REPLY,R.STAR,P.PRODUCTNAME,R.REVIEWTIME FROM REVIEW R INNER JOIN PRODUCT P ON R.PRODUCTNUM=P.PRODUCTNUM ORDER BY REVIEWTIME DESC"; 
	private static final String SQL_SELECTALL_SHOPSINGLE="SELECT R.REVIEWNUM ,R.MEMBERID ,R.TITLE ,R.CONTENT ,R.REVIEWTIME ,R.STAR FROM REVIEW R WHERE R.PRODUCTNUM = ?"; 
	private static final String SQL_SELECTALL_ADMIN_MAIN="SELECT R.REVIEWNUM,R.MEMBERID,R.TITLE,R.CONTENT,R.REPLY,R.STAR,P.PRODUCTNAME,R.REVIEWTIME FROM REVIEW R INNER JOIN PRODUCT P ON R.PRODUCTNUM=P.PRODUCTNUM WHERE REPLY IS NULL ORDER BY REVIEWTIME DESC"; 
	private static final String SQL_SELECTONE="SELECT REVIEWNUM,MEMBERID,TITLE,CONTENT,REPLY,STAR,PRODUCTNUM,REVIEWTIME FROM REVIEW WHERE REVIEWNUM = ?";
	private static final String SQL_SELECTONE_USER="SELECT R.REVIEWNUM,R.MEMBERID,R.TITLE,R.CONTENT,R.REPLY,R.STAR,P.PRODUCTNAME,R.REVIEWTIME FROM REVIEW R INNER JOIN PRODUCT P ON R.PRODUCTNUM=P.PRODUCTNUM WHERE R.MEMBERID = ? AND R.PRODUCTNUM = ?";
	private static final String SQL_SELECTONE_ADMIN_RECENTREVIEW="SELECT COUNT(REVIEWNUM) REVIEWCOUNT FROM REVIEW WHERE REVIEWTIME > SYSDATE-1";
	private static final String SQL_SELECTONE_AVGSTAR="SELECT AVG(STAR) AVGSTAR FROM REVIEW R WHERE PRODUCTNUM = ?";

	public boolean insert(ReviewVO rVO) {
		System.out.println("ReviewDAO2 로그 insert() 메서드");
		try {
			int rs = 0;
			if(rVO.getSearchCondition() == null || rVO.getSearchCondition().equals("USER")){
				rs = jdbcTemplate.update(SQL_INSERT_USER,rVO.getMemberId(),rVO.getTitle(),rVO.getContent(),rVO.getStar(),rVO.getProductNum());
			}
			else if(rVO.getSearchCondition().equals("ADMIN")) {
				rs = jdbcTemplate.update(SQL_INSERT_ADMIN,rVO.getMemberId(),rVO.getTitle(),rVO.getContent(),rVO.getStar(),rVO.getProductNum(),rVO.getReviewTime());
			}
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	public boolean update(ReviewVO rVO) {
		System.out.println("ReviewDAO2 로그 update() 메서드");
		try {
			int rs = 0;
			if(rVO.getSearchCondition() == null || rVO.getSearchCondition().equals("REVIEW")) {
				rs = jdbcTemplate.update(SQL_UPDATE_REVIEW,rVO.getTitle(),rVO.getContent(),rVO.getStar(),rVO.getReviewNum());
			}
			else if(rVO.getSearchCondition().equals("REPLY")) {
				rs = jdbcTemplate.update(SQL_UPDATE_REPLY,rVO.getReply(),rVO.getReviewNum());
			}
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	public boolean delete(ReviewVO rVO) {
		System.out.println("ReviewDAO2 로그 delete() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_DELETE, rVO.getReviewNum(),rVO.getMemberId());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}


	public List<ReviewVO> selectAll(ReviewVO rVO) {
		try {
			if(rVO.getSearchCondition()==null || rVO.getSearchCondition().equals("SELECTALL")) {
				return jdbcTemplate.query(SQL_SELECTALL,new ReviewRowMapper());
			}
			else if(rVO.getSearchCondition().equals("SHOPSINGLE")) {
				Object[] args = { rVO.getProductNum() };
				return jdbcTemplate.query(SQL_SELECTALL_SHOPSINGLE, args,new ReviewRowMapper_SHOPSINGLE());
			}
			else if(rVO.getSearchCondition().equals("ADMIN_MAIN")) {
				return jdbcTemplate.query(SQL_SELECTALL_ADMIN_MAIN,new ReviewRowMapper());
			}
			
		}catch(Exception e) {
			return null;
		}
		return null;
	}

	public ReviewVO selectOne(ReviewVO rVO) {
		try {
			if(rVO.getSearchCondition() == null || rVO.getSearchCondition().equals("SELECTONE")) {
				Object[] args = { rVO.getReviewNum() };
				return jdbcTemplate.queryForObject(SQL_SELECTONE, args, new ReviewRowMapper());
			}
			else if (rVO.getSearchCondition().equals("ADMIN_RECENTREVIEW")) {
				return jdbcTemplate.queryForObject(SQL_SELECTONE_ADMIN_RECENTREVIEW, new ReviewRowMapper_ADMIN());
			}
			else if (rVO.getSearchCondition().equals("AVGSTAR")) {
				Object[] args = { rVO.getProductNum() };
				return jdbcTemplate.queryForObject(SQL_SELECTONE_AVGSTAR, args, new ReviewRowMapper_AVGSTAR());
			}
			else if (rVO.getSearchCondition().equals("USER")) {
				Object[] args = { rVO.getMemberId(), rVO.getProductNum() };
				return jdbcTemplate.queryForObject(SQL_SELECTONE_USER, args, new ReviewRowMapper());
			}
		}catch(Exception e) {
			return null;
		}
		return null;
	}
}


class ReviewRowMapper implements RowMapper<ReviewVO> {
	//REVIEWNUM,MEMBERID,TITLE,CONTENT,REPLY,STAR,PRODUCTNUM,REVIEWTIME
	@Override
	public ReviewVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReviewVO data=new ReviewVO();
		data.setReviewNum(rs.getInt("REVIEWNUM"));
		data.setMemberId(rs.getString("MEMBERID"));
		data.setTitle(rs.getString("TITLE"));
		data.setContent(rs.getString("CONTENT"));
		data.setReply(rs.getString("REPLY"));
		data.setStar(rs.getInt("STAR"));
		data.setProductName(rs.getString("PRODUCTNAME"));
		data.setReviewTime(rs.getDate("REVIEWTIME"));
		return data;
	}
}
class ReviewRowMapper_SHOPSINGLE implements RowMapper<ReviewVO> {
	@Override
	public ReviewVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReviewVO data=new ReviewVO();
		data.setReviewNum(rs.getInt("REVIEWNUM"));
		data.setMemberId(rs.getString("MEMBERID"));
		data.setTitle(rs.getString("TITLE"));
		data.setContent(rs.getString("CONTENT"));
		data.setReviewTime(rs.getDate("REVIEWTIME"));
		data.setStar(rs.getInt("STAR"));
		return data;
	}
}
class ReviewRowMapper_ADMIN implements RowMapper<ReviewVO> {
	@Override
	public ReviewVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReviewVO data=new ReviewVO();
		data.setReviewCount(rs.getInt("REVIEWCOUNT"));
		return data;
	}
}
class ReviewRowMapper_AVGSTAR implements RowMapper<ReviewVO> {
	@Override
	public ReviewVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReviewVO data=new ReviewVO();
		data.setStar(rs.getInt("AVGSTAR"));
		return data;
	}
}

