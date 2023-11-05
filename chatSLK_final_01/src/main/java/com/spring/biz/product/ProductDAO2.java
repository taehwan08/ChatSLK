package com.spring.biz.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("ProductDAO")
public class ProductDAO2 {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	static final private String SQL_INSERT = "INSERT INTO PRODUCT (PRODUCTNUM,PRODUCTNAME,COMPANY,PRODUCTPRICE,PRODUCTEXPLAIN,CATEGORYNUM,CATEGORYDETAILNUM,PRODUCTCNT) VALUES((SELECT COALESCE(MAX(PRODUCTNUM), 0) + 1 FROM PRODUCT),?,?,?,?,?,?,?)"; 
	static final private String SQL_UPDATE_NAME = "UPDATE PRODUCT SET PRODUCTNAME=? WHERE PRODUCTNUM=?";
	static final private String SQL_UPDATE_COMPANY = "UPDATE PRODUCT SET COMPANY=? WHERE PRODUCTNUM=?";
	static final private String SQL_UPDATE_PRICE = "UPDATE PRODUCT SET PRODUCTPRICE=? WHERE PRODUCTNUM=?";
	static final private String SQL_UPDATE_EXPLAIN = "UPDATE PRODUCT SET PRODUCTEXPLAIN=? WHERE PRODUCTNUM=?";
	static final private String SQL_UPDATE_ALL = "UPDATE PRODUCT SET PRODUCTNAME = ?,COMPANY = ?,PRODUCTPRICE = ?,PRODUCTEXPLAIN = ?,CATEGORYNUM = ?,CATEGORYDETAILNUM = ?,PRODUCTCNT = ? WHERE PRODUCTNUM = ?";
	
	static final private String SQL_DELETE = "DELETE FROM PRODUCT WHERE PRODUCTNUM=?";
	static final private String SQL_SELECTALL = 
	         "SELECT "
	         + "P.PRODUCTNUM, I.PATH, P.PRODUCTNAME, P.COMPANY, P.PRODUCTPRICE, P.PRODUCTCNT, P.PRODUCTEXPLAIN, P.CATEGORYNUM, C.CATEGORYNAME, P.CATEGORYDETAILNUM, CD.CATEGORYDETAILNAME "
	         + "FROM PRODUCT P "
	         + "INNER JOIN CATEGORY C "
	         + "ON P.CATEGORYNUM = C.CATEGORYNUM "
	         + "INNER JOIN CATEGORYDETAIL CD "
	         + "ON P.CATEGORYDETAILNUM = CD.CATEGORYDETAILNUM "
	         + "LEFT JOIN ( SELECT PRODUCTNUM, PATH, ROW_NUMBER() OVER (PARTITION BY PRODUCTNUM ORDER BY IMAGENUM) AS IMAGERANK FROM IMAGES ) I "
	         + "ON P.PRODUCTNUM = I.PRODUCTNUM AND I.IMAGERANK = 1";
	
	   
	   static final private String SQL_SELECTALL_PRODUCTLIST = 
	         "SELECT "
	         + "P.PRODUCTNUM, P.PRODUCTNAME, P.COMPANY, P.PRODUCTPRICE, P.PRODUCTEXPLAIN, P.CATEGORYNUM, C.CATEGORYNAME, P.CATEGORYDETAILNUM, CD.CATEGORYDETAILNAME, P.PRODUCTCNT "
	         + "FROM PRODUCT P "
	         + "INNER JOIN CATEGORY C "
	         + "ON P.CATEGORYNUM = C.CATEGORYNUM "
	         + "INNER JOIN CATEGORYDETAIL CD "
	         + "ON P.CATEGORYDETAILNUM = CD.CATEGORYDETAILNUM ORDER BY PRODUCTNUM";
	   
