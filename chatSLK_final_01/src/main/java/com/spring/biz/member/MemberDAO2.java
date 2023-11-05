package com.spring.biz.member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("MemberDAO")
public class MemberDAO2 {



	@Autowired
	private JdbcTemplate jdbcTemplate;
	// 회원가입
	private static final String SQL_INSERT = "INSERT INTO MEMBER (MEMBERID,MEMBERPW,MEMBERNAME,PHONENUMBER,EMAIL,DOMAIN) VALUES(?,?,?,?,?,?)";
	// 회원 정보 변경
	private static final String SQL_UPDATE = "UPDATE MEMBER SET MEMBERPW = ?, MEMBERNAME = ?, PHONENUMBER = ?, EMAIL = ?,DOMAIN = ? WHERE MEMBERID = ?";
	private static final String SQL_UPDATE_NOPW = "UPDATE MEMBER SET MEMBERNAME = ?, PHONENUMBER = ?, EMAIL = ?,DOMAIN = ? WHERE MEMBERID = ?";
	private static final String SQL_UPDATE_MEMBERPW = "UPDATE MEMBER SET MEMBERNAME = ?, MEMBERPW = ?, PHONENUMBER = ?, EMAIL = ?,DOMAIN = ? WHERE MEMBERID = ?";
	private static final String SQL_UPDATE_ROLE = "UPDATE MEMBER SET ROLE = ? WHERE MEMBERID = ?";
	// 회원 탈퇴
	private static final String SQL_DELETE = "DELETE FROM MEMBER WHERE MEMBERID=?";

	private static final String SQL_SELECTALL = "SELECT MEMBERID, MEMBERPW, MEMBERNAME, PHONENUMBER,EMAIL,DOMAIN,ROLE FROM MEMBER";
	// 로그인
	private static final String SQL_SELECTONE = "SELECT MEMBERID, MEMBERPW, MEMBERNAME, PHONENUMBER,EMAIL,DOMAIN,ROLE FROM MEMBER WHERE MEMBERID = ? AND MEMBERPW = ?";
	private static final String SQL_SELECTONE_NOPW = "SELECT MEMBERID, MEMBERPW, MEMBERNAME, PHONENUMBER,EMAIL,DOMAIN,ROLE FROM MEMBER WHERE MEMBERID = ?";
	private static final String SQL_SELECTONE_MEMBERID = "SELECT MEMBERID,EMAIL,DOMAIN FROM MEMBER WHERE MEMBERNAME = ? AND EMAIL = ? AND DOMAIN = ?";
	private static final String SQL_SELECTONE_MEMBERPW = "SELECT MEMBERID,MEMBERNAME,PHONENUMBER FROM MEMBER WHERE MEMBERID = ? AND MEMBERNAME = ? AND PHONENUMBER = ?";


