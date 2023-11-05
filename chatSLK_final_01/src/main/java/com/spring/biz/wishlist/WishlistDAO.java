//package com.spring.biz.wishlist;
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
//
//@Repository("WishlistDAO")
//public class WishlistDAO {
//	// 찜 추가
//	//	private static final String sql_insert="INSERT INTO WISHLIST (PRODUCTNUM,MEMBERID) VALUES(?,?)";
//	private static final String sql_insert="INSERT IGNORE INTO WISHLIST (PRODUCTNUM,MEMBERID) VALUES(?,?)";
//	// 찜 삭제
//	private static final String sql_delete="DELETE FROM WISHLIST WHERE PRODUCTNUM=? AND MEMBERID=?";
//	// 찜 목록 전체 출력
//	private static final String sql_selectAll="SELECT WISHLISTNUM,MEMBERID,WISHLIST.PRODUCTNUM,PATH,PRODUCTNAME,COMPANY,PRODUCTPRICE FROM WISHLIST INNER JOIN PRODUCT ON WISHLIST.PRODUCTNUM = PRODUCT.PRODUCTNUM INNER JOIN IMAGES ON PRODUCT.PRODUCTNUM = IMAGES.PRODUCTNUM WHERE WISHLIST.MEMBERID=? and path like '%(1)%' ORDER BY WISHLISTNUM DESC";
//	private static final String sql_selectAll_NOPIC="SELECT WISHLISTNUM,MEMBERID,WISHLIST.PRODUCTNUM,PRODUCTNAME,COMPANY,PRODUCTPRICE FROM WISHLIST INNER JOIN PRODUCT ON WISHLIST.PRODUCTNUM = PRODUCT.PRODUCTNUM WHERE WISHLIST.MEMBERID=? ORDER BY WISHLIST.PRODUCTNUM";
//
//	Connection conn;
//	PreparedStatement pstmt;
//	ResultSet rs;
//
//	public boolean insert(WishlistVO wVO) {
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				pstmt=conn.prepareStatement(sql_insert);
//				pstmt.setInt(1, wVO.getProductNum());
//				pstmt.setString(2, wVO.getMemberId());
//				int res = pstmt.executeUpdate();
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
//	// 장바구니 삭제
//	public boolean delete(WishlistVO wVO) {
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				pstmt=conn.prepareStatement(sql_delete);
//				pstmt.setInt(1, wVO.getProductNum());
//				pstmt.setString(2, wVO.getMemberId());
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
//	// 위시리스트 전체 출력
//	public List<WishlistVO> selectAll(WishlistVO wVO){
//		List<WishlistVO> wdatas = new ArrayList<WishlistVO>();
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				if(wVO.getSearchCondition()==null) {
//					pstmt=conn.prepareStatement(sql_selectAll);
//					pstmt.setString(1, wVO.getMemberId());	
//					rs=pstmt.executeQuery();
//					//WISHLISTNUM,MEMBERID,WISHLIST.PRODUCTNUM,PATH,PRODUCTNAME,COMPANY,PRODUCTPRICE
//					while(rs.next()) {
//						WishlistVO data=new WishlistVO();
//						data.setWishlistNum(rs.getInt("WISHLISTNUM"));
//						data.setMemberId(rs.getString("MEMBERID"));
//						data.setProductNum(rs.getInt("PRODUCTNUM"));
//						data.setPath(rs.getString("PATH"));
//						data.setProductName(rs.getString("PRODUCTNAME"));
//						data.setCompany(rs.getString("COMPANY"));
//						data.setProductPrice(rs.getInt("PRODUCTPRICE"));
//						wdatas.add(data);
//					}
//				}
//				else if(wVO.getSearchCondition().equals("위시리스트체크")) {
//					pstmt=conn.prepareStatement(sql_selectAll_NOPIC);
//					pstmt.setString(1, wVO.getMemberId());	
//					rs=pstmt.executeQuery();
//					//WISHLISTNUM,MEMBERID,WISHLIST.PRODUCTNUM,PRODUCTNAME,COMPANY,PRODUCTPRICE
//					while(rs.next()) {
//						WishlistVO data=new WishlistVO();
//						data.setWishlistNum(rs.getInt("WISHLISTNUM"));
//						data.setMemberId(rs.getString("MEMBERID"));
//						data.setProductNum(rs.getInt("PRODUCTNUM"));
//						data.setProductName(rs.getString("PRODUCTNAME"));
//						data.setCompany(rs.getString("COMPANY"));
//						data.setProductPrice(rs.getInt("PRODUCTPRICE"));
//						wdatas.add(data);
//					}
//				}
//
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
//		return wdatas;
//	}
//
//	public WishlistVO selectOne(WishlistVO wVO){
//		return null;
//	}
//	public boolean update(WishlistVO wVO){
//		return false;
//	}
//}
