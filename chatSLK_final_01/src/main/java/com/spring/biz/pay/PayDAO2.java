package com.spring.biz.pay;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
@Repository("PayDAO")
public class PayDAO2 {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SQL_INSERT="INSERT INTO PAY (PAYNUM,PAYMETHOD,MEMBERID,ZIPCODE,ADDRESS,ADDRESSDETAIL) VALUES((SELECT COALESCE(MAX(PAYNUM), 0) + 1 FROM PAY),?,?,?,?,?)";
	private static final String SQL_UPDATE="UPDATE PAY SET STATUS = 'ING' WHERE PAYNUM = ?";
	private static final String SQL_DELETE="DELETE FROM PAY WHERE PAYNUM=? AND MEMBERID=?";
	// 결제 목록 출력
		private static final String SQL_SELECTALL=
				"SELECT "
						+ "MEMBERID,P.PRODUCTNUM,PAY.PAYNUM,PATH,PRODUCTNAME,COMPANY,PRODUCTPRICE,PAYMETHOD,PD.PAYCOUNT,PAY.STATUS "
						+ "FROM PAY "
						+ "INNER JOIN PAYDETAIL PD "
						+ "ON PAY.PAYNUM = PD.PAYNUM "
						+ "INNER JOIN PRODUCT P "
						+ "ON PD.PRODUCTNUM = P.PRODUCTNUM "
						+ "INNER JOIN (SELECT PRODUCTNUM, PATH, ROW_NUMBER() OVER (PARTITION BY PRODUCTNUM ORDER BY IMAGENUM) AS IMAGERANK FROM IMAGES) I "
						+ "ON P.PRODUCTNUM = I.PRODUCTNUM AND I.IMAGERANK = 1 "
						+ "WHERE MEMBERID = ? ORDER BY PAY.PAYNUM DESC";

		// 관리자 주문내역 사용
		private static final String SQL_SELECTALL_ADMIN=
				"SELECT "
				+ "PAY.PAYNUM,(SELECT PRODUCT.PRODUCTNAME FROM PAYDETAIL "
				+ "INNER JOIN PRODUCT ON PRODUCT.PRODUCTNUM = PAYDETAIL.PRODUCTNUM "
				+ "WHERE PAY.PAYNUM = PAYDETAIL.PAYNUM AND ROWNUM = 1) PRODUCTNAME,"
				+ "(SELECT COUNT(PAYDETAIL.PRODUCTNUM)-1 FROM PAYDETAIL "
				+ "WHERE PAY.PAYNUM=PAYDETAIL.PAYNUM) PAYCOUNT,MEMBERID,PAYTIME,"
				+ "PAYMETHOD,ZIPCODE,ADDRESS,ADDRESSDETAIL,STATUS FROM PAY ORDER BY PAY.PAYNUM DESC";
		

		// 상태가 'BEFORE' 관리자 메인페이지 사용
		private static final String SQL_SELECTALL_ADMIN_MAIN=
				"SELECT PAY.PAYNUM,(SELECT PRODUCT.PRODUCTNAME FROM PAYDETAIL INNER JOIN PRODUCT ON PRODUCT.PRODUCTNUM = PAYDETAIL.PRODUCTNUM WHERE PAY.PAYNUM = PAYDETAIL.PAYNUM AND ROWNUM = 1) PRODUCTNAME,(SELECT COUNT(PAYDETAIL.PRODUCTNUM)-1 FROM PAYDETAIL WHERE PAY.PAYNUM=PAYDETAIL.PAYNUM) PAYCOUNT,MEMBERID,PAYTIME,PAYMETHOD,ZIPCODE,ADDRESS,ADDRESSDETAIL,STATUS FROM PAY WHERE STATUS='BEFORE' ORDER BY PAY.PAYNUM DESC";

		private static final String SQL_SELECTALL_PAYONE=
				"SELECT "
						+ "MEMBERID,P.PRODUCTNUM,PAY.PAYNUM,PATH,PRODUCTNAME,COMPANY,PRODUCTPRICE,PAYMETHOD,PD.PAYCOUNT,PAY.STATUS "
						+ "FROM PAY "
						+ "INNER JOIN PAYDETAIL PD "
						+ "ON PAY.PAYNUM = PD.PAYNUM "
						+ "INNER JOIN PRODUCT P "
						+ "ON PD.PRODUCTNUM = P.PRODUCTNUM "
						+ "INNER JOIN (SELECT PRODUCTNUM, PATH, ROW_NUMBER() OVER (PARTITION BY PRODUCTNUM ORDER BY IMAGENUM) AS IMAGERANK FROM IMAGES) I "
						+ "ON P.PRODUCTNUM = I.PRODUCTNUM AND I.IMAGERANK = 1 "
						+ "WHERE MEMBERID=? AND PAY.PAYNUM=?";