	   static final private String SQL_SELECTALL_SEARCH = 
	         "SELECT "
	         + "P.PRODUCTNUM,(SELECT PATH FROM IMAGES WHERE P.PRODUCTNUM = IMAGES.PRODUCTNUM AND ROWNUM = 1) AS PATH,"
	         + "P.PRODUCTNAME, P.COMPANY, P.PRODUCTPRICE, P.PRODUCTEXPLAIN, P.CATEGORYNUM, C.CATEGORYNAME, P.CATEGORYDETAILNUM, CD.CATEGORYDETAILNAME, P.PRODUCTCNT "
	         + "FROM PRODUCT P "
	         + "INNER JOIN CATEGORY C ON P.CATEGORYNUM = C.CATEGORYNUM "
	         + "INNER JOIN CATEGORYDETAIL CD ON P.CATEGORYDETAILNUM = CD.CATEGORYDETAILNUM "
	         + "WHERE P.PRODUCTNAME LIKE '%'|| ? ||'%'";
	   
	   static final private String SQL_SELECTALL_CATEGORY = 
	         "SELECT "
	         + "P.PRODUCTNUM,(SELECT PATH FROM IMAGES WHERE P.PRODUCTNUM = IMAGES.PRODUCTNUM AND ROWNUM = 1) AS PATH,"
	         + "PRODUCTNAME,COMPANY,PRODUCTPRICE,PRODUCTEXPLAIN,P.CATEGORYNUM,CATEGORYNAME,P.CATEGORYDETAILNUM,CATEGORYDETAILNAME, P.PRODUCTCNT "
	         + "FROM PRODUCT P "
	         + "INNER JOIN CATEGORY C "
	         + "ON P.CATEGORYNUM = C.CATEGORYNUM "
	         + "INNER JOIN CATEGORYDETAIL CD "
	         + "ON P.CATEGORYDETAILNUM = CD.CATEGORYDETAILNUM WHERE P.CATEGORYDETAILNUM=?";
	static final private String SQL_SELECTALL_RECPRODUCT = "SELECT PATH,PRODUCT.PRODUCTNUM,PRODUCTNAME,COMPANY,PRODUCTPRICE,PRODUCTEXPLAIN,PRODUCT.CATEGORYNUM,CATEGORYNAME,PRODUCT.CATEGORYDETAILNUM,CATEGORYDETAILNAME, P.PRODUCTCNT FROM PRODUCT INNER JOIN IMAGES ON PRODUCT.PRODUCTNUM = IMAGES.PRODUCTNUM INNER JOIN CATEGORY ON PRODUCT.CATEGORYNUM = CATEGORY.CATEGORYNUM INNER JOIN CATEGORYDETAIL ON PRODUCT.CATEGORYDETAILNUM = CATEGORYDETAIL.CATEGORYDETAILNUM LIMIT 0,?";

	static final private String SQL_SELECTONE = 
			"SELECT PRODUCT.PRODUCTNUM,(SELECT PATH FROM IMAGES WHERE PRODUCT.PRODUCTNUM = IMAGES.PRODUCTNUM AND ROWNUM = 1) AS PATH,"
			+ "PRODUCTNAME,COMPANY,PRODUCTPRICE,PRODUCTEXPLAIN,PRODUCT.CATEGORYNUM,CATEGORYNAME,PRODUCT.CATEGORYDETAILNUM,CATEGORYDETAILNAME, PRODUCT.PRODUCTCNT "
			+ "FROM PRODUCT "
			+ "INNER JOIN CATEGORY "
			+ "ON PRODUCT.CATEGORYNUM = CATEGORY.CATEGORYNUM "
			+ "INNER JOIN CATEGORYDETAIL "
			+ "ON PRODUCT.CATEGORYDETAILNUM = CATEGORYDETAIL.CATEGORYDETAILNUM WHERE PRODUCTNUM=?";
	
	
	static final private String SQL_SELECTONE_PRODUCTNUM = "SELECT PRODUCTNUM FROM PRODUCT WHERE PRODUCTNUM = (SELECT MAX(PRODUCTNUM) FROM PRODUCT)";
	

