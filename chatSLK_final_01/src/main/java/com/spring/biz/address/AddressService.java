package com.spring.biz.address;

import java.util.List;
public interface AddressService {

	public AddressVO selectOne(AddressVO aVO);
	public List<AddressVO> selectAll(AddressVO aVO);
	
	public boolean insert(AddressVO aVO);
	public boolean update(AddressVO aVO);
	public boolean delete(AddressVO aVO);

}




