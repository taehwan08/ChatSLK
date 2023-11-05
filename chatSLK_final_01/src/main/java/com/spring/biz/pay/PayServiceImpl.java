package com.spring.biz.pay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PayService")
public class PayServiceImpl implements PayService {
	
	@Autowired
	private PayDAO2 PayDAO; 
	
	@Override
	public PayVO selectOne(PayVO pVO) {
		return PayDAO.selectOne(pVO) ;
	}

	@Override
	public List<PayVO> selectAll(PayVO pVO) {
		return PayDAO.selectAll(pVO);
	}

	@Override
	public boolean insert(PayVO pVO) {
		return PayDAO.insert(pVO);
	}

	@Override
	public boolean update(PayVO pVO) {
		return PayDAO.update(pVO);
	}

	@Override
	public boolean delete(PayVO pVO) {
		return PayDAO.delete(pVO);
	}

}