	public boolean insert(ProductVO pVO) {
		System.out.println("ProductDAO2 로그 insert() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_INSERT, pVO.getProductName(), pVO.getCompany(),
					pVO.getProductPrice(), pVO.getProductExplain(), pVO.getCategoryNum(), pVO.getCategoryDetailNum(), pVO.getProductCnt());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}


	public boolean update(ProductVO pVO) {
		System.out.println("ProductDAO2 로그 update() 메서드");
		try {
			int rs = 0;
			if (pVO.getSearchCondition() != null && pVO.getSearchCondition().equals("NAME")) {
				rs = jdbcTemplate.update(SQL_UPDATE_NAME, pVO.getProductName(), pVO.getProductNum());
			} else if (pVO.getSearchCondition().equals("COMPANY")) {
				rs = jdbcTemplate.update(SQL_UPDATE_COMPANY, pVO.getCompany(), pVO.getProductNum());
			} else if (pVO.getSearchCondition().equals("PRICE")) {
				rs = jdbcTemplate.update(SQL_UPDATE_PRICE, pVO.getProductPrice(), pVO.getProductNum());
			} else if (pVO.getSearchCondition().equals("EXPLAIN")) {
				rs = jdbcTemplate.update(SQL_UPDATE_EXPLAIN, pVO.getProductExplain(), pVO.getProductNum());
			} else if (pVO.getSearchCondition().equals("ALL")) {
				//PRODUCTNAME = ?,COMPANY = ?,PRODUCTPRICE = ?,PRODUCTEXPLAIN = ?,CATEGORYNUM = ?,CATEGORYDETAILNUM = ?,PRODUCTCNT = ? WHERE PRODUCTNUM = ?
				rs = jdbcTemplate.update(SQL_UPDATE_ALL,pVO.getProductName(),pVO.getCompany(),pVO.getProductPrice(), pVO.getProductExplain(),pVO.getCategoryNum(),pVO.getCategoryDetailNum(),pVO.getProductCnt(), pVO.getProductNum());
			}
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}


	public boolean delete(ProductVO pVO) {
		System.out.println("ProductDAO2 로그 delete() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_DELETE, pVO.getProductNum());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	public List<ProductVO> selectAll(ProductVO pVO) {
		System.out.println("ProductDAO2 로그 selectAll() 메서드");
		try {
			if (pVO.getSearchCondition() == null || pVO.getSearchCondition().equals("SELECTALL")) {
				return jdbcTemplate.query(SQL_SELECTALL, new ProductRowMapper());
			} else if (pVO.getSearchCondition().equals("PRODUCTLIST")) {
				return jdbcTemplate.query(SQL_SELECTALL_PRODUCTLIST, new ProductRowMapper_PRODUCTLIST());
			} else if (pVO.getSearchCondition().equals("CATEGORY")) {
				Object[] args = { pVO.getCategoryDetailNum() };
				return jdbcTemplate.query(SQL_SELECTALL_CATEGORY, args, new ProductRowMapper());
			} else if (pVO.getSearchCondition().equals("RECPRODUCT")) {
				Object[] args = { pVO.getListcnt() };
				return jdbcTemplate.query(SQL_SELECTALL_RECPRODUCT, args, new ProductRowMapper());
			} else if (pVO.getSearchCondition().equals("SEARCH")) {
				Object[] args = { pVO.getSearchKeyword() };
				return jdbcTemplate.query(SQL_SELECTALL_SEARCH, args, new ProductRowMapper());
			}
		}catch(Exception e) {
			return null;
		}

		return null;
	}


	public ProductVO selectOne(ProductVO pVO) {
		System.out.println("ProductDAO2 로그 selectOne() 메서드");
		try {
			if (pVO.getSearchCondition()==null || pVO.getSearchCondition().equals("SELECTONE")) {
				Object[] args = { pVO.getProductNum() };
				return jdbcTemplate.queryForObject(SQL_SELECTONE, args, new ProductRowMapper());
			} else if (pVO.getSearchCondition().equals("PRODUCTNUM")) {
				return jdbcTemplate.queryForObject(SQL_SELECTONE_PRODUCTNUM, new ProductRowMapper_PRODUCTNUM());
			}
		}catch(Exception e) {
			return null;
		}

		return null;
	}


	
}

class ProductRowMapper implements RowMapper<ProductVO> {
	//P.PRODUCTNUM, I.PATH, P.PRODUCTNAME, P.COMPANY, P.PRODUCTPRICE, P.PRODUCTEXPLAIN, P.CATEGORYNUM, C.CATEGORYNAME, P.CATEGORYDETAILNUM, CD.CATEGORYDETAILNAME
	//PRODUCTNUM,PATH,PRODUCTNAME,COMPANY,PRODUCTPRICE,PRODUCTEXPLAIN,PRODUCT.CATEGORYNUM,CATEGORYNAME,PRODUCT.CATEGORYDETAILNUM,CATEGORYDETAILNAME
	@Override
	public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductVO data = new ProductVO();
		data.setProductNum(rs.getInt("PRODUCTNUM"));
		data.setPath(rs.getString("PATH")); 
		data.setProductName(rs.getString("PRODUCTNAME")); 
		data.setCompany(rs.getString("COMPANY")); 
		data.setProductPrice(rs.getInt("PRODUCTPRICE")); 
		data.setProductCnt(rs.getInt("PRODUCTCNT")); 
		data.setProductExplain(rs.getString("PRODUCTEXPLAIN")); 
		data.setCategoryNum(rs.getInt("CATEGORYNUM")); 
		data.setCategoryName(rs.getString("CATEGORYNAME")); 
		data.setCategoryDetailNum(rs.getInt("CATEGORYDETAILNUM")); 
		data.setCategoryDetailName(rs.getString("CATEGORYDETAILNAME"));
		return data;
	}
}
class ProductRowMapper_PRODUCTLIST implements RowMapper<ProductVO> {
	//P.PRODUCTNUM, P.PRODUCTNAME, P.COMPANY, P.PRODUCTPRICE, P.CATEGORYNUM, C.CATEGORYNAME, P.CATEGORYDETAILNUM, CD.CATEGORYDETAILNAME
	@Override
	public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductVO data = new ProductVO();
		data.setProductNum(rs.getInt("PRODUCTNUM"));
		data.setProductName(rs.getString("PRODUCTNAME")); 
		data.setCompany(rs.getString("COMPANY")); 
		data.setProductPrice(rs.getInt("PRODUCTPRICE")); 
		data.setProductCnt(rs.getInt("PRODUCTCNT")); 
		//		data.setProductExplain(rs.getString("PRODUCTEXPLAIN")); 
		data.setCategoryNum(rs.getInt("CATEGORYNUM")); 
		data.setCategoryName(rs.getString("CATEGORYNAME")); 
		data.setCategoryDetailNum(rs.getInt("CATEGORYDETAILNUM")); 
		data.setCategoryDetailName(rs.getString("CATEGORYDETAILNAME"));
		return data;
	}
}
class ProductRowMapper_PRODUCTNUM implements RowMapper<ProductVO> {
	@Override
	public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductVO data = new ProductVO();
		//		data.setPath(rs.getString("PATH")); 
		data.setProductNum(rs.getInt("PRODUCTNUM"));
		//		data.setProductName(rs.getString("PRODUCTNAME")); 
		//		data.setCompany(rs.getString("COMPANY")); 
		//		data.setProductPrice(rs.getInt("PRODUCTPRICE")); 
		//		data.setProductCnt(rs.getInt("CNT")); 
		//		data.setProductExplain(rs.getString("PRODUCTEXPLAIN")); 
		//		data.setCategoryNum(rs.getInt("CATEGORYNUM")); 
		//		data.setCategoryName(rs.getString("CATEGORYNAME")); 
		//		data.setCategoryDetailNum(rs.getInt("CATEGORYDETAILNUM")); 
		//		data.setCategoryDetailName(rs.getString("CATEGORYDETAILNAME"));
		return data;
	}
}