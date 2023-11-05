package com.spring.biz.paydetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("PatDetailDAO")
public class PayDetailDAO2 {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	//	private static final String SQL_INSERT = "INSERT INTO PAYDETAIL (PAYDETAILNUM,PAYNUM,PRODUCTNUM,PAYCOUNT) VALUES(PAY_SEQ.NEXTVAL,?,?,?)";	
	private static final String SQL_INSERT = "INSERT INTO PAYDETAIL (PAYDETAILNUM,PAYNUM,PRODUCTNUM,PAYCOUNT) VALUES((SELECT COALESCE(MAX(PAYDETAILNUM), 0) + 1 FROM PAYDETAIL),?,?,?)";	
	private static final String SQL_UPDATE="UPDATE PAYDETAIL SET= ";
	private static final String SQL_DELETE="DELETE FROM PAYDETAIL WHERE PAYNUM=?";	
	private static final String SQL_SELECTALL="SELECT PAYNUM,PRODUCTNUM,PAYCOUNT FROM PAYDETAIL";
	private static final String SQL_SELECTONE="SELECT PAYNUM,PRODUCTNUM,PAYCOUNT FROM PAYDETAIL WHERE PAYNUM=?";
	private static final String SQL_SELECTONE_ADMIN_MAIN="SELECT SUM(P.PRODUCTPRICE * PD.PAYCOUNT) PRODUCTPRICE FROM PAYDETAIL PD INNER JOIN PRODUCT P ON PD.PRODUCTNUM = P.PRODUCTNUM";


	public boolean insert(PayDetailVO pdVO) {
		System.out.println("PayDetailDAO2 로그 insert() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_INSERT,pdVO.getPayNum(),pdVO.getProductNum(),pdVO.getPayCount());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;

	}
	public boolean update(PayDetailVO pdVO) {
		System.out.println("PayDetailDAO2 로그 update() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_UPDATE);
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;

	}
	public boolean delete(PayDetailVO pdVO) {
		System.out.println("PayDetailDAO2 로그 delete() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_DELETE,pdVO.getPayNum());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;

	}
	public List<PayDetailVO> selectAll(PayDetailVO pdVO) {
		try {
			return jdbcTemplate.query(SQL_SELECTALL,new PayDetailRowMapper());
		}catch (Exception e) {
			return null;
		}
	}

	public PayDetailVO selectOne(PayDetailVO pdVO) {
		try {
			if(pdVO.getSearchCondition() == null || pdVO.getSearchCondition().equals("SELECTONE")) {
				Object[] args = { pdVO.getPayNum() };
				return jdbcTemplate.queryForObject(SQL_SELECTONE, args, new PayDetailRowMapper());
			}else if (pdVO.getSearchCondition().equals("ADMIN_MAIN")) {
				return jdbcTemplate.queryForObject(SQL_SELECTONE_ADMIN_MAIN, new PayDetailRowMapper_ADMIN());
			}
		}catch(Exception e) {
			return null;
		}
		return null;
	}

}


class PayDetailRowMapper implements RowMapper<PayDetailVO> {

	@Override
	public PayDetailVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		PayDetailVO data = new PayDetailVO();
		data.setPayNum(rs.getInt("PAYNUM"));
		data.setProductNum(rs.getInt("PRODUCTNUM"));
		data.setPayCount(rs.getInt("PAYCOUNT"));
		return data;
	}
}
class PayDetailRowMapper_ADMIN implements RowMapper<PayDetailVO> {

	@Override
	public PayDetailVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		PayDetailVO data = new PayDetailVO();
		data.setPayTotal(rs.getInt("PRODUCTPRICE"));
		return data;
	}
}