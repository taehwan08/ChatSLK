package com.spring.biz.cart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("CartDAO")
public class CartDAO2 {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 장바구니 추가
	//	private static final String SQL_INSERT = "INSERT INTO CART (CARTNUM,PRODUCTNUM,MEMBERID,CARTCOUNT) VALUES(CART_SEQ.NEXTVAL,?,?,?)";
	private static final String SQL_INSERT = "INSERT INTO CART (CARTNUM,PRODUCTNUM,MEMBERID,CARTCOUNT) VALUES((SELECT COALESCE(MAX(CARTNUM), 0) + 1 FROM CART),?,?,?)";
	// 갯수 변경
	private static final String SQL_UPDATE = "UPDATE CART SET CARTCOUNT = CARTCOUNT+? WHERE MEMBERID = ? AND PRODUCTNUM = ?";
	// 장바구니 삭제
	private static final String SQL_DELETE = "DELETE FROM CART WHERE MEMBERID = ? AND PRODUCTNUM = ?";
	// 장바구니 전체 출력
	private static final String SQL_SELECTALL = "SELECT CARTNUM,(SELECT PATH FROM IMAGES WHERE PRODUCT.PRODUCTNUM = IMAGES.PRODUCTNUM AND ROWNUM = 1) AS PATH,MEMBERID,PRODUCT.PRODUCTNUM,PRODUCTNAME,COMPANY,PRODUCTPRICE,CARTCOUNT FROM CART INNER JOIN PRODUCT ON CART.PRODUCTNUM = PRODUCT.PRODUCTNUM WHERE MEMBERID = ? ORDER BY CARTNUM DESC";

	private static final String SQL_SELECTONE = "SELECT CARTNUM,(SELECT PATH FROM IMAGES WHERE PRODUCT.PRODUCTNUM = IMAGES.PRODUCTNUM AND ROWNUM = 1) AS PATH,MEMBERID,PRODUCT.PRODUCTNUM,PRODUCTNAME,COMPANY,PRODUCTPRICE,CARTCOUNT FROM CART INNER JOIN PRODUCT ON CART.PRODUCTNUM = PRODUCT.PRODUCTNUM WHERE MEMBERID = ? AND CART.PRODUCTNUM = ?";

	private static final String SQL_SELECTONE_CARTNUM = "SELECT CARTNUM,(SELECT PATH FROM IMAGES WHERE PRODUCT.PRODUCTNUM = IMAGES.PRODUCTNUM AND ROWNUM = 1) AS PATH,MEMBERID,PRODUCT.PRODUCTNUM,PRODUCTNAME,COMPANY,PRODUCTPRICE,CARTCOUNT FROM CART INNER JOIN PRODUCT ON CART.PRODUCTNUM = PRODUCT.PRODUCTNUM WHERE CARTNUM = ?";
	
	// 장바구니 추가
	public boolean insert(CartVO cVO) {
		System.out.println("CartDAO2 로그 insert() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_INSERT,cVO.getProductNum(), cVO.getMemberId(),cVO.getCartCount());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	// 갯수 추가
	public boolean update(CartVO cVO) {
		System.out.println("CartDAO2 로그 update() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_UPDATE, cVO.getCartCount(),cVO.getMemberId(),cVO.getProductNum());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}


	// 장바구니 삭제
	public boolean delete(CartVO cVO) {
		System.out.println("CartDAO2 로그 delete() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_DELETE, cVO.getMemberId(),cVO.getProductNum());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	// 장바구니 전체 출력
	public List<CartVO> selectAll(CartVO cVO) {
		try {
			Object[] args = { cVO.getMemberId() };
			return jdbcTemplate.query(SQL_SELECTALL, args, new CartRowMapper());
		}
		catch (Exception e) {
			return null;
		}
	}

	public CartVO selectOne(CartVO cVO) {
		try {
			if(cVO.getSearchCondition() == null || cVO.getSearchCondition().equals("SELECTONE")) {
				Object[] args = { cVO.getMemberId(), cVO.getProductNum() };
				return jdbcTemplate.queryForObject(SQL_SELECTONE, args, new CartRowMapper());
			}else if (cVO.getSearchCondition().equals("CARTNUM")) {
				Object[] args = { cVO.getCartNum() };
				return jdbcTemplate.queryForObject(SQL_SELECTONE_CARTNUM, args, new CartRowMapper());
			}
		}
		catch (Exception e) {
			return null;
		}
		return null;
	}

	class CartRowMapper implements RowMapper<CartVO> {

		@Override
		public CartVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			CartVO data = new CartVO();
			data.setCartNum(rs.getInt("CARTNUM"));
			data.setPath(rs.getString("PATH"));
			data.setMemberId(rs.getString("MEMBERID"));
			data.setProductNum(rs.getInt("PRODUCTNUM"));
			data.setProductName(rs.getString("PRODUCTNAME"));
			data.setCompany(rs.getString("COMPANY"));
			data.setProductPrice(rs.getInt("PRODUCTPRICE"));
			data.setCartCount(rs.getInt("CARTCOUNT"));
			return data;
		}
	}
}
