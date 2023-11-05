package com.spring.biz.paydetail;

import java.util.List;

import com.spring.biz.pay.PayVO;

public interface PayDetailService {

	public PayDetailVO selectOne(PayDetailVO pdVO);
	public List<PayDetailVO> selectAll(PayDetailVO pdVO);
	
	public boolean insert(PayDetailVO pdVO);
	public boolean update(PayDetailVO pdVO);
	public boolean delete(PayDetailVO pdVO);
}
