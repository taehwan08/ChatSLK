//package com.spring.biz.cart;
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
//@Repository("CartDAO")
//public class CartDAO {
//
//	// 장바구니 추가
//	private static final String sql_insert="INSERT INTO CART (CARTNUM,PRODUCTNUM,MEMBERID,CARTCOUNT) VALUES(CART_SEQ.NEXTVAL,?,?,?)";
//	// 갯수 변경
//	private static final String sql_update="UPDATE CART SET CARTCOUNT = CARTCOUNT+? WHERE MEMBERID = ? AND PRODUCTNUM = ?";
//	// 장바구니 삭제
//	private static final String sql_delete="DELETE FROM CART WHERE MEMBERID = ? AND PRODUCTNUM = ?";
//	// 장바구니 전체 출력
//	private static final String sql_selectAll="SELECT CARTNUM,PATH,MEMBERID,PRODUCT.PRODUCTNUM,PRODUCTNAME,COMPANY,PRODUCTPRICE,CARTCOUNT FROM CART INNER JOIN PRODUCT ON CART.PRODUCTNUM = PRODUCT.PRODUCTNUM INNER JOIN IMAGES ON PRODUCT.PRODUCTNUM = IMAGES.PRODUCTNUM WHERE MEMBERID=? and path like '%(1)%' ORDER BY CARTNUM DESC";
//
//	private static final String sql_selectOne="SELECT CARTNUM,PATH,MEMBERID,PRODUCT.PRODUCTNUM,PRODUCTNAME,COMPANY,PRODUCTPRICE,CARTCOUNT FROM CART INNER JOIN PRODUCT ON CART.PRODUCTNUM = PRODUCT.PRODUCTNUM INNER JOIN IMAGES ON PRODUCT.PRODUCTNUM = IMAGES.PRODUCTNUM WHERE MEMBERID=? AND CART.PRODUCTNUM=? and path like '%(1)%'";
//
//	Connection conn;
//	PreparedStatement pstmt;
//	ResultSet rs;
//
//
//	// 장바구니 추가
//	public boolean insert(CartVO cVO) {
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				int res = 0;
//				pstmt=conn.prepareStatement(sql_insert);
//				pstmt.setInt(1, cVO.getCartNum());
//				pstmt.setInt(1, cVO.getProductNum());
//				pstmt.setString(2, cVO.getMemberId());
//				pstmt.setInt(3, cVO.getCartCount());
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
//	// 갯수 추가
//	public boolean update(CartVO cVO) {
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				pstmt=conn.prepareStatement(sql_update);
//				pstmt.setInt(1, cVO.getTmpcnt());
//				pstmt.setString(2, cVO.getMemberId());
//				pstmt.setInt(3, cVO.getProductNum());
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
//	// 장바구니 삭제
//	public boolean delete(CartVO cVO) {
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				pstmt=conn.prepareStatement(sql_delete);
//				pstmt.setString(1, cVO.getMemberId());
//				pstmt.setInt(2, cVO.getProductNum());
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
//	// 장바구니 전체 출력
//	public List<CartVO> selectAll(CartVO cVO){
//		List<CartVO> cdatas = new ArrayList<CartVO>();
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				pstmt=conn.prepareStatement(sql_selectAll);
//				pstmt.setString(1, cVO.getMemberId());
//				rs=pstmt.executeQuery();
//
//				// PATH,MEMBERID,PRODUCT.PRODUCTNUM,PRODUCTNAME,COMPANY,PRODUCTPRICE,CARTCOUNT
//				while(rs.next()) {
//					CartVO data=new CartVO();
//					data.setPath(rs.getString("PATH"));
//					data.setMemberId(rs.getString("MEMBERID"));
//					data.setProductNum(rs.getInt("PRODUCTNUM"));
//					data.setProductName(rs.getString("PRODUCTNAME"));
//					data.setCompany(rs.getString("COMPANY"));
//					data.setCartNum(rs.getInt("CARTNUM"));
//					data.setProductPrice(rs.getInt("PRODUCTPRICE"));
//					data.setCartCount(rs.getInt("CARTCOUNT"));
//					cdatas.add(data);
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
//		return cdatas;
//	}
//
//	public CartVO selectOne(CartVO cVO){
//		CartVO cdata = null;
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				pstmt=conn.prepareStatement(sql_selectOne);
//				pstmt.setString(1, cVO.getMemberId());
//				pstmt.setInt(2, cVO.getProductNum());
//				rs=pstmt.executeQuery();
//				if(rs.next()) {
//					cdata=new CartVO();
//					cdata.setPath(rs.getString("PATH"));
//					cdata.setMemberId(rs.getString("MEMBERID"));
//					cdata.setProductNum(rs.getInt("PRODUCTNUM"));
//					cdata.setProductName(rs.getString("PRODUCTNAME"));
//					cdata.setCompany(rs.getString("COMPANY"));
//					cdata.setCartNum(rs.getInt("CARTNUM"));
//					cdata.setProductPrice(rs.getInt("PRODUCTPRICE"));
//					cdata.setCartCount(rs.getInt("CARTCOUNT"));
//				}
//			}
//			catch (SQLIntegrityConstraintViolationException e) {
//				return cdata;
//			}
//			catch (SQLException e){
//				e.printStackTrace();
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				return cdata;
//			}
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		finally {
//			JDBCUtil.disconnect(rs, pstmt, conn);
//		}
//		return cdata;
//	}
//}
