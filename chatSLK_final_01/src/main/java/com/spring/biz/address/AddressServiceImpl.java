package com.spring.biz.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AddressService")
public class AddressServiceImpl implements AddressService {

	
	@Autowired
	private AddressDAO2 AddressDAO;
	// 의존관계 -> DI
	
	@Override
	public AddressVO selectOne(AddressVO aVO) {
		return AddressDAO.selectOne(aVO);
	}

	@Override
	public List<AddressVO> selectAll(AddressVO aVO) {
		return AddressDAO.selectAll(aVO);
	}

	@Override
	public boolean insert(AddressVO aVO) {
		return AddressDAO.insert(aVO);
	}

	@Override
	public boolean update(AddressVO aVO) {
		return AddressDAO.update(aVO);
	}

	@Override
	public boolean delete(AddressVO aVO) {
		return AddressDAO.delete(aVO);
	}
}