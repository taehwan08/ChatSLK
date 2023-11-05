//package com.spring.biz.categorydetail;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.stereotype.Repository;
//
//import com.spring.biz.common.JDBCUtil;
//@Repository("CategorydetailDAO")
//public class CategorydetailDAO {
//	private Connection conn;
//	private PreparedStatement pstmt;
//	private ResultSet rs;
//
//	static final private String SQL_SELECTALL="SELECT CATEGORYDETAILNUM,CATEGORYDETAIL.CATEGORYNUM,CATEGORYDETAILNAME FROM CATEGORYDETAIL INNER JOIN CATEGORY ON CATEGORYDETAIL.CATEGORYNUM = CATEGORY.CATEGORYNUM";
//	static final private String SQL_SELECTALL_RECCATEGORY="SELECT CATEGORYDETAILNUM,CATEGORYDETAIL.CATEGORYNUM,CATEGORYDETAILNAME FROM CATEGORYDETAIL INNER JOIN CATEGORY ON CATEGORYDETAIL.CATEGORYNUM = CATEGORY.CATEGORYNUM LIMIT 0,?";
//	static final private String SQL_SELECTONE="SELECT CATEGORYDETAILNUM,CATEGORYDETAILNAME FROM CATEGORYDETAIL WHERE CATEGORYDETAILNUM=?";
//
//	public List<CategorydetailVO> selectAll(CategorydetailVO cdVO){
//		conn=JDBCUtil.connect();
//
//		
//		List<CategorydetailVO> cdatas= new ArrayList<CategorydetailVO>();
//		try {
//			if(cdVO.getSerchCondition()==null){
//				pstmt=conn.prepareStatement(SQL_SELECTALL);
//				rs=pstmt.executeQuery();
//			}
//			else if(cdVO.getSerchCondition().equals("추천카테고리")) {
//			pstmt=conn.prepareStatement(SQL_SELECTALL_RECCATEGORY);
//			pstmt.setInt(1, cdVO.getListcnt());
//			rs=pstmt.executeQuery();
//			}
//
//			while(rs.next()) {
//				CategorydetailVO cdata=new CategorydetailVO();
//				cdata.setCategoryDetailNum(rs.getInt("CATEGORYDETAILNUM"));
//				cdata.setCategoryNum(rs.getInt("CATEGORYNUM"));
//				cdata.setCategoryDetailName(rs.getString("CATEGORYDETAILNAME"));
//				cdatas.add(cdata);
//			}
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		finally {      
//			JDBCUtil.disconnect(rs,pstmt,conn);
//		}
//		return cdatas;
//	}
//
//
//	public CategorydetailVO selectOne(CategorydetailVO cdVO) {
//		conn=JDBCUtil.connect();
//
//		CategorydetailVO cdata=null;
//
//		try {
//			pstmt=conn.prepareStatement(SQL_SELECTONE);
//			pstmt.setInt(1, cdVO.getCategoryDetailNum());
//			rs=pstmt.executeQuery();
//			if(rs.next()) {
//				cdata=new CategorydetailVO();
//				cdata.setCategoryDetailNum(rs.getInt("CATEGORYDETAILNUM"));
//				cdata.setCategoryDetailName(rs.getString("CATEGORYDETAILNAME"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			JDBCUtil.disconnect(rs, pstmt, conn);
//		}
//		return cdata;
//	}
//	
//	public boolean insert(CategorydetailVO cdVO) {
//		return false;
//	}
//	public boolean update(CategorydetailVO cdVO) {
//		return false;
//	}
//	public boolean delete(CategorydetailVO cdVO) {
//		return false;
//	}
//
//}
