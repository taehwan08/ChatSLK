//package com.spring.biz.pay;
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
//@Repository("PayDAO")
//public class PayDAO {
//
//	// 결제하기
//	private static final String sql_insert="INSERT INTO PAY (PAYMETHOD,MEMBERID) VALUES(?,?)";
//	// 결제 목록 출력
//	private static final String sql_selectAll="SELECT MEMBERID,PRODUCT.PRODUCTNUM,PAY.PAYNUM,PATH,PRODUCTNAME,COMPANY,PRODUCTPRICE,PAYMETHOD,PAYDETAIL.PAYCOUNT FROM PAY INNER JOIN PAYDETAIL ON PAY.PAYNUM=PAYDETAIL.PAYNUM INNER JOIN PRODUCT ON PAYDETAIL.PRODUCTNUM = PRODUCT.PRODUCTNUM INNER JOIN IMAGES ON PRODUCT.PRODUCTNUM = IMAGES.PRODUCTNUM WHERE MEMBERID=? AND PATH LIKE '%(1)%' ORDER BY PAY.PAYNUM DESC";
//
//	private static final String sql_selectOne_first="SELECT PAYNUM,MEMBERID FROM PAY WHERE MEMBERID=? ORDER BY PAYNUM DESC LIMIT 0,1";
//
//	private static final String sql_selectAll_payone="SELECT MEMBERID,PRODUCT.PRODUCTNUM,PAY.PAYNUM,PATH,PRODUCTNAME,COMPANY,PRODUCTPRICE,PAYMETHOD,PAYDETAIL.PAYCOUNT FROM PAY INNER JOIN PAYDETAIL ON PAY.PAYNUM=PAYDETAIL.PAYNUM INNER JOIN PRODUCT ON PAYDETAIL.PRODUCTNUM = PRODUCT.PRODUCTNUM INNER JOIN IMAGES ON PRODUCT.PRODUCTNUM = IMAGES.PRODUCTNUM WHERE MEMBERID=? AND PAY.PAYNUM=? AND PATH LIKE '%(1)%'";
//
//	Connection conn;
//	PreparedStatement pstmt;
//	ResultSet rs;
//
//	// 결제하기
//	public boolean insert(PayVO pVO) {
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				pstmt=conn.prepareStatement(sql_insert);
//				pstmt.setString(1, pVO.getPayMethod());
//				pstmt.setString(2, pVO.getMemberId());
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
//	// 결제 목록 출력
//	public List<PayVO> selectAll(PayVO pVO){
//		List<PayVO> pdatas = new ArrayList<PayVO>();
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				if(pVO.getSearchCondition()==null) {
//					pstmt=conn.prepareStatement(sql_selectAll);
//					pstmt.setString(1, pVO.getMemberId());
//					rs=pstmt.executeQuery();
//
//					while(rs.next()) {
//						// PRODUCT.PRODUCTNUM,PAY.PAYNUM,PATH,PRODUCTNAME,COMPANY,PRODUCTPRICE,PAYMETHOD,PAYDETAIL.PAYCOUNT
//						PayVO data=new PayVO();
//						data.setProductNum(rs.getInt("PRODUCTNUM"));
//						data.setPayNum(rs.getInt("PAYNUM"));
//						data.setPath(rs.getString("PATH"));
//						data.setProductName(rs.getString("PRODUCTNAME"));
//						data.setCompany(rs.getString("COMPANY"));
//						data.setProductPrice(rs.getString("PRODUCTPRICE"));
//						data.setPayMethod(rs.getString("PAYMETHOD"));
//						data.setMemberId(rs.getString("MEMBERID"));
//						data.setPayCount(rs.getInt("PAYCOUNT"));
//						pdatas.add(data);
//					}
//				}else if (pVO.getSearchCondition().equals("payone")) {
//					pstmt=conn.prepareStatement(sql_selectAll_payone);
//					pstmt.setString(1, pVO.getMemberId());
//					pstmt.setInt(2, pVO.getPayNum());
//					rs=pstmt.executeQuery();
//
//					while(rs.next()) {
//						// PRODUCT.PRODUCTNUM,PAY.PAYNUM,PATH,PRODUCTNAME,COMPANY,PRODUCTPRICE,PAYMETHOD,PAYDETAIL.PAYCOUNT
//						PayVO data=new PayVO();
//						data.setProductNum(rs.getInt("PRODUCTNUM"));
//						data.setPayNum(rs.getInt("PAYNUM"));
//						data.setPath(rs.getString("PATH"));
//						data.setProductName(rs.getString("PRODUCTNAME"));
//						data.setCompany(rs.getString("COMPANY"));
//						data.setProductPrice(rs.getString("PRODUCTPRICE"));
//						data.setPayMethod(rs.getString("PAYMETHOD"));
//						data.setMemberId(rs.getString("MEMBERID"));
//						data.setPayCount(rs.getInt("PAYCOUNT"));
//						pdatas.add(data);
//					}
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
//		return pdatas;
//	}
//
//	public PayVO selectOne(PayVO pVO){
//		PayVO pdata=new PayVO();
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				if(pVO.getSearchCondition().equals("first")) {
//					pstmt=conn.prepareStatement(sql_selectOne_first);
//					pstmt.setString(1, pVO.getMemberId());
//					rs=pstmt.executeQuery();
//
//					if(rs.next()) {
//						pdata.setPayNum(rs.getInt("PAYNUM"));
//						pdata.setMemberId(rs.getString("MEMBERID"));
//					}
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
//		return pdata;
//	}
//
//
//
//	// 변경
//	public boolean update(PayVO pVO) {
//		return false;
//	}
//
//	// 삭제
//	public boolean delete(PayVO pVO) {
//		return false;
//	}
//
//
//}
