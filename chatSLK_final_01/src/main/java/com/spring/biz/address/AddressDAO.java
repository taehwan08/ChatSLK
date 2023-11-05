package com.spring.biz.address;
//package com.spring.address;
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
//@Repository("AddressDAO")
//public class AddressDAO {
//
//	private static final String sql_insert="INSERT INTO ADDRESS (ADDRESSNUM,MEMBERID,ZIPCODE,ADDRESS,ADDRESSDETAIL,STATUS) VALUES(CART_SEQ.NEXTVAL,?,?,?,?,?)";
//	private static final String sql_update="UPDATE ADDRESS SET ZIPCODE=?,ADDRESS=?,ADDRESSDETAIL=? WHERE ADDRESSNUM=?";
//	private static final String sql_delete="DELETE FROM ADDRESS WHERE ADDRESSNUM = ? AND MEMBERID = ?";
//	private static final String sql_selectAll="SELECT ADDRESSNUM,MEMBERID,ZIPCODE,ADDRESS,ADDRESSDETAIL,STATUS FROM ADDRESS";
//	private static final String sql_selectOne="SELECT ADDRESSNUM,MEMBERID,ZIPCODE,ADDRESS,ADDRESSDETAIL,STATUS FROM ADDRESS";
//			
//	Connection conn;
//	PreparedStatement pstmt;
//	ResultSet rs;
//
//	// ADDRESSNUM,MEMBERID,ZIPCODE,ADDRESS,ADDRESSDETAIL,STATUS 
//	public boolean insert(AddressVO aVO) {
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				int res = 0;
//				pstmt=conn.prepareStatement(sql_insert);
//				pstmt.setInt(1, aVO.getAddressNum());
//				pstmt.setString(2, aVO.getMemberId());
//				pstmt.setString(3, aVO.getZipcode());
//				pstmt.setString(4, aVO.getAddress());
//				pstmt.setString(5, aVO.getAddressDetail());
//				pstmt.setString(6, aVO.getStatus());
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
//	public boolean update(AddressVO aVO) {
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				pstmt=conn.prepareStatement(sql_update);
//				pstmt.setString(1, aVO.getZipcode());
//				pstmt.setString(2, aVO.getAddress());
//				pstmt.setString(3, aVO.getAddressDetail());
//				pstmt.setInt(4, aVO.getAddressNum());
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
//	public boolean delete(AddressVO aVO) {
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				pstmt=conn.prepareStatement(sql_delete);
//				pstmt.setInt(1, aVO.getAddressNum());
//				pstmt.setString(2, aVO.getMemberId());
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
//	public List<AddressVO> selectAll(AddressVO aVO){
//		List<AddressVO> adatas = new ArrayList<AddressVO>();
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				pstmt=conn.prepareStatement(sql_selectAll);
//				pstmt.setString(1, aVO.getMemberId());
//				rs=pstmt.executeQuery();
//
//				// ADDRESSNUM,MEMBERID,ZIPCODE,ADDRESS,ADDRESSDETAIL,STATUS 
//				while(rs.next()) {
//					AddressVO data=new AddressVO();
//					data.setAddressNum(rs.getInt("ADDRESSNUM"));
//					data.setMemberId(rs.getString("MEMBERID"));
//					data.setZipcode(rs.getString("ZIPCODE"));
//					data.setAddress(rs.getString("ADDRESS"));
//					data.setAddressDetail(rs.getString("ADDRESSDETAIL"));
//					adatas.add(data);
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
//		return adatas;
//	}
//
//	public AddressVO selectOne(AddressVO aVO){
//		AddressVO adata = null;
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				pstmt=conn.prepareStatement(sql_selectOne);
//				pstmt.setInt(1, aVO.getAddressNum());
//				pstmt.setString(2, aVO.getMemberId());
//				pstmt.setString(3, aVO.getZipcode());
//				pstmt.setString(4, aVO.getAddress());
//				pstmt.setString(5, aVO.getAddressDetail());
//				pstmt.setString(6, aVO.getStatus());
//				rs=pstmt.executeQuery();
//				if(rs.next()) {
//					adata=new AddressVO();
//					adata.setAddressNum(rs.getInt("ADDRESSNUM"));
//					adata.setMemberId(rs.getString("MEMBERID"));
//					adata.setZipcode(rs.getString("ZIPCODE"));
//					adata.setAddress(rs.getString("ADDRESS"));
//					adata.setAddressDetail(rs.getString("ADDRESSDETAIL"));
//				}
//			}
//			catch (SQLIntegrityConstraintViolationException e) {
//				return adata;
//			}
//			catch (SQLException e){
//				e.printStackTrace();
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				return adata;
//			}
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		finally {
//			JDBCUtil.disconnect(rs, pstmt, conn);
//		}
//		return adata;
//	}
//}
