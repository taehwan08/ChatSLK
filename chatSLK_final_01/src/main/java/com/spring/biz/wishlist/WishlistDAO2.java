package com.spring.biz.wishlist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.biz.pay.PayVO;

@Repository("WishlistDAO")
public class WishlistDAO2 {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 찜 추가
//	private static final String SQL_INSERT = "INSERT INTO WISHLIST (WISHLISTNUM,PRODUCTNUM,MEMBERID) VALUES(WISHLIST_SEQ.NEXTVAL,?,?)";
	private static final String SQL_INSERT = "INSERT INTO WISHLIST (WISHLISTNUM,PRODUCTNUM,MEMBERID) VALUES((SELECT COALESCE(MAX(WISHLISTNUM), 0) + 1 FROM WISHLIST),?,?)";
	// 찜 삭제
	private static final String SQL_DELETE = "DELETE FROM WISHLIST WHERE PRODUCTNUM=? AND MEMBERID=?";
	// 찜 목록 전체 출력
	private static final String SQL_SELECTALL = "SELECT WISHLISTNUM,MEMBERID,WISHLIST.PRODUCTNUM,(SELECT PATH FROM IMAGES WHERE PRODUCT.PRODUCTNUM = IMAGES.PRODUCTNUM AND ROWNUM = 1) AS PATH,PRODUCTNAME,COMPANY,PRODUCTPRICE FROM WISHLIST INNER JOIN PRODUCT ON WISHLIST.PRODUCTNUM = PRODUCT.PRODUCTNUM WHERE WISHLIST.MEMBERID = ? ORDER BY WISHLISTNUM DESC";
	private static final String SQL_SELECTALL_NOPIC = "SELECT WISHLISTNUM,MEMBERID,WISHLIST.PRODUCTNUM,PRODUCTNAME,COMPANY,PRODUCTPRICE FROM WISHLIST INNER JOIN PRODUCT ON WISHLIST.PRODUCTNUM = PRODUCT.PRODUCTNUM WHERE WISHLIST.MEMBERID=? ORDER BY WISHLIST.PRODUCTNUM";
	private static final String SQL_SELECTALL_WISHCNT = "SELECT * FROM ( SELECT PRODUCTNUM, COUNT(PRODUCTNUM) AS WISHCNT FROM WISHLIST GROUP BY PRODUCTNUM ORDER BY WISHCNT DESC) WHERE ROWNUM <= 3";
	private static final String SQL_SELECTONE = "SELECT WISHLISTNUM,MEMBERID,WISHLIST.PRODUCTNUM,PRODUCTNAME,COMPANY,PRODUCTPRICE FROM WISHLIST INNER JOIN PRODUCT ON WISHLIST.PRODUCTNUM = PRODUCT.PRODUCTNUM WHERE WISHLIST.MEMBERID = ? AND WISHLIST.PRODUCTNUM = ?";
	private static final String SQL_SELECTONE_WISHCNT = "SELECT PRODUCTNUM ,COUNT(PRODUCTNUM) WISHCNT FROM WISHLIST WHERE PRODUCTNUM = ? GROUP BY PRODUCTNUM;";