	// private static final String SQL_SELECTONE="SELECT PAYNUM,MEMBERID FROM PAY WHERE MEMBERID=? ORDER BY PAYNUM DESC LIMIT 0,1";
	private static final String SQL_SELECTONE="SELECT PAYNUM,MEMBERID FROM PAY WHERE MEMBERID=? AND PAYNUM = (SELECT MAX(PAYNUM) FROM PAY)";
	private static final String SQL_SELECTONE_ADMIN_RECENTSALE="SELECT COUNT(PAYNUM) PAYCOUNT FROM PAY WHERE PAYTIME > SYSDATE-1";



	// 결제하기
	public boolean insert(PayVO pVO) {
		System.out.println("PayDAO2 로그 insert() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_INSERT,pVO.getPayMethod(),pVO.getMemberId(),pVO.getZipcode(),pVO.getAddress(),pVO.getAddressDetail());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	public boolean update(PayVO pVO) {
		System.out.println("PayDAO2 로그 update() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_UPDATE,pVO.getPayNum());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	public boolean delete(PayVO pVO) {
		System.out.println("PayDAO2 로그 delete() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_DELETE,pVO.getPayNum(),pVO.getMemberId());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	// 결제 목록 출력
	public List<PayVO> selectAll(PayVO pVO){
		System.out.println("PayDAO2 로그 selectAll() 메서드");
		try {
			if(pVO.getSearchCondition()==null || pVO.getSearchCondition().equals("SELECTALL")) {
				Object[] args= {pVO.getMemberId()};
				return jdbcTemplate.query(SQL_SELECTALL,args, new PayRowMapper());
			}else if (pVO.getSearchCondition().equals("PAYONE")) {
				Object[] args= {pVO.getMemberId(), pVO.getPayNum()};
				return jdbcTemplate.query(SQL_SELECTALL_PAYONE,args, new PayRowMapper());
			}else if (pVO.getSearchCondition().equals("ADMIN")) {
				return jdbcTemplate.query(SQL_SELECTALL_ADMIN, new PayRowMapper_ADMIN());
			}else if (pVO.getSearchCondition().equals("ADMIN_MAIN")) {
				return jdbcTemplate.query(SQL_SELECTALL_ADMIN_MAIN, new PayRowMapper_ADMIN());
			}
		}catch(Exception e) {
			return null;
		}
		return null;
	}


	public PayVO selectOne(PayVO pVO){
		System.out.println("PayDAO2 로그 selectOne() 메서드");
		try {
			if(pVO.getSearchCondition() == null || pVO.getSearchCondition().equals("SELECTONE")) {	
				Object[] args= {pVO.getMemberId()};
				return jdbcTemplate.queryForObject(SQL_SELECTONE,args,new PayRowMapper_SELECTONE());
			}else if(pVO.getSearchCondition().equals("ADMIN_RECENTSALE")) {
				return jdbcTemplate.queryForObject(SQL_SELECTONE_ADMIN_RECENTSALE,new PayRowMapper_ADMIN_RECENTSALE());
			}
		}catch(Exception e){
			return null;
		}
		return null;
	}
}

class PayRowMapper implements RowMapper<PayVO> {
	@Override
	public PayVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		PayVO data = new PayVO();
		data.setPayNum(rs.getInt("PAYNUM"));
		data.setPayMethod(rs.getString("PAYMETHOD"));
		data.setMemberId(rs.getString("MEMBERID"));
		data.setProductNum(rs.getInt("PRODUCTNUM"));
		data.setPath(rs.getString("PATH"));
		data.setProductName(rs.getString("PRODUCTNAME"));
		data.setCompany(rs.getString("COMPANY"));
		data.setProductPrice(rs.getString("PRODUCTPRICE"));
		data.setPayCount(rs.getInt("PAYCOUNT"));
		data.setStatus(rs.getString("STATUS"));

		return data;
	}
}
class PayRowMapper_SELECTONE implements RowMapper<PayVO> {
	@Override
	public PayVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		PayVO data = new PayVO();
		data.setPayNum(rs.getInt("PAYNUM"));
		data.setMemberId(rs.getString("MEMBERID"));
		
		return data;
	}
}
class PayRowMapper_ADMIN_RECENTSALE implements RowMapper<PayVO> {
	@Override
	public PayVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		PayVO data = new PayVO();
		data.setPayCount(rs.getInt("PAYCOUNT"));
		return data;
	}
}
class PayRowMapper_ADMIN implements RowMapper<PayVO> {
	@Override
	public PayVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		PayVO data = new PayVO();
		//PAYNUM,PRODUCTNAME,PAYCOUNT,PAYTIME,PAYMETHOD,ZIPCODE,ADDRESS,ADDRESSDETAIL,STATUS
		data.setPayNum(rs.getInt("PAYNUM"));
		data.setProductName(rs.getString("PRODUCTNAME"));
		data.setPayCount(rs.getInt("PAYCOUNT"));
		data.setMemberId(rs.getString("MEMBERID"));
		data.setPayTime(rs.getDate("PAYTIME"));
		data.setPayMethod(rs.getString("PAYMETHOD"));
		data.setZipcode(rs.getString("ZIPCODE"));
		data.setAddress(rs.getString("ADDRESS"));
		data.setAddressDetail(rs.getString("ADDRESSDETAIL"));
		data.setStatus(rs.getString("STATUS"));

		return data;
	}
}
