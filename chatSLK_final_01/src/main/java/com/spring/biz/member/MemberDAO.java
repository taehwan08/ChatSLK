//package com.spring.biz.member;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.SQLIntegrityConstraintViolationException;
//import java.util.List;
//
//import org.springframework.stereotype.Repository;
//
//import com.spring.biz.common.JDBCUtil;
//
//@Repository("MemberDAO")
//public class MemberDAO {
//	// 회원가입
//	private static final String sql_insert="INSERT INTO MEMBER (MEMBERID,MEMBERPW,MEMBERNAME,PHONENUMBER,EMAIL) VALUES(?,?,?,?,?)";
//	private static final String sql_insert2="INSERT INTO MEMBER (MEMBERID,MEMBERPW,MEMBERNAME,PHONENUMBER,EMAIL,DOMAIN,ROLE) VALUES(?,?,?,?,?,?,?)";
//	// 비밀번호 변경
//	private static final String sql_update="UPDATE MEMBER SET MEMBERPW = ?, MEMBERNAME = ?, PHONENUMBER = ?, EMAIL = ? WHERE MEMBERID = ?";
//	private static final String sql_update2="UPDATE MEMBER SET MEMBERPW = ?, MEMBERNAME = ?, PHONENUMBER = ?, EMAIL = ?,DOMAIN = ? WHERE MEMBERID = ?";
//	// 회원 탈퇴
//	private static final String sql_delete="DELETE FROM MEMBER WHERE MEMBERID=?";
//	// 로그인
//	private static final String sql_selectOne="SELECT MEMBERID, MEMBERPW, MEMBERNAME, PHONENUMBER,EMAIL,DOMAIN,ROLE FROM MEMBER WHERE MEMBERID = ? AND MEMBERPW = ?";
//	private static final String sql_selectOne2="SELECT MEMBERID, MEMBERPW, MEMBERNAME, PHONENUMBER,EMAIL,DOMAIN,ROLE FROM MEMBER WHERE MEMBERID = ?";
//
//	Connection conn;
//	PreparedStatement pstmt;
//	ResultSet rs;
//
//	// 회원가입
//	public boolean insert(MemberVO mVO) {
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				int res=0;
//				if(mVO.getDomain()==null) {
//					pstmt=conn.prepareStatement(sql_insert);
//					pstmt.setString(1, mVO.getMemberId());
//					pstmt.setString(2, mVO.getMemberPw());
//					pstmt.setString(3, mVO.getMemberName());
//					pstmt.setString(4, mVO.getPhonenumber());
//					pstmt.setString(5, mVO.getEmail());
//					res = pstmt.executeUpdate();
//				}
//				else {
//					pstmt=conn.prepareStatement(sql_insert2);
//					pstmt.setString(1, mVO.getMemberId());
//					pstmt.setString(2, mVO.getMemberPw());
//					pstmt.setString(3, mVO.getMemberName());
//					pstmt.setString(4, mVO.getPhonenumber());
//					pstmt.setString(5, mVO.getEmail());
//					res = pstmt.executeUpdate();
//				}
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
//	// 회원 정보 변경
//	public boolean update(MemberVO mVO) {
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				int res=0;
//				if(mVO.getSearchCondition()==null) {
//					pstmt=conn.prepareStatement(sql_update2);	
//					pstmt.setString(1, mVO.getTmpPw());
//					pstmt.setString(2, mVO.getMemberName());
//					pstmt.setString(3, mVO.getPhonenumber());
//					pstmt.setString(4, mVO.getEmail());
//					pstmt.setString(5, mVO.getDomain());
//					pstmt.setString(6, mVO.getMemberId());
//					res=pstmt.executeUpdate();
//				}
//				else if(mVO.getSearchCondition().equals("정보변경")) {
//					pstmt=conn.prepareStatement(sql_update);
//					System.out.println("MemberDAO : update(mVO) = "+mVO);
//					pstmt.setString(1, mVO.getTmpPw());
//					pstmt.setString(2, mVO.getMemberName());
//					pstmt.setString(3, mVO.getPhonenumber());
//					pstmt.setString(4, mVO.getEmail());
//					pstmt.setString(5, mVO.getMemberId());
//					res=pstmt.executeUpdate();
//				}
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
//	// 회원 탈퇴
//	public boolean delete(MemberVO mVO) {
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				pstmt=conn.prepareStatement(sql_delete);
//				pstmt.setString(1, mVO.getMemberId());
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
//	// 로그인
//	public MemberVO selectOne(MemberVO mVO){
//		MemberVO mdata= null;
//		try {
//			conn=JDBCUtil.connect();
//			try {
//				if(mVO.getSearchCondition()==null) {
//				pstmt=conn.prepareStatement(sql_selectOne);
//				pstmt.setString(1, mVO.getMemberId());
//				pstmt.setString(2, mVO.getMemberPw());
//				rs=pstmt.executeQuery();
//				}
//				else if (mVO.getSearchCondition().equals("정보변경") || mVO.getSearchCondition().equals("구매")) {
//					pstmt=conn.prepareStatement(sql_selectOne2);
//					pstmt.setString(1, mVO.getMemberId());
//					rs=pstmt.executeQuery();	
//				}
//				//PHONENUMBER, EMAIL, ZIPCODE, ADDRESS, ADDRESSDETAIL
//				if(rs.next()) {
//					mdata=new MemberVO();
//					mdata.setMemberId(rs.getString("MEMBERID"));
//					mdata.setMemberPw(rs.getString("MEMBERPW"));
//					mdata.setMemberName(rs.getString("MEMBERNAME"));
//					mdata.setPhonenumber(rs.getString("PHONENUMBER"));
//					mdata.setEmail(rs.getString("EMAIL"));
//					mdata.setDomain(rs.getString("DOMAIN"));
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
//		return mdata;
//	}
//	
//	
//	
//	private List<MemberVO> selectAll(MemberVO mVO){
//		return null;
//	}
//}
