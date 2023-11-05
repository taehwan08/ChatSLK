package com.spring.biz.images;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("ImageDAO")
public class ImagesDAO2 {
	@Autowired
	private JdbcTemplate jdbcTemplate;

//	private static final String SQL_INSERT = "INSERT INTO IMAGES (IMAGENUM,PATH,PRODUCTNUM) VALUES(IMAGES_SEQ.NEXTVAL,?,?)";
	private static final String SQL_INSERT = "INSERT INTO IMAGES (IMAGENUM,PATH,PRODUCTNUM) VALUES((SELECT COALESCE(MAX(IMAGENUM), 0) + 1 FROM IMAGES),?,?)";
	private static final String SQL_UPDATE = "UPDATE IMAGESS SET PATH=?,PRODUCTNUM=? WHERE IMAGENUM=?";
	private static final String SQL_DELETE = "DELETE FROM IMAGES WHERE IMAGENUM = ?";
	private static final String SQL_SELECTONE = "SELECT IMAGENUM,PATH,PRODUCTNUM FROM IMAGES WHERE PRODUCTNUM=? AND PATH NOT LIKE '%(1)%';";
	private static final String SQL_SELECTALL = "SELECT IMAGENUM,PATH,PRODUCTNUM FROM IMAGES WHERE PRODUCTNUM= ? ";
	


	public boolean insert(ImagesVO iVO) {
		System.out.println("ImagesDAO2 로그 insert() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_INSERT,iVO.getPath(),iVO.getProductNum());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;

	}

	public boolean update(ImagesVO iVO) {
		System.out.println("ImagesDAO2 로그 insert() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_UPDATE,iVO.getPath(),iVO.getProductNum(),iVO.getImageNum());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;

	}

	public boolean delete(ImagesVO iVO) {
		System.out.println("ImagesDAO2 로그 insert() 메서드");
		try {
			int rs = jdbcTemplate.update(SQL_DELETE,iVO.getImageNum());
			if (rs <= 0) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;

	}
	public List<ImagesVO> selectAll(ImagesVO iVO) {
		try {
			Object[] args = { iVO.getProductNum() };
			return jdbcTemplate.query(SQL_SELECTALL, args,new ImagesRowMapper());
		}catch(Exception e) {
			return null;
		}
	}
	
	public ImagesVO selectOne(ImagesVO iVO) {
		try {
			Object[] args = { iVO.getProductNum() };
			return jdbcTemplate.queryForObject(SQL_SELECTONE, args, new ImagesRowMapper());
		}catch(Exception e) {
			return null;
		}
	}
}



class ImagesRowMapper implements RowMapper<ImagesVO> {

	@Override
	public ImagesVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ImagesVO data = new ImagesVO();
		data.setImageNum(rs.getInt("IMAGENUM"));
		data.setPath(rs.getString("PATH"));
		data.setProductNum(rs.getInt("PRODUCTNUM"));
		return data;
	}
}
