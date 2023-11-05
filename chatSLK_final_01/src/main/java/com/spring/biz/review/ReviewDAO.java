//package com.spring.biz.review;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.SQLIntegrityConstraintViolationException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.stereotype.Repository;
//
//import com.spring.biz.common.JDBCUtil;
//@Repository("ReviewDAO")
//public class ReviewDAO {
//
//	private static final String sql_insert="INSERT INTO REVIEW (REVIEWNUM,MEMBERID,TITLE,CONTENT,REPLY,STAR,PRODUCTNUM,REVIEWTIME) VALUES(CART_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
//	private static final String sql_update="UPDATE REVIEW SET TITLE =?, CONTENT=? WHERE REVIEWNUM=?";
//	private static final String sql_delete="DELETE FROM REVIEW WHERE REVIEWNUM = ? AND MEMBERID = ?";
//	private static final String sql_selectAll="SELECT REVIEWNUM,MEMBERID,TITLE,CONTENT,REPLY,STAR,PRODUCTNUM,REVIEWTIME FROM REVIEW"; 
//	private static final String sql_selectOne="SELECT REVIEWNUM,MEMBERID,TITLE,CONTENT,REPLY,STAR,PRODUCTNUM,REVIEWTIME FROM REVIEW";
//	Connection conn;
//	PreparedStatement pstmt;
//	ResultSet rs;
//
//	public boolean insert(ReviewVO rVO) {
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				int res = 0;
//				pstmt=conn.prepareStatement(sql_insert);
//				pstmt.setInt(1, rVO.getReviewNum());
//				pstmt.setString(2, rVO.getMemberId());
//				pstmt.setString(3, rVO.getTitle());
//				pstmt.setString(4, rVO.getContent());
//				pstmt.setString(5, rVO.getReply());
//				pstmt.setInt(6, rVO.getStar());
//				pstmt.setInt(7, rVO.getProductNum());
//				res = pstmt.executeUpdate();
//				if(res <= 0) {
//					return false;
//				}
//			}
//			catch (SQLIntegrityConstraintViolationException e) {
//				return false;
//			}
//			catch (SQLException e){
//				e.printStackTrace();
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				return false;
//			}
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		finally {
//			JDBCUtil.disconnect(pstmt, conn);
//		}
//		return true;
//	}
//
//	
//	public boolean update(ReviewVO rVO) {
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				pstmt=conn.prepareStatement(sql_update);
//				pstmt.setString(1, rVO.getTitle());
//				pstmt.setString(1, rVO.getContent());
//				pstmt.setInt(3, rVO.getReviewNum());
//				int res=pstmt.executeUpdate();
//				if(res<=0) {
//					return false;
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				return false;
//			}
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		finally {
//			JDBCUtil.disconnect(pstmt, conn);
//		}
//		return true;
//	}
//	
//	
//	public boolean delete(ReviewVO rVO) {
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				pstmt=conn.prepareStatement(sql_delete);
//				pstmt.setString(1, rVO.getMemberId());
//				pstmt.setInt(2, rVO.getProductNum());
//				int res=pstmt.executeUpdate();
//				if(res<=0) {
//					return false;
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				return false;
//			}
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		finally {
//			JDBCUtil.disconnect(pstmt, conn);
//		}
//		return true;
//	}
//
//	public List<ReviewVO> selectAll(ReviewVO rVO){
//		List<ReviewVO> rdatas = new ArrayList<ReviewVO>();
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				pstmt=conn.prepareStatement(sql_selectAll);
//				pstmt.setString(1, rVO.getMemberId());
//				rs=pstmt.executeQuery();
//				
//				//REVIEWNUM,MEMBERID,TITLE,CONTENT,REPLY,STAR,PRODUCTNUM,REVIEWTIME
//				while(rs.next()) {
//					ReviewVO data=new ReviewVO();
//					data.setReviewNum(rs.getInt("REVIEWNUM"));
//					data.setMemberId(rs.getString("MEMBERID"));
//					data.setTitle(rs.getString("TITLE"));
//					data.setContent(rs.getString("CONTENT"));
//					data.setReply(rs.getString("REPLY"));
//					data.setStar(rs.getInt("STAR"));
//					data.setProductNum(rs.getInt("PRODUCTNUM"));
//					rdatas.add(data);
//
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//					return null;
//				}
//				return null;
//			}
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		finally {
//			JDBCUtil.disconnect(pstmt, conn);
//		}
//		return rdatas;
//	}
//
//	public ReviewVO selectOne(ReviewVO rVO){
//		ReviewVO rdata = null;
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				pstmt=conn.prepareStatement(sql_selectOne);
//				pstmt.setString(1, rVO.getMemberId());
//				pstmt.setInt(2, rVO.getProductNum());
//				rs=pstmt.executeQuery();
//				if(rs.next()) {
//					ReviewVO data=new ReviewVO();
//					data.setReviewNum(rs.getInt("REVIEWNUM"));
//					data.setMemberId(rs.getString("MEMBERID"));
//					data.setTitle(rs.getString("TITLE"));
//					data.setContent(rs.getString("CONTENT"));
//					data.setReply(rs.getString("REPLY"));
//					data.setStar(rs.getInt("STAR"));
//					data.setProductNum(rs.getInt("PRODUCTNUM"));
//				}
//			}
//			catch (SQLIntegrityConstraintViolationException e) {
//				return rdata;
//			}
//			catch (SQLException e){
//				e.printStackTrace();
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				return rdata;
//			}
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		finally {
//			JDBCUtil.disconnect(rs, pstmt, conn);
//		}
//		return rdata;
//	}
//}
