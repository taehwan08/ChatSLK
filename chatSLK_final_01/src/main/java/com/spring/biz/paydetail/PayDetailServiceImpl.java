package com.spring.biz.paydetail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PayDetailService")
public class PayDetailServiceImpl implements PayDetailService{
	
	@Autowired
	private PayDetailDAO2 PayDetailDAO;
	
	@Override
	public List<PayDetailVO> selectAll(PayDetailVO pdVO) {
		return PayDetailDAO.selectAll(pdVO);
	}
	@Override
	public PayDetailVO selectOne(PayDetailVO pdVO) {
		return PayDetailDAO.selectOne(pdVO);
	}
	@Override
	public boolean insert(PayDetailVO pdVO) {
		return PayDetailDAO.insert(pdVO);
	}
	@Override
	public boolean update(PayDetailVO pdVO) {
		return PayDetailDAO.update(pdVO);
	}
	@Override
	public boolean delete(PayDetailVO pdVO) {
		return PayDetailDAO.delete(pdVO);
	}
}