	// 회원가입
	public boolean insert(MemberVO mVO) {
		System.out.println("MemberDAO2 로그 insert() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_INSERT, mVO.getMemberId(), mVO.getMemberPw(), mVO.getMemberName(),mVO.getPhonenumber(), mVO.getEmail(),mVO.getDomain());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	// 회원 정보 변경
	public boolean update(MemberVO mVO) {
		System.out.println("MemberDAO2 로그 update() 메서드");
		try {
			int rs = 0;
			if (mVO.getSearchCondition() == null || mVO.getSearchCondition().equals("UPDATE")) {
				rs = jdbcTemplate.update(SQL_UPDATE, mVO.getMemberPw(), mVO.getMemberName(), mVO.getPhonenumber(),mVO.getEmail(), mVO.getDomain(), mVO.getMemberId());
			} else if (mVO.getSearchCondition().equals("NOPW")){
				rs = jdbcTemplate.update(SQL_UPDATE_NOPW, mVO.getMemberName(), mVO.getPhonenumber(),mVO.getEmail(), mVO.getDomain(), mVO.getMemberId());
			} else if (mVO.getSearchCondition().equals("MEMBERPW")) {
				rs = jdbcTemplate.update(SQL_UPDATE_MEMBERPW, mVO.getMemberName(),mVO.getTmpPw(), mVO.getPhonenumber(),mVO.getEmail(), mVO.getDomain(), mVO.getMemberId());
			} else if (mVO.getSearchCondition().equals("ROLE")) {
				rs = jdbcTemplate.update(SQL_UPDATE_ROLE, mVO.getRole(), mVO.getMemberId());
			}
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	// 회원 탈퇴
	public boolean delete(MemberVO mVO) {
		System.out.println("MemberDAO2 로그 delete() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_DELETE, mVO.getMemberId());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}


	public List<MemberVO> selectAll(MemberVO mVO) {
		System.out.println("MemberDAO2 로그 selectAll() 메서드");
		try {
			return jdbcTemplate.query(SQL_SELECTALL,new MemberRowMapper());
		} catch(Exception e) {
			return null;
		}
	}

	public MemberVO selectOne(MemberVO mVO) {
		System.out.println("MemberDAO2 로그 selectOne() 메서드");
		try {
			if(mVO.getSearchCondition() == null || mVO.getSearchCondition().equals("SELECTONE")) {
				Object[] args= {mVO.getMemberId(),mVO.getMemberPw()};
				return jdbcTemplate.queryForObject(SQL_SELECTONE,args,new MemberRowMapper());
			}
			else if(mVO.getSearchCondition().equals("NOPW")) {
				Object[] args= {mVO.getMemberId()};
				return jdbcTemplate.queryForObject(SQL_SELECTONE_NOPW,args,new MemberRowMapper());
			}
			else if(mVO.getSearchCondition().equals("MEMBERID"))  {
				Object[] args= {mVO.getMemberName(),mVO.getEmail(),mVO.getDomain()};
				return jdbcTemplate.queryForObject(SQL_SELECTONE_MEMBERID,args,new MemberRowMapper_MEMBERID());
			}
			else if(mVO.getSearchCondition().equals("MEMBERPW"))  {
				Object[] args= {mVO.getMemberId(),mVO.getMemberName(),mVO.getPhonenumber()};
				return jdbcTemplate.queryForObject(SQL_SELECTONE_MEMBERPW,args,new MemberRowMapper_MEMBERPW());
			}
		}catch(Exception e) {
			return null;
		}
		return null;
	}

}

class MemberRowMapper implements RowMapper<MemberVO> {
	//MEMBERID, MEMBERPW, MEMBERNAME, PHONENUMBER,EMAIL,DOMAIN,ROLE
	@Override
	public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberVO data = new MemberVO();
		data.setMemberId(rs.getString("MEMBERID"));
		data.setMemberPw(rs.getString("MEMBERPW"));
		data.setMemberName(rs.getString("MEMBERNAME"));
		data.setPhonenumber(rs.getString("PHONENUMBER"));
		data.setEmail(rs.getString("EMAIL"));
		data.setDomain(rs.getString("DOMAIN"));
		data.setRole(rs.getString("ROLE"));
		return data;
	}
}

class MemberRowMapper_MEMBERID implements RowMapper<MemberVO> {
	//MEMBERID, MEMBERPW, MEMBERNAME, PHONENUMBER,EMAIL,DOMAIN,ROLE
	@Override
	public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberVO data = new MemberVO();
		data.setMemberId(rs.getString("MEMBERID"));
		data.setEmail(rs.getString("EMAIL"));
		data.setDomain(rs.getString("DOMAIN"));
		return data;
	}
}

class MemberRowMapper_MEMBERPW implements RowMapper<MemberVO> {
	//MEMBERID, MEMBERPW, MEMBERNAME, PHONENUMBER,EMAIL,DOMAIN,ROLE
	@Override
	public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberVO data = new MemberVO();
		data.setMemberId(rs.getString("MEMBERID"));
		data.setMemberName(rs.getString("MEMBERNAME"));
		data.setPhonenumber(rs.getString("PHONENUMBER"));
		return data;
	}
}