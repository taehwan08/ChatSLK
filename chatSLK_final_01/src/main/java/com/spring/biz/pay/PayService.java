package com.spring.biz.pay;

import java.util.List;

public interface PayService {


	public PayVO selectOne(PayVO payVO);
	public List<PayVO> selectAll(PayVO payVO);
	
	public boolean insert(PayVO payVO);
	public boolean update(PayVO payVO);
	public boolean delete(PayVO payVO);

}
