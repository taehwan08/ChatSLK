package com.spring.biz.address;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
@Repository("AddressDAO")
public class AddressDAO2 {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	//	private static final String SQL_INSERT="INSERT INTO ADDRESS (ADDRESSNUM,MEMBERID,ZIPCODE,ADDRESS,ADDRESSDETAIL,STATUS) VALUES(ADDRESS_SEQ.NEXTVAL,?,?,?,?,?)";


	private static final String SQL_INSERT="INSERT INTO ADDRESS (ADDRESSNUM,MEMBERID,ZIPCODE,ADDRESS,ADDRESSDETAIL,STATUS) VALUES((SELECT COALESCE(MAX(ADDRESSNUM), 0) + 1 FROM ADDRESS),?,?,?,?,?)";
	private static final String SQL_UPDATE="UPDATE ADDRESS SET ZIPCODE=?,ADDRESS=?,ADDRESSDETAIL=? WHERE ADDRESSNUM=?";
	private static final String SQL_UPDATE_NULL="UPDATE ADDRESS SET STATUS = NULL WHERE ADDRESSNUM=?";
	private static final String SQL_UPDATE_MAIN="UPDATE ADDRESS SET STATUS = 'MAIN' WHERE ADDRESSNUM=?";
	private static final String SQL_DELETE="DELETE FROM ADDRESS WHERE ADDRESSNUM = ? AND MEMBERID = ?";
	private static final String SQL_SELECTALL="SELECT ADDRESSNUM,MEMBERID,ZIPCODE,ADDRESS,ADDRESSDETAIL,STATUS FROM ADDRESS WHERE MEMBERID=?";
	private static final String SQL_SELECTONE="SELECT ADDRESSNUM,MEMBERID,ZIPCODE,ADDRESS,ADDRESSDETAIL,STATUS FROM ADDRESS WHERE MEMBERID=? AND STATUS='MAIN'";
	private static final String SQL_SELECTONE_ADDRESSNUM="SELECT ADDRESSNUM,MEMBERID,ZIPCODE,ADDRESS,ADDRESSDETAIL,STATUS FROM ADDRESS WHERE MEMBERID = ? AND ADDRESSNUM = ?";

	public boolean insert(AddressVO aVO) {
		System.out.println("AddressDAO2 로그 insert() 메서드");
		try {
			int rs = 0;
			rs = jdbcTemplate.update(SQL_INSERT,aVO.getMemberId(),aVO.getZipcode(),aVO.getAddress(),aVO.getAddressDetail(),aVO.getStatus());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	public boolean update(AddressVO aVO) {
		System.out.println("AddressDAO2 로그 update() 메서드");
		try {
			int rs = 0;
			if(aVO.getSearchCondition() == null || aVO.getSearchCondition().equals("UPDATE")) {
				rs = jdbcTemplate.update(SQL_UPDATE,aVO.getZipcode(),aVO.getAddress(),aVO.getAddressDetail(),aVO.getAddressNum());
				if (rs <= 0) {
					return false;
				}
			}
			else if (aVO.getSearchCondition().equals("NULL")) {
				rs = jdbcTemplate.update(SQL_UPDATE_NULL,aVO.getAddressNum());
				if (rs <= 0) {
					return false;
				}
			}
			else if (aVO.getSearchCondition().equals("MAIN")) {
				rs = jdbcTemplate.update(SQL_UPDATE_MAIN,aVO.getAddressNum());
				if (rs <= 0) {
					return false;
				}
			}
		}catch(Exception e) {
			return false;
		}
		return true;

	}

	public boolean delete(AddressVO aVO) {
		System.out.println("AddressDAO2 로그 delete() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_DELETE, aVO.getAddressNum(),aVO.getMemberId());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;

	}

	public List<AddressVO> selectAll(AddressVO aVO) {
		try {
			Object[] args = { aVO.getMemberId() };
			return jdbcTemplate.query(SQL_SELECTALL, args ,new AddressRowMapper());
		}catch(Exception e) {
			return null;
		}
	}

	public AddressVO selectOne(AddressVO aVO) {
		try {
			if(aVO.getSearchCondition() == null || aVO.getSearchCondition().equals("SELECTONE")) {
				Object[] args = { aVO.getMemberId() };
				return jdbcTemplate.queryForObject(SQL_SELECTONE, args, new AddressRowMapper());
			}
			else if (aVO.getSearchCondition().equals("ADDRESSNUM")) {
				Object[] args = { aVO.getMemberId(),aVO.getAddressNum() };
				return jdbcTemplate.queryForObject(SQL_SELECTONE_ADDRESSNUM, args, new AddressRowMapper());
			}
		}catch(Exception e) {
			return null;
		}
		return null;
	}
}


class AddressRowMapper implements RowMapper<AddressVO> {
	//ADDRESSNUM,MEMBERID,ZIPCODE,ADDRESS,ADDRESSDETAIL,STATUS
	@Override
	public AddressVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		AddressVO data=new AddressVO();
		data.setAddressNum(rs.getInt("ADDRESSNUM"));
		data.setMemberId(rs.getString("MEMBERID"));
		data.setZipcode(rs.getString("ZIPCODE"));
		data.setAddress(rs.getString("ADDRESS"));
		data.setAddressDetail(rs.getString("ADDRESSDETAIL"));
		data.setStatus(rs.getString("STATUS"));
		return data;
	}
}