	public boolean insert(WishlistVO wVO) {
		System.out.println("WishlistDAO2 로그 insert() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_INSERT, wVO.getProductNum(), wVO.getMemberId());
			if (rs <= 0) {
				return false;
			}
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}


	public boolean delete(WishlistVO wVO) {
		System.out.println("WishlistDAO2 로그 delete() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_DELETE, wVO.getProductNum(), wVO.getMemberId());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}


	public List<WishlistVO> selectAll(WishlistVO wVO) {
		System.out.println("WishlistDAO2 로그 selectAll() 메서드");
		try {
			if (wVO.getSearchCondition() == null || wVO.getSearchCondition().equals("SELECTALL")) {
				Object[] args = { wVO.getMemberId() };
				return jdbcTemplate.query(SQL_SELECTALL, args, new WishlistRowMapper());
			} else if (wVO.getSearchCondition().equals("NOPIC")) {
				Object[] args = { wVO.getMemberId() };
				return jdbcTemplate.query(SQL_SELECTALL_NOPIC, args, new WishlistRowMapper_NOPIC());
			} else if (wVO.getSearchCondition().equals("WISHCNT")) {
				return jdbcTemplate.query(SQL_SELECTALL_WISHCNT, new WishlistRowMapper_WISHCNT());
			}
		}catch(Exception e) {
			return null;
		}
		return null;
	}


	public WishlistVO selectOne(WishlistVO wVO) {
		System.out.println("WishlistDAO2 로그 selectOne() 메서드");
		try {
			if(wVO.getSearchCondition() == null || wVO.getSearchCondition().equals("SELECTONE") ) {
				Object[] args = { wVO.getMemberId(),wVO.getProductNum() };
				return jdbcTemplate.queryForObject(SQL_SELECTONE, args, new WishlistRowMapper_SELECTONE());
			}else if (wVO.getSearchCondition().equals("WISHCNT")) {
				return jdbcTemplate.queryForObject(SQL_SELECTONE_WISHCNT, new WishlistRowMapper_WISHCNT());
			}
		}catch(Exception e) {
			return null;
		}
		return null;
	}

	public boolean update(WishlistVO wVO) {
		System.out.println("WishlistDAO2 로그 update() 메서드");
//		try {
//		int rs = jdbcTemplate.update(SQL_UPDATE);
//			if (rs <= 0) {
//				return false;
//			}
//		}catch(Exception e) {
//			return false;
//		}
		return true;
	}
}

// SQL_SELECTALL
class WishlistRowMapper implements RowMapper<WishlistVO> {
	@Override
	public WishlistVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		WishlistVO data = new WishlistVO();
		data.setWishlistNum(rs.getInt("WISHLISTNUM"));
		data.setMemberId(rs.getString("MEMBERID"));
		data.setProductNum(rs.getInt("PRODUCTNUM"));
		data.setPath(rs.getString("PATH"));
		data.setProductName(rs.getString("PRODUCTNAME"));
		data.setCompany(rs.getString("COMPANY"));
		data.setProductPrice(rs.getInt("PRODUCTPRICE"));
		return data;
	}
}
// SQL_SELECTONE
//WISHLISTNUM,MEMBERID,PRODUCTNUM,PRODUCTNAME,COMPANY,PRODUCTPRICE
class WishlistRowMapper_SELECTONE implements RowMapper<WishlistVO> {
	@Override
	public WishlistVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		WishlistVO data = new WishlistVO();
		data.setWishlistNum(rs.getInt("WISHLISTNUM"));
		data.setMemberId(rs.getString("MEMBERID"));
		data.setProductNum(rs.getInt("PRODUCTNUM"));
		data.setProductName(rs.getString("PRODUCTNAME"));
		data.setCompany(rs.getString("COMPANY"));
		data.setProductPrice(rs.getInt("PRODUCTPRICE"));
		return data;
	}
}

// SQL_SELECTALL_NOPIC
class WishlistRowMapper_NOPIC implements RowMapper<WishlistVO> {
	@Override
	public WishlistVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		WishlistVO data = new WishlistVO();
		data.setWishlistNum(rs.getInt("WISHLISTNUM"));
		data.setMemberId(rs.getString("MEMBERID"));
		data.setProductNum(rs.getInt("PRODUCTNUM"));
		data.setProductName(rs.getString("PRODUCTNAME"));
		data.setCompany(rs.getString("COMPANY"));
		data.setProductPrice(rs.getInt("PRODUCTPRICE"));
		return data;
	}
}

// SQL_SELECTALL_WISHCNT
class WishlistRowMapper_WISHCNT implements RowMapper<WishlistVO> {
	@Override
	public WishlistVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		WishlistVO data = new WishlistVO();
		data.setProductNum(rs.getInt("PRODUCTNUM"));
		data.setWishCnt(rs.getInt("WISHCNT"));
		return data;
	}
}
